package com.example.kotlincrud

class DatabaseService {
    private val dbHelper = DatabaseHelper()

    fun createUser(user: User, onComplete: (Boolean) -> Unit) {
        try {
            dbHelper.createUser(user)
            onComplete(true)
        } catch (error: Exception) {
            onComplete(false)
        }
    }

    fun deleteUser(user: User, onComplete: (Boolean) -> Unit) {
        try {
            dbHelper.deleteUser(user)
            onComplete(true)
        } catch (error: Exception) {
            onComplete(false)
        }
    }

    fun editUser(user: User, onComplete: (Boolean) -> Unit) {
        try {
            dbHelper.editUser(user)
            onComplete(true)
        } catch (error: Exception) {
            onComplete(false)
        }
    }

    fun login(user: String, password: String, onComplete: (Boolean) -> Unit) {
        val loginResult = dbHelper.login(user, password)
        onComplete(loginResult)
    }
}
