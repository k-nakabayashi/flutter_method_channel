package com.example.flutter_method_channel

import io.flutter.embedding.android.FlutterActivity

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.NonNull
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.plugin.platform.PlatformViewsController

import android.util.Log
import android.telephony.TelephonyManager

import com.example.flutter_method_channel.PhoneCallChannelCtrl

class MainActivity: FlutterActivity() {

    companion object {
        private const val TAG = "TEST"
        private const val MSG = "MainActivity : @"
    }

    //////////////////////////////////////////////////////////////////////////////
    ///////////////  main
    //////////////////////////////////////////////////////////////////////////////
    @kotlin.ExperimentalStdlibApi
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        //pulginを設定する
        GeneratedPluginRegistrant.registerWith(flutterEngine)

        //channelを設定する
        PhoneCallChannelCtrl.setup(this, flutterEngine.dartExecutor.binaryMessenger)

//        PhoneCallChannelCtrl.instance?.sendToFLutter(phoneNumber)

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//
//        val phoneNumber: String? = savedInstanceState?.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
//
//        if (phoneNumber == null) {
//            return
//        }
//
//        Log.i(TAG, "${MSG} ${phoneNumber} ================================")
//        PhoneCallChannelCtrl.instance?.sendToFLutter(phoneNumber)
    }


    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume()")

    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart()")
    }

}
