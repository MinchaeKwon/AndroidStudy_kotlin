package com.example.androidstudy_kotlin.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // 2. View Binding, Data Binding + ViewModel
    private var num1 = 0

    fun increase1() {
        num1++
    }

    fun getNum1(): String {
        return num1.toString();
    }
//    fun getNum1(): String = num.toString()
//    fun getNum1() = num.toString()


    // 3. View Binding, Data Binding + ViewModel + LiveData
    private var num2 = MutableLiveData<Int>()
    // private var num2 = MutableLiveData<Int>(0);

    init {
        num2.value = 0
    }

    fun increase2() {
        num2.value = num2.value?.plus(1) // num2가 null이면 plus() 메소드호출이 무시되고 아니면 메소드 정상 실행
    }

    fun getNum2(): MutableLiveData<Int> {
        return num2
    }
}