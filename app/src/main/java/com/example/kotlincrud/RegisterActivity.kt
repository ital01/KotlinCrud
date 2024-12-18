package com.example.kotlincrud

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private val dbService = DatabaseService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val rootView = window.decorView.findViewById<View>(android.R.id.content)
        val appName: TextView = findViewById(R.id.register_app_name)
        val appLogo: ImageView = findViewById(R.id.register_app_logo)
        val registerTitle: TextView = findViewById(R.id.register_title)
        val registerSubTitle: TextView = findViewById(R.id.register_subtitle)
        val editTextName: EditText = findViewById(R.id.register_name_input)
        val editTextEmail: EditText = findViewById(R.id.register_email_input)
        val editTextPhone: EditText = findViewById(R.id.register_phone_input)
        val editTextCountry: EditText = findViewById(R.id.register_country_input)
        val editTextLanguage: EditText = findViewById(R.id.register_language_input)
        val editTextPersonalExperience: EditText = findViewById(R.id.register_personal_experience_input)

        val buttonCreate: Button = findViewById(R.id.buttonCreate)

        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)

            val screenHeight = rootView.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight > screenHeight * 0.15) {
                appName.visibility = View.GONE
                appLogo.visibility = View.GONE
                registerTitle.visibility = View.GONE
                registerSubTitle.visibility = View.GONE
            } else {
                appName.visibility = View.VISIBLE
                appLogo.visibility = View.VISIBLE
                registerTitle.visibility = View.VISIBLE
                registerSubTitle.visibility = View.VISIBLE
            }
        }

        buttonCreate.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val phone = editTextPhone.text.toString().trim()
            val country = editTextCountry.text.toString().trim()
            val language = editTextLanguage.text.toString().trim()
            val personalExperience = editTextPersonalExperience.text.toString().trim()

            val hasEmptyInput = name.isEmpty() ||
                    email.isEmpty() ||
                    phone.isEmpty() ||
                    country.isEmpty() ||
                    language.isEmpty() ||
                    personalExperience.isEmpty()

            if (hasEmptyInput) {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show()
            } else {
                val user = User(
                    name = name,
                    email = email,
                    phone = phone,
                    country = country,
                    language = language,
                    personalExperience = personalExperience
                )
                dbService.createUser(user, fun(success: Boolean) {
                    onCreateCallback(success)
                    editTextName.text.clear()
                    editTextEmail.text.clear()
                    editTextPhone.text.clear()
                    editTextCountry.text.clear()
                    editTextLanguage.text.clear()
                    editTextPersonalExperience.text.clear()
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
    }
}