package com.sadeeq.encoders.firebaseinjetpackcompose.repository

import com.google.firebase.auth.FirebaseAuth
import com.sadeeq.encoders.firebaseinjetpackcompose.model.User
import com.sadeeq.encoders.firebaseinjetpackcompose.selead.AuthResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepository @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun signUp(email: String, password: String): AuthResult {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            AuthResult.Success(User(result.user?.uid ?: "", email))
        } catch (e: Exception) {
            AuthResult.Error(e)
        }
    }

    suspend fun login(email: String, password: String): AuthResult {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            AuthResult.Success(User(result.user?.uid ?: "", email))
        } catch (e: Exception) {
            AuthResult.Error(e)
        }
    }

    fun isUserAuthenticated(): Boolean {
        return firebaseAuth.currentUser != null
    }
}
