package com.example.flutter_method_channel

import io.flutter.embedding.android.FlutterActivity

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.NonNull
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.plugin.platform.PlatformViewsController


import com.example.flutter_method_channel.PhoneCallChannelCtrl

class MainActivity: FlutterActivity() {

    //////////////////////////////////////////////////////////////////////////////
    ///////////////  main
    //////////////////////////////////////////////////////////////////////////////
    @kotlin.ExperimentalStdlibApi
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        //pulginを設定する
        GeneratedPluginRegistrant.registerWith(flutterEngine)

        //channelを設定する
        PhoneCallChannelCtrl.setup(this, flutterEngine.dartExecutor.binaryMessenger)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
