package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models

import android.os.Parcel
import android.os.Parcelable


data class UIPastExperience(
        val id: Long,
        val companyName: String,
        val roleName: String,
        val datesStart: String,
        val dateEnd: String,
        val responsibilities: String,
        val companyLogo: String,
        val techUsed: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(companyName)
        parcel.writeString(roleName)
        parcel.writeString(datesStart)
        parcel.writeString(dateEnd)
        parcel.writeString(responsibilities)
        parcel.writeString(companyLogo)
        parcel.writeString(techUsed)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UIPastExperience> {
        override fun createFromParcel(parcel: Parcel): UIPastExperience {
            return UIPastExperience(parcel)
        }

        override fun newArray(size: Int): Array<UIPastExperience?> {
            return arrayOfNulls(size)
        }
    }
}