        binding.signUpBtn.setOnClickListener {
            // Intent는 앱의 화면(액티비티) 간에 이동할 때 사용하는 전달자 역할을 하는 객체입니다
            // 현재 화면(LoginActivity)에서 회원가입 화면(SignUpActivity)으로 이동하라는 의도를 담고 있습니다
            val intent: Intent = Intent (this@LoginActivity, SignUpActivity::class.java)
            // startActivity를 호출하여 실제로 화면을 전환합니다
            startActivity(intent)