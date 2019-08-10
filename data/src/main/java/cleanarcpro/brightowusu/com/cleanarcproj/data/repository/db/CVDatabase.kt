package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.PastExperienceDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.ProSummaryDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.UserDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityUser
import androidx.room.Room



/**
 * Created by Bright Owusu-Amankwaa on 2019-08-19.
 */
@Database(entities = [
    EntityUser::class,
    EntityPastExperience::class,
    EntityProfessionalSummary::class
    ],
        version = 1)
abstract class CVDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun pastExperienceDao(): PastExperienceDao
    abstract fun proSummaryDao(): ProSummaryDao

    companion object {
        @Volatile private var instance: CVDatabase? = null
        private val LOCK = Any()

        operator fun invoke(appContext: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(appContext).also { instance = it }
        }


        private fun buildDatabase(appContext: Context) : CVDatabase{
           return Room.databaseBuilder(appContext.getApplicationContext(),
                    CVDatabase::class.java, "cv_database.db")
                    .build()
        }
    }
}