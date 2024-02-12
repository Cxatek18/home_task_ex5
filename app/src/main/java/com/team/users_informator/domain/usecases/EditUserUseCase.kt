package com.team.users_informator.domain.usecases

import com.team.users_informator.domain.User
import com.team.users_informator.domain.UserRepository

class EditUserUseCase(private val userRepository: UserRepository) {

    fun editUser(user: User) {
        userRepository.editUser(user)
    }
}