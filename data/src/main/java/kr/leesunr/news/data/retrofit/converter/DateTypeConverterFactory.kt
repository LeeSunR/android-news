package kr.leesunr.news.data.retrofit.converter

import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.util.Date
import java.util.Locale
import java.util.TimeZone

internal object DateTypeConverterFactory : Converter.Factory() {
    fun create(): Converter.Factory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .registerTypeHierarchyAdapter(Date::class.java, DateTypeAdapter())
                .create()
        )
    }
}

private class DateTypeAdapter : TypeAdapter<Date?>() {
    override fun write(writer: JsonWriter, date: Date?) {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ROOT)
        if (date == null) {
            writer.nullValue()
        } else {
            writer.value(format.format(date))
        }
    }

    override fun read(reader: JsonReader): Date? {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull()
            return null
        }
        val dateString = reader.nextString()
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.getDefault())

        return try {
            date.timeZone = TimeZone.getTimeZone("UTC")
            date.parse(dateString) ?: throw NullPointerException()
        } catch (e: ParseException) {
            try {
                val zonedDataTime = ZonedDateTime.parse(dateString).toInstant()
                Date.from(zonedDataTime)
            } catch (e: ParseException) {
                e.printStackTrace()
                throw e
            }
        }
    }
}