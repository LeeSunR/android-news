package kr.leesunr.news.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import kr.leesunr.news.data.room.dto.HeadlineDTO

@Dao
internal interface HeadlineDAO {
    @Query("SELECT * FROM Headline")
    fun getAll(): Flow<List<HeadlineDTO>>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<HeadlineDTO>)

    @Update
    suspend fun update(entity: HeadlineDTO)

    @Query("DELETE FROM Headline")
    fun deleteAll()
}