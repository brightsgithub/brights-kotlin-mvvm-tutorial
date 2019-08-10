package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.usercv

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.PastExperienceDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.ProSummaryDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.UserDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapEntityUserDetailsToDomain
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapPastExperiencesEntityToDomain
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapProSummaryEntityToDomain
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.*
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.network.IUserCVApi
import cleanarcpro.brightowusu.com.cleanarcproj.domain.exceptions.NoConnectivityConnection
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import java.net.SocketTimeoutException

/**
 * UserRepositoryImpl -  decides where our data comes from, i.e. database, network, shared pref etc.
 * TODO - Use Room DB for single source of truth.
 */
class UserRepositoryImpl(
        val uerCVApi: IUserCVApi,
        val userDao: UserDao,
        val proSummaryDao: ProSummaryDao,
        val pastExperienceDao: PastExperienceDao) : IUserRepository{

    override suspend fun getUser(userId: Long): DomainUser {

        return try {
            val entity = uerCVApi.getUserDetails(userId)
            userDao.insertUser(entity)
            MapEntityUserDetailsToDomain.transform(entity)
        } catch (ex: Exception) {
            handleError(ex) {MapEntityUserDetailsToDomain.transform(userDao.loadUser(userId))}
        }
    }

    override suspend fun getPastExperiences(userId: Long): DomainPastExperiences {

        return try {
            val entity = uerCVApi.getPastExperiences(userId)
            pastExperienceDao.insertPastExp(entity.pastExperiences)
            return MapPastExperiencesEntityToDomain.transform(entity)
        } catch (ex: Exception) {
            handleError(ex) {MapPastExperiencesEntityToDomain.transform(pastExperienceDao.loadPastExp(userId))}
        }
    }

    override suspend fun getProfessionalSummary(userId: Long): DomainProfessionalSummary {

        return try {
            val entity = uerCVApi.getProfessionalSummary(userId)
            proSummaryDao.insert(entity)
            MapProSummaryEntityToDomain.transform(entity)
        } catch (ex: Exception) {
            handleError(ex) {MapProSummaryEntityToDomain.transform(proSummaryDao.load(userId))}
        }
    }

    /**
     * All errors handled the same way.
     * If there is a NoConnectivityConnection or SocketTimeoutException, execute the suspend function
     * block, which obtains data from the DB and returns it.
     * This function will return the same return type to the caller of this function.
     * Otherwise, if the exception is anything other than what we are looking for then just re-throw
     * the exception.
     */
    private suspend fun <T> handleError(ex: Exception, loadFromDB: suspend () -> T) : T {
        when(ex) {
            is NoConnectivityConnection,
            is SocketTimeoutException -> {
                return loadFromDB()
            }
            else -> throw ex
        }
    }
}