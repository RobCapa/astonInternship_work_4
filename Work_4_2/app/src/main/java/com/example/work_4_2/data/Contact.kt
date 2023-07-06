package com.example.work_4_2.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val id: Int,
    val lastName: String,
    val firstName: String,
    val number: String,
    val avatarUrl: String,
): Parcelable
