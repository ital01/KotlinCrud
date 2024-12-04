package com.example.kotlincrud

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var editTextUser: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    private val dbService = DatabaseService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextUser = findViewById(R.id.userTextInput)
        editTextPassword = findViewById(R.id.passwordTextInput)
        buttonLogin = findViewById(R.id.buttonLogin)

        buttonLogin.setOnClickListener {
            val user = editTextUser.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            val hasEmptyInput = user.isEmpty() || password.isEmpty()
            if (hasEmptyInput) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                dbService.login(user, password, fun(success: Boolean) {
                    onCreateCallback(success)
                })
            }
        }
    }

    private fun onCreateCallback(success: Boolean) {
        if (success) {
            Toast.makeText(this, "Bem vindo de volta!", Toast.LENGTH_SHORT).show()
            clearInputs()
            val registerActivity = Intent(this, RegisterActivity::class.java)
            startActivity(registerActivity)
        } else {
            Toast.makeText(this, "Erro ao entrar, tente novamente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearInputs() {
        editTextUser.text.clear()
        editTextPassword.text.clear()
    }
}
