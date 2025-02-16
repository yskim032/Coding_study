package com.example.chat002

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chat002.databinding.ActivitySignUpBinding

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class SingUpActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignUpBinding
    lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        // 부모 클래스의 onCreate를 먼저 호출
        super.onCreate(savedInstanceState)
        
        // 우리가 만든 화면 도면(XML)을 실제 화면으로 만들어볼게요!
        // layoutInflater는 도면을 보고 진짜 화면을 만드는 도구예요
        // 이렇게 하면 우리가 만든 버튼이나 글씨를 화면에서 볼 수 있어요
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        // 이제 우리가 만든 화면을 화면에 표시해볼게요
        // binding.root는 우리가 만든 화면의 루트 뷰입니다
        // 이 뷰가 화면에 표시되면 우리가 만든 모든 버튼이나 글씨가 화면에 보이게 됩니다
        setContentView(binding.root)

    
        mAuth = Firebase.auth

        mDbRef = Firebase.database.reference

        binding.signUpBtn.setOnClickListener {

            val name = binding.nameEdit.text.toString().trim()
            val email = binding.emailEdit.text.toString().trim()
            val password = binding.passwordEdit.text.toString().trim()

            signUp(email, password)
        }

    }

    private fun signUp(email: String, password: String) {

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //성공시

                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(this@SingUpActivity, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    //실패시
                    Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uld: String){
        mDbRef.child("user").child(uld).setValue(User(name, email,uld))
    }

}