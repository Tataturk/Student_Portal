package com.example.studentportal

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portal (
    val title: String,
    val uri: String
    ) : Parcelable