package com.team.users_informator.domain.usecases

import androidx.lifecycle.LiveData
import com.team.users_informator.domain.User
import com.team.users_informator.domain.UserRepository

class GetUserListUseCase(private val userRepository: UserRepository) {

    fun getListUser(): LiveData<List<User>> {
        return userRepository.getListUser()
    }
}