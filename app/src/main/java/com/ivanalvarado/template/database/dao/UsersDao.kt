package com.ivanalvarado.template.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ivanalvarado.template.database.entity.UserEntity
import io.reactivex.Observable

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<UserEntity>): LongArray

    @Query("SELECT * FROM users ORDER BY reputation DESC")
    fun getUsersRx(): Observable<List<UserEntity>>

    @Query("SELECT * FROM users ORDER BY reputation DESC")
    fun getUsersLiveData(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM users ORDER BY reputation DESC")
    fun getUsersPaged(): DataSource.Factory<Int, UserEntity>
}