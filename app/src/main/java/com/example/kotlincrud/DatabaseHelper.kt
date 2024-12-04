package com.example.kotlincrud

import android.util.Log

class DatabaseHelper {

    fun createUser(user: User) {
        Log.d("UserCreation", "Created user: ${user.name}")
    }

    fun deleteUser(user: User) {
        Log.d("UserDeletion", "Deleted user: ${user.name}")
    }

    fun editUser(user: User) {
        Log.d("UserEdition", "Edited user: ${user.name}")
    }

    fun listUsers() {
        // Implementar listagem de usuários
    }

    fun login(user: String, password: String): Boolean {
        return try {
            Log.d("Login", "Login with user $user")
            true
        } catch (error: Error) {
            Log.e("LoginError", "Error logging in: ${error.message}")
            false
        }
    }
}
