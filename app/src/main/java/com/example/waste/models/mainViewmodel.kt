package com.example.waste.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.waste.data.STATUS
import com.example.waste.data.UserData
import com.example.waste.data.product
import com.example.waste.userdb
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class userItemsUiState(val list: MutableList<UserData>)
data class productListUiState(val list: MutableList<product> = mutableListOf())
class mainViewmodel: ViewModel() {

    public val user = MutableStateFlow(Firebase.auth.currentUser)
    val _user=user.asStateFlow()
    val userDB= userdb












    fun updateUi(userT:FirebaseUser?){

        user.value=userT



    }












}