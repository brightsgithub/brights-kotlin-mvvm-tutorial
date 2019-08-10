package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao

import androidx.room.*
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityProfessionalSummary

/**
 * Created by Bright Owusu-Amankwaa on 2019-08-19.
 */

@Dao
interface ProSummaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(proSum: EntityProfessionalSummary)

    @Update
    suspend fun update(proSum: EntityProfessionalSummary)

    @Delete
    suspend fun delete(proSum: EntityProfessionalSummary)

    @Query("SELECT * FROM professional_summary_table ps where ps.user_id = :userId" )
    suspend fun load(userId: Long) : EntityProfessionalSummary

}