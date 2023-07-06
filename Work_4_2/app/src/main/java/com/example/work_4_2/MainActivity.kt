package com.example.work_4_2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.work_4_2.fragments.EditContactFragment
import com.example.work_4_2.fragments.ListContactsFragment
import com.example.work_4_2.interfaces.Navigate

class MainActivity : AppCompatActivity(), Navigate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navTo(DIRECTION.LIST_CONTACTS_FRAG, null, true)
    }

    override fun navTo(direction: DIRECTION, bundle: Bundle?, addToBackStack: Boolean) {
        val frag = when (direction) {
            DIRECTION.LIST_CONTACTS_FRAG -> ListContactsFragment.newInstance(bundle)
            DIRECTION.EDIT_CONTACT_FRAG -> EditContactFragment.newInstance(bundle)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.activityMain_frameLayout, frag)
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
        LIST_CONTACTS_FRAG,
        EDIT_CONTACT_FRAG,
    }

    companion object {
        @JvmStatic
        fun newIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}