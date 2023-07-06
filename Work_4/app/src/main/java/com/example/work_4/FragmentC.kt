package com.example.work_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.work_4.databinding.FragmentCBinding

class FragmentC : Fragment() {
    private lateinit var binding: FragmentCBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCBinding.inflate(inflater, container, false)

        binding.fragmentCTextView.text = arguments?.getString(MESSAGE_EXTRA)

        binding.fragmentCButtonD.setOnClickListener {
            (requireActivity() as Navigate).navTo(
                MainActivity.DIRECTION.FRAGMENT_D,
                null,
                true,
            )
        }

        binding.fragmentCButtonA.setOnClickListener {
            (requireActivity() as Navigate).popBackStackTo(
                MainActivity.DIRECTION.FRAGMENT_A,
            )
        }

        return binding.root
    }

    companion object {
        const val MESSAGE_EXTRA = "MESSAGE_EXTRA"

        fun createBundle(message: String): Bundle {
            return bundleOf(
                MESSAGE_EXTRA to message
            )
        }

        fun newInstance(bundle: Bundle?): FragmentC {
            return FragmentC().apply {
                arguments = bundle
            }
        }
    }
}