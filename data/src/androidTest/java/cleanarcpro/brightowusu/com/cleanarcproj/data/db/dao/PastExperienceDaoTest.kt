package cleanarcpro.brightowusu.com.cleanarcproj.data.db.dao

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import cleanarcpro.brightowusu.com.cleanarcproj.data.db.dao.DBTestUtils.Companion.createPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.data.db.dao.DBTestUtils.Companion.createUser
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.CVDatabase
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.PastExperienceDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.UserDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityUser
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Bright Owusu-Amankwaa on 2019-08-25.
 */
@RunWith(AndroidJUnit4::class)
class PastExperienceDaoTest {

    private lateinit var userDao: UserDao
    private lateinit var pastExperienceDao: PastExperienceDao
    private lateinit var db: CVDatabase
    private val userId = 1L

    @Before
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, CVDatabase::class.java).build()

        pastExperienceDao = db.pastExperienceDao()
        userDao = db.userDao()
    }

    @Test
    fun loadCreatedPastExperiences() = runBlocking {
        val pastExpSize = 3
        val user = loadCreatedUser()
        val pastExperiences = createPastExperiences(user.id, pastExpSize)

        pastExperienceDao.insertPastExp(pastExperiences)

        val pastExpFromDB = pastExperienceDao.loadPastExp(user.id)

        assertTrue(pastExpFromDB.isNotEmpty())
        assertTrue(pastExpFromDB.size == pastExpSize +1)
    }

    @Test(expected = SQLiteConstraintException::class)
    fun shouldThrowSQLForeignKeyException() = runBlocking {
        val pastExperiences = createPastExperiences(userId, 3)

        pastExperienceDao.insertPastExp(pastExperiences) // oops, no user exists
    }

    @Test
    fun updateAPastExp() = runBlocking {
        val pastExpSize = 3
        val user = loadCreatedUser()
        val pastExperiences = createPastExperiences(user.id, pastExpSize)

        // Insert the past experiences
        pastExperienceDao.insertPastExp(pastExperiences)

        // Load the first past experience to get its Id
        val firstPastExpId = pastExperienceDao.loadPastExp(userId)[0].id

        // create a past exp with the same firstPastExpId but change some properties
        val pastExpToBeEdited = EntityPastExperience(
                firstPastExpId,
                userId,
                "companyName_EDITED",
                "roleName_EDITED",
                "datesStart_EDITED",
                "datesEnd_EDITED",
                "responsibilities_EDITED",
                "companyLogo_EDITED",
                "techUsed_EDITED")

        // Update the entity with the same firstPastExpId
        pastExperienceDao.updatePastExp(pastExpToBeEdited)

        // Load the past experiences and again, get the first past exp
        val updated = pastExperienceDao.loadPastExp(userId)[0]

        // confirm that past exp has been updated.
        assertThat(pastExpToBeEdited, equalTo(updated))
    }

    @Test
    fun shouldRemoveAllPastExpWhenUserIsDeleted() = runBlocking {
        val pastExpSize = 3
        val user = loadCreatedUser()
        val pastExperiences = createPastExperiences(user.id, pastExpSize)

        pastExperienceDao.insertPastExp(pastExperiences)

        // be sure that records were inserted within the db for past exp
        val pastExpFromDB = pastExperienceDao.loadPastExp(user.id)
        assertTrue(pastExpFromDB.isNotEmpty())
        assertTrue(pastExpFromDB.size == pastExpSize +1)

        // Removing the user should cascade delete all related children
        userDao.deleteUser(user)

        // verify that all past experiences have been removed
        val zeroExpFromDB = pastExperienceDao.loadPastExp(user.id)
        assertTrue(zeroExpFromDB.isEmpty())
    }


    suspend fun loadCreatedUser() : EntityUser {
        val user = createUser(userId,"Bright")
        userDao.insertUser(user)
        return userDao.loadUser(userId)
    }

}