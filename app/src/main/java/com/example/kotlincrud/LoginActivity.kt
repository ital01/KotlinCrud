package com.example.kotlincrud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private val dbService = DatabaseService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextUser: EditText = findViewById(R.id.userTextInput)
        val editTextPassword: EditText = findViewById(R.id.passwordTextInput)
        val buttonLogin: Button = findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val user = editTextUser.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val hasEmptyInput = user.isEmpty() || password.isEmpty()
            if (hasEmptyInput) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                dbService.login(user, password, fun(success: Boolean) {
                    onCreateCallback(success)
                    editTextUser.text.clear()
                    editTextPassword.text.clear()
                })
            }
        }
    }

    private fun onCreateCallback(success: Boolean) {
        if (success) {
            Toast.makeText(this, "Bem vindo de volta!", Toast.LENGTH_SHORT).show()
            val registerActivity = Intent(this, RegisterActivity::class.java)
            startActivity(registerActivity)
        } else {
            Toast.makeText(this, "Erro ao entrar, tente novamente", Toast.LENGTH_SHORT).show()
        }
    }
}
