package kr.leesunr.news.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kr.leesunr.news.data.room.dto.HeadlineDTO

@Dao
internal interface HeadlineDAO {
    @Query("SELECT * FROM Headline")
    fun getAll(): Flow<List<HeadlineDTO>>

    @Insert
    suspend fun insertAll(entities: List<HeadlineDTO>)

    @Query("DELETE FROM Headline")
    fun deleteAll()
}