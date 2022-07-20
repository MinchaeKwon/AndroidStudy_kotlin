package com.example.androidstudy_kotlin.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidstudy_kotlin.AppDatabase
import com.example.androidstudy_kotlin.data.User
import com.example.androidstudy_kotlin.databinding.FragmentRoomBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

// Room + Coroutine 사용
class RoomFragment : Fragment() {

    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomBinding.inflate(inflater, container, false)
        return binding.root

//        return inflater.inflate(R.layout.fragment_room, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = activity?.let {
            AppDatabase.getInstance(it.applicationContext)
        }!!

        getUserList()

        binding.btnSaveUser.setOnClickListener {
            addUser()
            getUserList()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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