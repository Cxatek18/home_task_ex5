package com.team.users_informator.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.team.users_informator.R
import com.team.users_informator.databinding.FragmentMainBinding
import com.team.users_informator.presentation.adapters.UserAdapter
import com.team.users_informator.presentation.view_models.MainViewModel

class FragmentMain : Fragment() {

    private lateinit var adapter: UserAdapter

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserAdapter()
        installationUserListToAdapter(adapter)
        binding.rvMainFragment.adapter = adapter
        startFragmentEditUser(adapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun installationUserListToAdapter(adapter: UserAdapter) {
        viewModel.listPhoneNumber.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun startFragmentEditUser(adapter: UserAdapter) {
        adapter.onClickUser = {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, FragmentEditUser.newInstance(it))
                .addToBackStack(FragmentEditUser.TAG_FRAGMENT)
                .commit()
        }
    }

    companion object {

        const val TAG_FRAGMENT = "main_fragment"

        fun newInstance(): FragmentMain {
            return FragmentMain()
        }
    }
}