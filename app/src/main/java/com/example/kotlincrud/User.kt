package com.example.kotlincrud

data class User (
    var name: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var password: String? = null,
    var country: String? = null,
    var language: String? = null,
    var personalExperience: String? = null
)