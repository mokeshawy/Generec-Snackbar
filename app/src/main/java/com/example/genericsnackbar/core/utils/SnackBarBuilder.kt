package com.example.genericsnackbar.core.utils

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import com.example.genericsnackbar.databinding.GenerecSnacbarLayoutBinding
import com.google.android.material.snackbar.Snackbar

class SnackBarBuilder {

    lateinit var binding: GenerecSnacbarLayoutBinding

    private var message: Int? = null
    private var startIcon: Int? = null
    private var endIcon: Int? = null
    private var duration: Int? = null
    private var startActionBtn: (View) -> Unit = {}
    private var endActionBtn: (View) -> Unit = {}

    fun build(activity: Activity, view: View): Snackbar {
        val inflater = activity.layoutInflater
        binding = GenerecSnacbarLayoutBinding.inflate(inflater)

        val snackBar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)

        message?.let { binding.message.text = activity.resources.getString(it) }

        initializeStartActionBtn(snackBar)
        startIcon?.let { initializeStartIcon(it) }

        initializeEndActionBtn(snackBar)
        endIcon?.let { initializeEndIcon(it) }

        snackBar.setSnackBarView()
        return snackBar
    }

    fun setMessage(message: Int): SnackBarBuilder {
        this.message = message
        return this
    }

    fun setStartActionBtn(startActionBtn: (View) -> Unit = {}): SnackBarBuilder {
        this.startActionBtn = startActionBtn
        return this
    }

    private fun initializeStartActionBtn(snackBar: Snackbar) {
        binding.startActionBtn.setOnClickListener {
            startActionBtn(it)
            snackBar.dismiss()
        }
    }

    fun setStartIcon(startIcon: Int?): SnackBarBuilder {
        this.startIcon = startIcon
        return this
    }

    private fun initializeStartIcon(startIcon: Int?) {
        startIcon?.let {
            binding.apply {
                startActionBtn.visibility = View.VISIBLE
                binding.startActionBtn.setImageResource(it)
            }
        }
    }

    fun setEndActionBtn(endActionBtn: (View) -> Unit = {}): SnackBarBuilder {
        this.endActionBtn = endActionBtn
        return this
    }

    private fun initializeEndActionBtn(snackBar: Snackbar) {
        binding.endActionBtn.setOnClickListener {
            endActionBtn(it)
            snackBar.dismiss()
        }
    }

    fun setEndIcon(endIcon: Int): SnackBarBuilder {
        this.endIcon = endIcon
        return this
    }

    private fun initializeEndIcon(endIcon: Int?) {
        endIcon?.let {
            binding.apply {
                endActionBtn.visibility = View.VISIBLE
                endActionBtn.setImageResource(it)
            }
        }
    }

    fun setDuration(duration: Int): SnackBarBuilder {
        this.duration = duration
        return this
    }

    private fun Snackbar.setSnackBarView() {
        val snackBarLayout = view as? Snackbar.SnackbarLayout
        snackBarLayout?.apply {
            setBackgroundColor(Color.TRANSPARENT)
            setPadding(0, 0, 0, 0)
            view.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            addView(binding.root, 0)
        }
    }
}