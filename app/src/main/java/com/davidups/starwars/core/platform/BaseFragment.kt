package com.davidups.starwars.core.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.davidups.starwars.core.navigation.MainActivity
import kotlinx.android.synthetic.main.navigation_activity.*

abstract class BaseFragment(layout: Int) : Fragment() {

    val layouID = layout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(layouID, container, false)

    internal fun showProgress() = progressStatus(View.VISIBLE)

    internal fun hideProgress() = progressStatus(View.GONE)

    private fun progressStatus(viewStatus: Int) =
        with(activity) {
            if (this is MainActivity) {
                this.progress.visibility = viewStatus
            }
        }

    internal fun showSpinner(show: Boolean) {
        when (show) {
            true -> showProgress()
            false -> hideProgress()
        }
    }
}
