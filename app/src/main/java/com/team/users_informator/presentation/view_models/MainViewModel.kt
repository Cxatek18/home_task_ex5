package com.team.users_informator.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.team.users_informator.data.UserRepositoryImpl
import com.team.users_informator.domain.User
import com.team.users_informator.domain.usecases.GetUserListUseCase

class MainViewModel : ViewModel() {

    private val repository = UserRepositoryImpl
    private val getUserListUseCase = GetUserListUseCase(repository)

    val listPhoneNumber: LiveData<List<User>>
        get() = getUserListUseCase.getListUser()
}