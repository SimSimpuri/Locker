package com.example.locker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ScreenOffReciver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when{
            intent?.action == Intent.ACTION_SCREEN_OFF ->{
                Log.d("ScreenOffReciver", "잠금화면이 꺼졌습니다.")
                Toast.makeText(context, "잠금화면이 종료되었습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}