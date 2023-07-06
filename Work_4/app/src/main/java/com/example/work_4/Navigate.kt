package com.example.work_4

import android.os.Bundle

interface Navigate {
    fun navTo(
        direction: MainActivity.DIRECTION,
        bundle: Bundle?,
        addToBackStack: Boolean,
    )

    fun popBackStackTo(direction: MainActivity.DIRECTION?)
}