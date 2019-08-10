package cleanarcpro.brightowusu.com.cleanarcproj.data.db.dao

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityUser

/**
 * Created by Bright Owusu-Amankwaa on 2019-08-25.
 */
class DBTestUtils {

    companion object {

        fun createUser(id: Long, name: String): EntityUser {
            return EntityUser(id, name, "some_email@email.com", "12345")
        }

        fun createPastExperiences(userId: Long, numOfPastExp: Int): List<EntityPastExperience> {

            val pastExperiences = ArrayList<EntityPastExperience>(numOfPastExp)

            for (i in 0..numOfPastExp) {
                pastExperiences.add(
                        EntityPastExperience(
                        i.toLong(),
                        userId,
                        "companyName_$i",
                        "roleName_$i",
                        "datesStart_$i",
                        "datesEnd_$i",
                        "responsibilities_$i",
                        "companyLogo_$i",
                        "techUsed$i")
                )
            }
            return pastExperiences
        }

        fun createProfessionalSummary(userId: Long, proSumm: String): EntityProfessionalSummary {
            return EntityProfessionalSummary(1,userId, proSumm)
        }
    }
}