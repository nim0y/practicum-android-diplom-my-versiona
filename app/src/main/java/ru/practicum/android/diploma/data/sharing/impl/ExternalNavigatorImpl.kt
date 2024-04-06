package ru.practicum.android.diploma.data.sharing.impl

import android.content.Context
import android.content.Intent
import android.net.Uri
import ru.practicum.android.diploma.data.sharing.ExternalNavigator

class ExternalNavigatorImpl(private val context: Context) : ExternalNavigator {
    override fun sendEmail(email: String, subject: String) {
        Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK

            context.startActivity(this)
        }
    }

    override fun dialPhoneNumber(phoneNumber: String) {
        Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK

            context.startActivity(this)
        }
    }

    override fun shareVacancy(url: String) {
        Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, url)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK

            context.startActivity(this)
        }
    }
}
