package cleanarcpro.brightowusu.com.cleanarcproj.data.db.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.CVDatabase
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.UserDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityUser
import junit.framework.Assert.assertNull
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Bright Owusu-Amankwaa on 2019-08-25.
 */
@RunWith(AndroidJUnit4::class)
open class UserDaoTest {

    private lateinit var userDao: UserDao
    private lateinit var db: CVDatabase
    private val userId = 1L

    @Before
    fun initDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, CVDatabase::class.java).build()

        userDao = db.userDao()
    }

    @Test
    fun loadCreatedUser()  = runBlocking {
        val user = createUser()
        userDao.insertUser(user)

        val byId = userDao.loadUser(userId)
        assertTrue(byId ==  user)
    }


    @Test
    fun updateCreatedUser()  = runBlocking {
        val user = createUser()
        userDao.insertUser(user)

        // Since we have an immutable object, lets create the user with the same ID, edit, then update
        val editedUserWithSameId = DBTestUtils.createUser(userId, "BrightEdited")
        userDao.updateUser(editedUserWithSameId)// Update user with Id = userId

        val editedUserFromDB = userDao.loadUser(userId)
        assertTrue(editedUserFromDB == editedUserWithSameId)
    }

    @Test
    fun deleteCreatedUser()  = runBlocking {
        val user = createUser()
        userDao.insertUser(user)

        val byId = userDao.loadUser(userId)
        assertTrue(byId == user)

        userDao.deleteUser(user)

        val noUser = userDao.loadUser(userId)
        assertNull(noUser)
    }

    private fun createUser() : EntityUser {
        return DBTestUtils.createUser(1, "Bright")
    }
}