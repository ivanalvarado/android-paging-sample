package com.ivanalvarado.template.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ivanalvarado.template.database.dao.UsersDao
import com.ivanalvarado.template.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UsersDao
}