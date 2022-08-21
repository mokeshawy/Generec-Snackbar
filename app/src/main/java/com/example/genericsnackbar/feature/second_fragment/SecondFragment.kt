package com.example.genericsnackbar.feature.second_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.genericsnackbar.R
import com.example.genericsnackbar.core.utils.SnackBarBuilder
import com.example.genericsnackbar.databinding.SecondFragmentBinding

class SecondFragment : Fragment() {

    lateinit var binding: SecondFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SecondFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.secondFragmentBtn.setOnClickListener { navigateToNextScreen() }
        setOnShowSnackBarClicked()
    }

    private fun navigateToNextScreen() {
        findNavController().navigate(R.id.actionSecondFragmentToThirdFragment)
    }

    private fun setOnShowSnackBarClicked() {
        binding.showSnackBarBtn.setOnClickListener { handleSnackBarShowing() }
    }

    private fun handleSnackBarShowing() {
        SnackBarBuilder()
            .setMessage(R.string.showSnackOnSecondScreen)
            .setEndIcon(R.drawable.ic_vector_end_action)
            .setEndActionBtn {
                showToast()
            }.build(requireActivity(), binding.root)
            .show()
    }

    private fun showToast() {
        Toast.makeText(
            requireActivity(),
            resources.getString(R.string.showSnackOnSecondScreen),
            Toast.LENGTH_SHORT
        ).show()
    }
}