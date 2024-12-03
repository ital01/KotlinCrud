package com.example.kotlincrud
import android.util.Log

class DatabaseHelper {

    fun createUser(user: User, onComplete: (Boolean) -> Unit) {
        Log.d("UserCreation", "Created user: ${user.name}")
        onComplete(true)
    }

    fun deleteUser(user: User, onComplete: (Boolean) -> Unit) {
        Log.d("UserDeletion", "Deleted user: ${user.name}")
        onComplete(true)
    }

    fun editUser(user: User, onComplete: (Boolean) -> Unit) {
        Log.d("UserEdition", "Edited user: ${user.name}")
        onComplete(true)
    }

    fun listUsers() {

    }

}
