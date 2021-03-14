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
                when{
                    intent?.action == Intent.ACTION_SCREEN_OFF -> {
                        val intent = Intent(context, QuizActivity::class.java)

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        context?.startActivity(intent)
                    }
                }
            }
        }
    }
}