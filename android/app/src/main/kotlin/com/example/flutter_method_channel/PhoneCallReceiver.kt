package com.example.flutter_method_channel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle

import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager

import java.io.File
import java.io.FileNotFoundException

import android.util.Log


class PhoneCallReceiver : BroadcastReceiver()
    {

    //////////////////////////////////////////////////////////////////////////////
    ///////////////  property
    //////////////////////////////////////////////////////////////////////////////
    companion object {
        private const val TAG = "TEST"
        private const val MSG = "PhoneCallReceiver : @"
    }
    //////////////////////////////////////////////////////////////////////////////
    ///////////////  main
    //////////////////////////////////////////////////////////////////////////////

    override fun onReceive(context: Context, intent: Intent?) {
        Log.i(TAG, "${MSG}onReceive ================================")

        val bundle: Bundle? = intent?.getExtras()
        if (bundle == null) {
            return
        }

        if(!checkPhoneState(bundle)) {
            return
        }

        val phoneNumber: String = getCallNumber(bundle)
        if (phoneNumber.equals("")) {
            return
        }


//      Q1 : What happens the following NG Case? and What can I do?
//      OK Case : this running app can make PhoneCallReceiver "PhoneCallChannelCtrl.instance?.sendToFLutter(phoneNumber)"
//      NG Case : this terminated app cann't do it. (this app can just is started.

//      Detail:
//      This app is started by @booApp and then,
//      MainActivity@configureFlutterEngine set MethodChannel (which is set PhoneCallChannelCtrl@setup).
        booApp(context, phoneNumber)
        PhoneCallChannelCtrl.instance?.sendToFLutter(phoneNumber)
    }

    //////////////////////////////////////////////////////////////////////////////
    ///////////////  detail
    //////////////////////////////////////////////////////////////////////////////
    fun checkPhoneState (bundle: Bundle) : Boolean {
        val state: String? = bundle.getString(TelephonyManager.EXTRA_STATE)
        if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
            return true
        }
        return false
    }

    fun getCallNumber (bundle: Bundle) : String {
        val phoneNumber: String? = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)

        if (phoneNumber == null) {
            return ""
        }

        return phoneNumber
    }

    fun booApp(context: Context, phoneNumber: String) {

        val startIntent: Intent? = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName())
        if (startIntent != null) {
            Log.i(TAG, "${MSG}booApp : phoneNumber = ${phoneNumber}")
            startIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED)

//          Q2 : How do fluter side catch this intent?
            startIntent.putExtra("phoneNumber", phoneNumber)

            context.startActivity(startIntent)
        }
    }


}