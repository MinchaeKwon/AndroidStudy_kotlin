package com.example.androidstudy_kotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidstudy_kotlin.db.dao.UserDao
import com.example.androidstudy_kotlin.db.entity.User

// version은 앱을 업데이트하다가 entity의 구조를 변경해야 하는 일이 생겼을 때 이전 구조와 현재 구조를 구분해주는 역할
// 구조가 바뀌었는데 버전이 같다면 에러가 발생하고 디버깅 되지 않음, 처음 DB 생성하는 경우에는 1을 넣어줌
@Database(entities = [User::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "test-database"
                    ).build()
                }
            }

            return instance
        }
    }
}