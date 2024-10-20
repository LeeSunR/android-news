package kr.leesunr.news.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kr.leesunr.news.data.room.dto.HeadlineDTO
import kr.leesunr.news.data.room.dto.HeadlineWithIsVisitHistoryDTO

@Dao
internal interface HeadlineDAO {
    @Query("SELECT * FROM Headline ORDER BY publishedAt DESC")
    fun getAll(): Flow<List<HeadlineWithIsVisitHistoryDTO>>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<HeadlineDTO>)

    @Transaction
    suspend fun fetch(entities: List<HeadlineDTO>) {
        deleteAll()
        insertAll(entities)
    }

    @Query("DELETE FROM Headline")
    fun deleteAll()
}