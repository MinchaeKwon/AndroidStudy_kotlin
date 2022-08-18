package com.example.androidstudy_kotlin.db.dao

import androidx.room.*
import com.example.androidstudy_kotlin.db.entity.User

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User")
    fun getAll(): List<User>

    @Query("DELETE FROM User WHERE name = :name") // 'name'에 해당하는 유저를 삭제
    fun deleteUserByName(name: String)
}