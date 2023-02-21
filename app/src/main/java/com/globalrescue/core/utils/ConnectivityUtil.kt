package com.globalrescue.core.utils

import java.net.InetAddress

class ConnectivityUtil {

    fun isInternetAvailable(): Boolean {
        return try {
            val address: InetAddress = InetAddress.getByName("google.com")
            //You can replace it with your name
            !address.equals("")
        } catch (e: Exception) {
            false
        }
    }
}