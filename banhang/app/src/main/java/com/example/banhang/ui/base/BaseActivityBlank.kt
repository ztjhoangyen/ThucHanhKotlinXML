package com.example.banhang.ui.base

import android.view.View
import androidx.appcompat.app.AppCompatActivity
//Mặc định, các lớp, phương thức và thuộc tính trong Kotlin là final, tức là không thể kế thừa hoặc ghi đè, trừ khi chúng được đánh dấu là open
open class BaseActivityBlank : AppCompatActivity() {
//  View là lớp cơ sở cho tất cả các thành phần giao diện người dùng (UI) trong Android, như Button, TextView, EditText, v.v.
    fun back(view: View){
//        dừng activity hiện tại, và nếu có activity trước thì quay lại
        finish()
    }
}