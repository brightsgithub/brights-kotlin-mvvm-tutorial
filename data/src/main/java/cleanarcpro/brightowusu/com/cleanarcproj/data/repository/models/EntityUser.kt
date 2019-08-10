package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


private const val id_ = "id"
private const val name_ = "name"
private const val email_ = "email"
private const val phone_ = "phone"

@Entity(tableName = "user_table")
data class EntityUser(

        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = id_)
        @SerializedName(id_) val id: Long,

        @ColumnInfo(name = name_)
        @SerializedName(name_) val userName: String,

        @ColumnInfo(name = email_)
        @SerializedName(email_) val email: String,

        @ColumnInfo(name = phone_)
        @SerializedName(phone_) val phone: String
)