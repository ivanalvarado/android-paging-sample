package com.ivanalvarado.template.database.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class UserEntity(
    @ColumnInfo(name = "id") @PrimaryKey @field:NonNull val id: Int,
    @ColumnInfo(name = "user_name") val userName: String,
    @ColumnInfo(name = "reputation") val reputation: Int,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    @ColumnInfo(name = "website_url") val websiteUrl: String,
    @ColumnInfo(name = "last_access_date") val lastAccessDate: Int
)