package com.davidups.starwars.core.extensions

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.davidups.skell.core.utils.InfoAlertDialog

inline fun Fragment.showInfoAlertDialog(func: InfoAlertDialog.() -> Unit): AlertDialog =
    InfoAlertDialog(this.requireContext()).apply {
        func()
    }.create()
