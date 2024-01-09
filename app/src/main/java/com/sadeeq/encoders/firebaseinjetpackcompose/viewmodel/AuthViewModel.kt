package com.sadeeq.encoders.firebaseinjetpackcompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadeeq.encoders.firebaseinjetpackcompose.repository.AuthRepository
import com.sadeeq.encoders.firebaseinjetpackcompose.selead.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _signUpResult = MutableLiveData<AuthResult>()
    val signUpResult: LiveData<AuthResult> get() = _signUpResult

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _signUpResult.value = authRepository.signUp(email, password)
        }
    }

    private val _loginResult = MutableLiveData<AuthResult>()
    val loginResult: LiveData<AuthResult> get() = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = authRepository.login(email, password)
        }
    }


    private val _isUserAuthenticated = MutableLiveData<Boolean>()
    val isUserAuthenticated: LiveData<Boolean> get() = _isUserAuthenticated

    private fun updateAuthenticationState() {
        _isUserAuthenticated.value = authRepository.isUserAuthenticated()
    }
}
