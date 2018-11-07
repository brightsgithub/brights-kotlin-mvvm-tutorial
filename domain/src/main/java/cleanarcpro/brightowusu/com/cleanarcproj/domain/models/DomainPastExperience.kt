package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models



data class DomainPastExperience(
        val id: Int,
        val companyName: String,
        val roleName: String,
        val datesStart: String,
        val dateEnd: String,
        val responsibilities: String,
        val companyLogo: String,
        val techUsed: String
)