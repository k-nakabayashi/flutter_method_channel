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

        booApp(context, phoneNumber)//

//        疑問 1
//        アプリ終了中の場合、
//        ここでPhoneCallChannelCtrlが設定されるので、
//        メソッドチャネルが使えるのでは？
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

//          疑問 2
//          MainActivityのライフサイクルメソッドのどれかで、
//          intentを扱えれば、そのメソッドからメソッドチャネルが使えるのでしょうか？
            startIntent.putExtra("phoneNumber", phoneNumber)

            context.startActivity(startIntent)
        }
    }


}