package com.team.users_informator.domain.usecases

import com.team.users_informator.domain.UserRepository

class EditImageUserUseCase(private val userRepository: UserRepository) {

    fun editImageUser(userName: String, image: Int) {
        userRepository.editImageUser(userName, image)
    }
}