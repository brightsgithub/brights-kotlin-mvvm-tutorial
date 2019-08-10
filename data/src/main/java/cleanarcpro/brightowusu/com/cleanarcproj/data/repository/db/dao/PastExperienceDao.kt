package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao

import androidx.room.*
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityPastExperience

/**
 * Created by Bright Owusu-Amankwaa on 2019-08-19.
 */
@Dao
interface PastExperienceDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPastExp(pastExperiences: List<EntityPastExperience>)

    @Update
    suspend fun updatePastExp(pastExperience: EntityPastExperience)

    @Delete
    suspend fun deletePastExp(pastExperience: EntityPastExperience)

    @Query("SELECT * FROM past_exp_table pe where pe.user_id = :userId order by pe.id asc" )
    suspend fun loadPastExp(userId: Long) : List<EntityPastExperience>


}