package kr.leesunr.news.data.room

import androidx.room.TypeConverter
import java.util.Date

object RoomConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}