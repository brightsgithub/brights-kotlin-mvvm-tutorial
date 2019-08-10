package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

// fields

private const val user_id = "user_id"

private const val id_ = "id"
private const val company_name = "company_name"
private const val role_name = "role_name"
private const val dates_start = "dates_start"
private const val date_end = "date_end"
private const val responsibilities_ = "responsibilities"
private const val company_logo = "company_logo"
private const val tech_used = "tech_used"

@Entity(tableName = "past_exp_table",
        foreignKeys = arrayOf(ForeignKey(
        entity = EntityUser::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"),
                onDelete = CASCADE)
    )
)
data class EntityPastExperience(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id")
        @SerializedName("id") val id: Long,

        @ColumnInfo(name = "user_id")
        @SerializedName("user_id") val userId: Long,

        @ColumnInfo(name = "company_name")
        @SerializedName("company_name") val companyName: String,

        @ColumnInfo(name = "role_name")
        @SerializedName("role_name") val roleName: String,

        @ColumnInfo(name = "dates_start")
        @SerializedName("dates_start") val datesStart: String,

        @ColumnInfo(name = "date_end")
        @SerializedName("date_end") val dateEnd: String,

        @ColumnInfo(name = "responsibilities")
        @SerializedName("responsibilities") val responsibilities: String,

        @ColumnInfo(name = "company_logo")
        @SerializedName("company_logo") val companyLogo: String,

        @ColumnInfo(name = "tech_used")
        @SerializedName("tech_used") val techUsed: String
)