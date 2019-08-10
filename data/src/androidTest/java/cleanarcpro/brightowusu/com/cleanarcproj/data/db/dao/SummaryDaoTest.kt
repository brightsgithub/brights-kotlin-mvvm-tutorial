package cleanarcpro.brightowusu.com.cleanarcproj.data.db.dao

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import cleanarcpro.brightowusu.com.cleanarcproj.data.db.dao.DBTestUtils.Companion.createProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.CVDatabase
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.ProSummaryDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.UserDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityUser
import junit.framework.Assert
import junit.framework.Assert.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Bright Owusu-Amankwaa on 2019-08-25.
 */
@RunWith(AndroidJUnit4::class)
class SummaryDaoTest {

    private lateinit var userDao: UserDao
    private lateinit var summaryDao: ProSummaryDao
    private lateinit var db: CVDatabase
    private val userId = 1L
    private val proSumText =  "My professional summary"

    @Before
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, CVDatabase::class.java).build()

        summaryDao = db.proSummaryDao()
        userDao = db.userDao()
    }


    @Test
    fun loadCreatedProSummary() = runBlocking {
        val user = loadCreatedUser()
        val proSumm = createProfessionalSummary(user.id,proSumText)

        summaryDao.insert(proSumm)

        val summaryFromDB = summaryDao.load(user.id)

        assert(proSumm == summaryFromDB)

    }

    @Test(expected = SQLiteConstraintException::class)
    fun shouldThrowSQLForeignKeyException() = runBlocking {
        val proSum = createProfessionalSummary(userId, proSumText)

        summaryDao.insert(proSum) // oops, no user exists
    }

    @Test
    fun updateAPastExp() = runBlocking {
        val user = loadCreatedUser()
        val proSum = createProfessionalSummary(user.id, proSumText)

        // Insert the summary
        summaryDao.insert(proSum)

        // Load pro sum Id
        val proSumId = summaryDao.load(user.id).id

        // create a pro summary with the same id loaded from the db but change some properties
        val proSummToBeUpdated = EntityProfessionalSummary(proSumId, user.id, proSumText+"_EDITED")

        // Update the entity
        summaryDao.update(proSummToBeUpdated)

        // Load the proSumm
        val updated = summaryDao.load(user.id)

        // confirm that past exp has been updated.
        assert(proSummToBeUpdated == updated)
    }

    @Test
    fun shouldRemoveAllProSummWhenUserIsDeleted() = runBlocking {
        val user = loadCreatedUser()
        val proSumm = createProfessionalSummary(user.id, proSumText)

        // Insert the summary
        summaryDao.insert(proSumm)

        // be sure that records were inserted within the db for pro summary
        val proSumFromDb = summaryDao.load(user.id)
        assert(proSumm == proSumFromDb)

        // Removing the user should cascade delete all related children
        userDao.deleteUser(user)

        // verify that pro summary has been deleted
        val nullProSumFromDB = summaryDao.load(user.id)
        assertNull(nullProSumFromDB)
    }

    suspend fun loadCreatedUser() : EntityUser {
        val user = DBTestUtils.createUser(userId, "Bright")
        userDao.insertUser(user)
        return userDao.loadUser(userId)
    }
}