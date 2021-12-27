package com.example.flutter_method_channel


import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodChannel
import android.util.Log

class PhoneCallChannelCtrl private constructor(private val channel: MethodChannel) {

    companion object {
        var instance: PhoneCallChannelCtrl? = null
        private const val CHANNEL = "test.channel"
        private const val METHOD_1 = "testChannel"

        fun setup(rootScreen: FlutterActivity, messenger: BinaryMessenger) {
            if (instance == null) {
                val _channel: MethodChannel = MethodChannel(messenger, CHANNEL)
                instance = PhoneCallChannelCtrl(_channel)
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    ///////////////  main
    //////////////////////////////////////////////////////////////////////////////
    fun sendToFLutter(phoneNumber: String) {
        channel.invokeMethod(METHOD_1, phoneNumber)
    }
}