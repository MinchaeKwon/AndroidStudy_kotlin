package com.example.androidstudy_kotlin.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var name: String?,
    var age: Int?,
    var phone: String?
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
