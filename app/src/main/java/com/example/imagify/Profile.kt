package com.example.imagify

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_details")
data class Profile(
    @PrimaryKey(autoGenerate = true) val id: Int?,

    @ColumnInfo(name = "first_name") val firstName:String?,

    @ColumnInfo(name = "Email_id") val Email:String?,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) val img: ByteArray?,

    @ColumnInfo(name = "Phone_No") val phoneNo: String?

)
