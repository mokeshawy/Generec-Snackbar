package com.example.genericsnackbar.feature.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.genericsnackbar.R
import com.example.genericsnackbar.core.utils.SnackBarBuilder
import com.example.genericsnackbar.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    lateinit var binding: HomeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeBtn.setOnClickListener { navigateToNextScreen() }
        setOnShowSnackBarClicked()
    }

    private fun navigateToNextScreen() {
        findNavController().navigate(R.id.actionHomeFragmentToSecondFragment)
    }

    private fun setOnShowSnackBarClicked() {
        binding.showSnackBarBtn.setOnClickListener { handleSnackBarShowing() }
    }

    private fun handleSnackBarShowing() {
        SnackBarBuilder()
            .setMessage(R.string.showSnackOnHomeScreen)
            .setStartIcon(R.drawable.ic_vector_start_action)
            .setStartActionBtn {
                showToast()
            }.show(requireActivity(), binding.root)
    }

    private fun showToast() {
        Toast.makeText(
            requireActivity(),
            resources.getString(R.string.showSnackOnHomeScreen),
            Toast.LENGTH_SHORT
        ).show()
    }
}