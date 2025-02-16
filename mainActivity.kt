package com.example.chat002

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat002.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

// MainActivity는 채팅 앱의 메인 화면을 담당하는 액티비티입니다.
// 주요 기능:
// 1. 사용자 목록을 보여주는 RecyclerView 구현 
// 2. Firebase 인증 및 실시간 데이터베이스 연동
// 3. 로그아웃 메뉴 제공
class MainActivity : AppCompatActivity() {

    lateinit var  binding:ActivityMainBinding
    lateinit var adapter: UserAdapter5

    // mAuth: Firebase 인증 기능을 사용하기 위한 객체입니다. 로그인/로그아웃 등의 인증 작업에 사용됩니다.
    private lateinit var mAuth: FirebaseAuth
    // mDbRef: Firebase 실시간 데이터베이스의 참조 객체입니다. 데이터베이스 읽기/쓰기 작업에 사용됩니다.
    private lateinit var mDbRef: DatabaseReference

    // userList: 사용자 목록을 저장하는 ArrayList입니다. 데이터베이스에서 가져온 사용자 정보를 저장하고 화면에 표시합니다.
    private lateinit var userList:ArrayList<User>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth= Firebase.auth
        mDbRef=Firebase.database.reference
        userList=ArrayList()

        adapter= UserAdapter(this, userList)

        binding.userRecylerView.layoutManager = LinearLayoutManager(this)
        binding.userRecylerView.adapter=adapter

        mDbRef.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(postSnapshot in snapshot.children){

                    val currentUser = postSnapshot.getValue(User::class.java)

                    if(mAuth.currentUser?.uid != currentUser?.uld){
                        userList.add(currentUser!!)
                    }
            }

                adapter.notifyDataSetChanged()
        }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.log_out){
            mAuth.signOut()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return true
        }

        return true

    }

}