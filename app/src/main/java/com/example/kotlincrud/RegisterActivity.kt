package com.example.kotlincrud

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private val dbService = DatabaseService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val editTextName: EditText = findViewById(R.id.nameTextInput)
        val editTextEmail: EditText = findViewById(R.id.emailTextInput)
        val editTextPhone: EditText = findViewById(R.id.phoneTextInput)
        val buttonCreate: Button = findViewById(R.id.buttonCreate)

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
                    editTextName.text.clear()
                    editTextEmail.text.clear()
                    editTextPhone.text.clear()
                })
            }
        }
    }

    private fun onCreateCallback(success: Boolean) {
        if (success) {
            Toast.makeText(this, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Erro ao criar usuário!", Toast.LENGTH_SHORT).show()
        }
    } }
