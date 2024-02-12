package com.team.users_informator.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.team.users_informator.data.UserRepositoryImpl
import com.team.users_informator.domain.User
import com.team.users_informator.domain.usecases.EditImageUserUseCase
import com.team.users_informator.domain.usecases.EditUserUseCase
import com.team.users_informator.domain.usecases.GetUserUseCase

class EditUserViewModel : ViewModel() {

    private val repository = UserRepositoryImpl
    private val editUserListUseCase = EditUserUseCase(repository)
    private val getUserUseCase = GetUserUseCase(repository)
    private val editImageUserUseCase = EditImageUserUseCase(repository)

    private var _oldUser = MutableLiveData<User>()
    val oldUser: LiveData<User>
        get() = _oldUser

    private var _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private var _userIsEditImage = MutableLiveData<User>()
    val userIsEditImage: LiveData<User>
        get() = _userIsEditImage

    fun editUser(name: String, surname: String, phone: String) {
        val user = _oldUser.value?.image?.let {
            User(
                it,
                name,
                surname,
                phone
            )
        }
        user?.let { editUserListUseCase.editUser(it) }
    }

    fun getUser(name: String) {
        try {
            _oldUser.value = getUserUseCase.getUserInfo(name)
        } catch (e: Exception) {
            _error.value = TEXT_ERROR
        }
    }

    fun editImageUser(name: String, image: Int) {
        try {
            editImageUserUseCase.editImageUser(name, image)
            _userIsEditImage.value = getUserUseCase.getUserInfo(name)
        } catch (e: Exception) {
            _error.value = TEXT_ERROR_IMAGE
        }

    }

    companion object {
        private const val TEXT_ERROR = "Пользователь не найден"
        private const val TEXT_ERROR_IMAGE = "Ошибка при изменение картинки"
    }
}