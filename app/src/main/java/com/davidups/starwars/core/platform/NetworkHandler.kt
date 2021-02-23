package com.davidups.starwars.core.platform

import android.content.Context
import com.davidups.starwars.core.extensions.checkNetworkState

class NetworkHandler
(private val context: Context) {
    val isConnected get() = context.checkNetworkState()
}
