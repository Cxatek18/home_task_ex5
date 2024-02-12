package com.team.users_informator.domain

import androidx.lifecycle.LiveData

interface UserRepository {

    fun getUserInfo(userName: String): User

    fun editUser(user: User)

    fun getListUser(): LiveData<List<User>>

    fun editImageUser(userName: String, image: Int)
}