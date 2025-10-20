package com.asmit404.retrofit1

data class UserResponse(val data: Data)

data class Data(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)

data class UserData(val name: String, val email: String)
data class UserCreationResponse(val id: String, val message: String)