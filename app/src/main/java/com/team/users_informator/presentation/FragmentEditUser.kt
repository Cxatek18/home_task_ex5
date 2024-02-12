package com.team.users_informator.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.team.users_informator.R
import com.team.users_informator.databinding.FragmentEditUserBinding
import com.team.users_informator.presentation.view_models.EditUserViewModel
import kotlin.random.Random

class FragmentEditUser : Fragment() {

    private var _binding: FragmentEditUserBinding? = null
    private val binding: FragmentEditUserBinding
        get() = _binding ?: throw RuntimeException("FragmentEditUserBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[EditUserViewModel::class.java]
    }

    private var userNameArgs: String? = null
    private var userImage: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser(userNameArgs ?: "")
        observeViewModel()
        onClickEditUser()
        onClickEditImage()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.oldUser.observe(viewLifecycleOwner) {
            with(binding) {
                etUserSurname.setText(it.surname)
                etUserPhone.setText(it.phone)
                userImageView.setImageResource(it.image)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireActivity(),
                it,
                Toast.LENGTH_LONG
            ).show()
        }

        viewModel.userIsEditImage.observe(viewLifecycleOwner) {
            with(binding) {
                etUserSurname.setText(it.surname)
                etUserPhone.setText(it.phone)
                userImageView.setImageResource(it.image)
            }
            requireActivity().supportFragmentManager.beginTransaction()
                .detach(this)
                .attach(this)
                .commit()
        }
    }

    private fun onClickEditUser() {
        binding.buttonChangeUser.setOnClickListener {
            with(binding) {
                userNameArgs?.let { it1 ->
                    viewModel.editUser(
                        it1,
                        etUserSurname.text.toString(),
                        etUserPhone.text.toString(),
                    )
                }
            }
            requireActivity().supportFragmentManager.popBackStack(
                FragmentMain.TAG_FRAGMENT, 0
            )
        }
    }

    private fun onClickEditImage() {
        binding.buttonChangeImage.setOnClickListener {
            userNameArgs?.let { it1 ->
                viewModel.editImageUser(
                    it1, formingListImages()[Random.nextInt(0, 4)]
                )
            }
        }
    }

    private fun formingListImages(): List<Int> {
        return listOf(
            R.drawable.user1,
            R.drawable.user2,
            R.drawable.user3,
            R.drawable.user4
        )
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(EXTRA_USER_NAME)) {
            throw RuntimeException("argument user_name is not")
        }
        userNameArgs = args.getString(EXTRA_USER_NAME)
    }

    companion object {

        const val TAG_FRAGMENT = "edit_text"
        private const val EXTRA_USER_NAME = "user_name"

        fun newInstance(userName: String): FragmentEditUser {
            return FragmentEditUser().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_USER_NAME, userName)
                }
            }
        }
    }
}