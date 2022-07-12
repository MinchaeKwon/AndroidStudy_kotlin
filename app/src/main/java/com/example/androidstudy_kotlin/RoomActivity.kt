package com.example.androidstudy_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidstudy_kotlin.data.User
import com.example.androidstudy_kotlin.databinding.ActivityRoomBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

// Room, Coroutine 사용
class RoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoomBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(applicationContext)!!
        getUserList()

        binding.btnSaveUser.setOnClickListener {
            addUser()
            getUserList()
        }

        binding.btnPrev.setOnClickListener {
            finish()
        }
    }

    private fun addUser() {
        val name = binding.etUserName.text.toString()
        val age = Integer.parseInt(binding.etUserAge.text.toString())
        val phone = binding.etUserPhone.text.toString()

        CoroutineScope(Dispatchers.IO).launch {
            db.userDao().insert(User(name, age, phone))
        }
    }

    private fun getUserList() {
        var userList = "유저 리스트\n"

        /*
            스레드 종류
            1. Main : 말 그대로 메인 스레드에 대한 Context이며 UI 갱신이나 Toast 등의 View 작업에 사용(메인 스레드. 화면 UI 작업 등을 하는 곳)
            2. IO : 네트워킹이나 내부 DB 접근 등 백그라운드에서 필요한 작업을 수행할 때 사용 (네트워크, DB 등 백그라운드에서 필요한 작업을 하는 곳)
            3. Default : 크기가 큰 리스트를 다루거나 필터링을 수행하는 등 무거운 연산이 필요한 작업에 사용 (정렬이나 무거운 계산 작업 등을 하는 곳)

            메소드 종류
            1. launch : 즉시 실행되고, 실행 결과 반환하지 않음(실행하고 잊어버리는 형태의 코루틴 실행), 관리를 위한 Job 객체를 반환, join을 통해 완료 대기 가능
            2. async : 결과나 예외를 반환, 실행결과는 Deferred<T>를 통해서 반환하며 await을 통해서 받을 수 있고, await은 작업이 완료될때까지 기다림

            CoroutineScope 안에 있는 것은 별도의 스레드라고 생각하면 됨
         */
        CoroutineScope(Dispatchers.Main).launch {
            val users = CoroutineScope(Dispatchers.IO).async {
                db.userDao().getAll()
            }.await()

            for (user in users) {
                userList += "이름 : ${user.name}, 나이 : ${user.age}, 전화번호 : ${user.phone}\n"
            }

            binding.tvUser.text = userList
        }
    }
}