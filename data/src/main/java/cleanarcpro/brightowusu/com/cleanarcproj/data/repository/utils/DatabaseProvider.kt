package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils

import android.content.Context
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.CVDatabase
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.PastExperienceDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.ProSummaryDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.UserDao

/**
 * Created by Bright Owusu-Amankwaa on 2019-08-19.
 */
class DatabaseProvider {

    companion object {

        fun providesUserDao(appContext: Context) : UserDao {
            val db = CVDatabase.invoke(appContext)
            return db.userDao()
        }
        fun providesProSummaryDao(appContext: Context) : ProSummaryDao {
            val db = CVDatabase.invoke(appContext)
            return db.proSummaryDao()
        }

        fun providesPastExperienceDao(appContext: Context) : PastExperienceDao {
            val db = CVDatabase.invoke(appContext)
            return db.pastExperienceDao()
        }
    }
}