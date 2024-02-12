package com.team.users_informator.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.team.users_informator.databinding.UserItemBinding
import com.team.users_informator.domain.User

class UserAdapter : ListAdapter<User, UserViewHolder>(
    UserDiffCallback()
) {

    var onClickUser: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        with(holder.binding) {
            with(user) {
                imgUser.setImageResource(user.image)
                nameUser.text = user.name
                surnameUser.text = user.surname
                phoneUser.text = user.phone
                holder.itemView.setOnClickListener {
                    onClickUser?.invoke(nameUser.text.toString())
                    true
                }
            }
        }
    }
}