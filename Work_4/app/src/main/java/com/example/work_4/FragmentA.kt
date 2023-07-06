package com.example.work_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.work_4.databinding.FragmentABinding

class FragmentA : Fragment() {
    private lateinit var binding: FragmentABinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentABinding.inflate(inflater, container, false)

        binding.fragmentAButtonB.setOnClickListener {
            (requireActivity() as Navigate).navTo(
                MainActivity.DIRECTION.FRAGMENT_B,
                null,
                true,
            )
        }

        return binding.root
    }

    companion object {
        fun newInstance(bundle: Bundle?): FragmentA {
            return FragmentA().apply {
                arguments = bundle
            }
        }
    }
}