package com.example.chat002

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chat002.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

// LoginActivity는 사용자 로그인을 처리하는 액티비티입니다.
// 주요 기능:
// 1. 이메일/비밀번호 입력 UI 제공
// 2. Firebase Authentication을 통한 로그인 처리
// 3. 회원가입 화면으로 이동하는 기능 제공
class LoginActivity : AppCompatActivity() {

    // ActivityLoginBinding: 레이아웃 XML과 코드를 연결하는 바인딩 클래스입니다.
    // lateinit이 필요한 이유: binding은 onCreate()에서 초기화되기 때문에
    // 선언 시점에서는 초기화할 수 없습니다. lateinit을 사용하면 나중에 초기화가 가능합니다.
    lateinit var binding: ActivityLoginBinding

    // FirebaseAuth: Firebase 인증 기능을 사용하기 위한 객체입니다.
    // lateinit이 필요한 이유: mAuth는 onCreate()에서 Firebase.auth로 초기화되기 때문에
    // 선언 시점에서는 초기화할 수 없습니다. lateinit으로 나중에 안전하게 초기화합니다.
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        // 부모 클래스의 onCreate 메서드를 먼저 호출합니다
        super.onCreate(savedInstanceState)
        // XML 레이아웃 파일을 코드와 연결하는 바인딩 객체를 생성합니다
        binding = ActivityLoginBinding.inflate(layoutInflater)
        // 생성된 바인딩의 루트 뷰를 화면에 표시합니다
        setContentView(binding.root)

        //인증초기화
        mAuth = Firebase.auth

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEdit.text.toString()
            val password =binding.passwordEdit.text.toString()

            login(email,password)
        }


        // 회원가입 버튼을 클릭했을 때 실행되는 코드입니다
        // binding.signUpBtn.setOnClickListener { } : 회원가입 버튼이 클릭되면 중괄호 안의 코드가 실행됩니다
        // val intent = Intent(...) : 회원가입 화면(SignUpActivity)으로 이동하기 위한 Intent 객체를 생성합니다
        // startActivity(intent) : 생성한 Intent로 새로운 화면을 시작합니다
        binding.signUpBtn.setOnClickListener {
            // Intent는 안드로이드에서 화면 전환이나 작업 요청을 나타내는 객체입니다.
            // 여기서는 현재 화면(LoginActivity)에서 회원가입 화면(SignUpActivity)으로 전환하기 위해 사용됩니다.
            // this@LoginActivity: 현재 액티비티를 나타냅니다.
            // SignUpActivity::class.java: 전환하고자 하는 대상 액티비티를 지정합니다.
            val intent: Intent = Intent (this@LoginActivity, SingUpActivity::class.java)
            // startActivity()를 호출하여 실제로 화면을 전환합니다.
            startActivity(intent)
        }

    }

    private fun login (email : String, password : String) {
        // mAuth.signInWithEmailAndPassword(email, password) : FirebaseAuth 인증 객체를 사용하여 이메일과 비밀번호를 사용하여 로그인 시도
        // .addOnCompleteListener(this) : 로그인 작업이 완료되면 실행되는 리스너를 추가
        // if (task.isSuccessful) : 로그인 작업이 성공했는지 확인
        // val intent: Intent = Intent(this@LoginActivity, MainActivity::class.java) : 로그인 성공 시 메인 화면으로 이동하기 위한 Intent 객체 생성
        // startActivity(intent) : 생성한 Intent로 새로운 화면을 시작합니다
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // 로그인 성공 시 사용자에게 먼저 알림을 보여줍니다
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                    
                    // MainActivity로 이동하기 위한 Intent를 생성합니다
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    // 이전 액티비티들을 모두 제거하고 새로운 태스크를 시작합니다
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    
                    // 현재 로그인 화면을 종료합니다
                    finish()
                } else{
                    Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                    Log.d("Login", "Error: ${task.exception}")

                }
            }
    }

}