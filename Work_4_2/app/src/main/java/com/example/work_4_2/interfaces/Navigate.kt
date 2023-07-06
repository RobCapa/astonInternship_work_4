package com.example.work_4_2.interfaces

import android.os.Bundle
import com.example.work_4_2.MainActivity

interface Navigate {
    fun navTo(
        direction: MainActivity.DIRECTION,
        bundle: Bundle?,
        addToBackStack: Boolean,
    )

    fun popBackStackTo(direction: MainActivity.DIRECTION?)
}