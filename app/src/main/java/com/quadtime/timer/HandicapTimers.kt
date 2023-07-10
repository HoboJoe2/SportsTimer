package com.quadtime.timer

import android.content.SharedPreferences
import android.widget.TextView
import androidx.preference.PreferenceManager
import com.quadtime.timer.constants.FlagNotification
import com.quadtime.timer.constants.MillisecondsPerMinute
import com.quadtime.timer.constants.defHandicapLength
import com.quadtime.timer.constants.timeFormatter
import kotlin.math.max

class HandicapTimers(inputAlert: Alert, inputContext: MainActivity) {
    private var timerBase: Long = System.currentTimeMillis()
    private val siren: Alert = inputAlert
    private val notificationText: String = inputContext.getString(R.string.notification_flag_desc)
    private var timerDuration: Long
    private val flagChronometer: TextView = inputContext.findViewById(R.id.flagCountdown)
    private var flagRunning = true

    init {
        val myPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(inputContext)

        timerDuration =
            try {
                (myPref.getString(inputContext.getString(R.string.handicap_length_key), "$defHandicapLength")?.toInt()
                    ?: defHandicapLength) * MillisecondsPerMinute
            }catch(e: NumberFormatException){
                defHandicapLength* MillisecondsPerMinute
            }
        //whenever timerBase is set from an external source, it does not include timerDuration
        timerBase += timerDuration

    }
}
