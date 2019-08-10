package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

private const val user_id = "user_id"

private const val id_ = "id"
private const val professional_summary = "professional_summary"

@Entity(
        tableName = "professional_summary_table",
        foreignKeys = arrayOf(ForeignKey(
                entity = EntityUser::class,
                parentColumns = arrayOf(id_),
                childColumns = arrayOf(user_id),
                onDelete = ForeignKey.CASCADE)
        )
)
data class EntityProfessionalSummary(

        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = id_)
        @SerializedName(id_) val id: Long,

        @ColumnInfo(name = user_id)
        @SerializedName(user_id) val userId: Long,

        @ColumnInfo(name = professional_summary)
        @SerializedName(professional_summary) val professionalSummary: String
)