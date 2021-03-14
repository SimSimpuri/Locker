package com.example.locker

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.MultiSelectListPreference
import android.preference.PreferenceFragment
import android.preference.SwitchPreference


class MainActivity : AppCompatActivity() {

    val fragment = MyPreferenceFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager.beginTransaction().replace(R.id.preferemceContent, fragment).commit()
    }

    class MyPreferenceFragment: PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            addPreferencesFromResource(R.xml.pref)

            val categoryPref = findPreference("category") as MultiSelectListPreference
            categoryPref.summary = categoryPref.values.joinToString(", ")

            categoryPref.setOnPreferenceChangeListener { preference, newValue ->
                val newValuesSet = newValue as? HashSet<*>
                    ?: return@setOnPreferenceChangeListener true

                categoryPref.summary = newValue.joinToString(", ")

                true
            }

            val useLockScreenPref = findPreference("useLockScreen") as SwitchPreference

            useLockScreenPref.setOnPreferenceClickListener {
                when {
                    useLockScreenPref.isChecked -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            activity.startForegroundService(
                                Intent(
                                    activity,
                                    LockerScreenService::class.java
                                )
                            )
                        } else {
                            activity.startService(Intent(activity, LockerScreenService::class.java))
                        }
                    }
                    else -> activity.stopService(Intent(activity, LockerScreenService::class.java))
                }
                true
            }

            if (useLockScreenPref.isChecked) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    activity.startForegroundService(
                        Intent(
                            activity,
                            LockerScreenService::class.java
                        )
                    )
                } else {
                    activity.startService(Intent(activity, LockerScreenService::class.java))
                }

            }
        }
    }
}