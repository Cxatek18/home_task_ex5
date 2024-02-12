package com.team.users_informator.domain.usecases

import com.team.users_informator.domain.User
import com.team.users_informator.domain.UserRepository

class GetUserUseCase(private val userRepository: UserRepository) {

    fun getUserInfo(userName: String): User {
        return userRepository.getUserInfo(userName)
    }
}