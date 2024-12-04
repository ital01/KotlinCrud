package com.example.kotlincrud

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var buttonCreate: Button

    private val dbService = DatabaseService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editTextName = findViewById(R.id.nameTextInput)
        editTextEmail = findViewById(R.id.emailTextInput)
        editTextPhone = findViewById(R.id.phoneTextInput)
        buttonCreate = findViewById(R.id.buttonCreate)

        buttonCreate.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val phone = editTextPhone.text.toString().trim()
            val hasEmptyInput = name.isEmpty() || email.isEmpty() || phone.isEmpty()
            if (hasEmptyInput) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                val user = User(name, email, phone)
                dbService.createUser(user, fun(success: Boolean) {
                    onCreateCallback(success)
                })
            }
        }
    }

    private fun onCreateCallback(success: Boolean) {
        if (success) {
            Toast.makeText(this, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show()
            clearInputs()
        } else {
            Toast.makeText(this, "Erro ao criar usuário!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearInputs() {
        editTextName.text.clear()
        editTextEmail.text.clear()
        editTextPhone.text.clear()
    }
}
