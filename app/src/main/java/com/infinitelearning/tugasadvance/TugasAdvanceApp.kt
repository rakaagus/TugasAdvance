package com.infinitelearning.tugasadvance

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.infinitelearning.tugasadvance.utils.NotificationKeys.RMNDR_NOTI_CHNNL_ID
import com.infinitelearning.tugasadvance.utils.NotificationKeys.RMNDR_NOTI_CHNNL_NAME
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TugasAdvanceApp: Application(){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val notification = NotificationChannel(
            RMNDR_NOTI_CHNNL_ID,
            RMNDR_NOTI_CHNNL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notification)
    }
}