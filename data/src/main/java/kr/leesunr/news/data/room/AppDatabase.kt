package kr.leesunr.news.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kr.leesunr.news.data.room.dao.HeadlineDAO
import kr.leesunr.news.data.room.dao.VisitHistoryDAO
import kr.leesunr.news.data.room.dto.HeadlineDTO
import kr.leesunr.news.data.room.dto.HeadlineWithIsVisitHistoryDTO
import kr.leesunr.news.data.room.dto.VisitHistoryDTO

private const val DATABASE_VERSION = 1
private const val DATABASE_NAME = "news.db"

@Database(
    entities = [
        HeadlineDTO::class,
        VisitHistoryDTO::class,
    ], version = DATABASE_VERSION
)

@TypeConverters(RoomConverter::class)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun getHeadlineDAO(): HeadlineDAO
    abstract fun getVisitHistoryDAO(): VisitHistoryDAO
}

internal object RoomFactory {
    fun create(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .build()
    }
}