package kr.leesunr.news.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import kr.leesunr.news.data.room.dto.VisitHistoryDTO

@Dao
internal interface VisitHistoryDAO {
    @Insert
    suspend fun insert(entity: VisitHistoryDTO)
}