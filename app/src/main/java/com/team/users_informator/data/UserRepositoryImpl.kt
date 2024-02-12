package com.team.users_informator.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.team.users_informator.R
import com.team.users_informator.domain.User
import com.team.users_informator.domain.UserRepository
import java.util.TreeSet

object UserRepositoryImpl : UserRepository {

    private val userList: TreeSet<User> = sortedSetOf<User>(
        { o1, o2 -> o1.name.compareTo(o2.name) }
    )
    private val userListLD = MutableLiveData<List<User>>()

    init {
        for (it in 1..4) {
            val item = User(
                R.drawable.icon_user,
                "Name $it",
                "Surname $it",
                "+7333333333$it"
            )
            userList.add(item)
        }
        updateList()
    }

    override fun getUserInfo(userName: String): User {
        return userList.find { it.name == userName } ?: throw RuntimeException(
            "User name with id $userName not found"
        )
    }

    override fun editUser(user: User) {
        val oldUser = getUserInfo(user.name)
        userList.remove(oldUser)
        userList.add(user)
        updateList()
    }

    override fun getListUser(): LiveData<List<User>> {
        return userListLD
    }

    override fun editImageUser(userName: String, image: Int) {
        getUserInfo(userName).image = image
        updateList()
    }

    private fun updateList() {
        userListLD.value = userList.toList()
    }
}