package com.sadeeq.encoders.firebaseinjetpackcompose.selead

import com.sadeeq.encoders.firebaseinjetpackcompose.model.User

sealed class AuthResult {
    data class Success(val user: User) : AuthResult()
    data class Error(val exception: Exception) : AuthResult()
}