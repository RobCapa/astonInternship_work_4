package com.example.work_4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), Navigate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navTo(DIRECTION.FRAGMENT_A, null, true)
    }

    override fun navTo(
        direction: DIRECTION,
        bundle: Bundle?,
        addToBackStack: Boolean,
    ) {
        val frag = when (direction) {
            DIRECTION.FRAGMENT_A -> FragmentA.newInstance(bundle)
            DIRECTION.FRAGMENT_B -> FragmentB.newInstance(bundle)
            DIRECTION.FRAGMENT_C -> FragmentC.newInstance(bundle)
            DIRECTION.FRAGMENT_D -> FragmentD.newInstance(bundle)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainActivity_frameLayout, frag)
            .apply { if (addToBackStack) addToBackStack(direction.toString()) }
            .commit()
    }

    override fun popBackStackTo(direction: DIRECTION?) {
        if (direction != null) {
            supportFragmentManager.popBackStack(
                direction.toString(),
                0,
            )
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    enum class DIRECTION {
        FRAGMENT_A,
        FRAGMENT_B,
        FRAGMENT_C,
        FRAGMENT_D,
    }

    companion object {
        @JvmStatic
        fun newIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}