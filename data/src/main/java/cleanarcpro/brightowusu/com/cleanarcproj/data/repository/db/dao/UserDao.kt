package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao

import androidx.room.*
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityUser

/**
 * Created by Bright Owusu-Amankwaa on 2019-08-19.
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: EntityUser)

    @Update
    suspend fun updateUser(user: EntityUser)

    @Delete
    suspend fun deleteUser(user: EntityUser)

    @Query("SELECT * FROM user_table ut where ut.id = :id")
    suspend fun loadUser(id: Long) : EntityUser

}