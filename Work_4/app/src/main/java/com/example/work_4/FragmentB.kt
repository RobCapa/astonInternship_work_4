package com.example.work_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.work_4.databinding.FragmentBBinding

class FragmentB : Fragment() {
    private lateinit var binding: FragmentBBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBBinding.inflate(inflater, container, false)

        binding.fragmentBButtonC.setOnClickListener {
            (requireActivity() as Navigate).navTo(
                MainActivity.DIRECTION.FRAGMENT_C,
                FragmentC.createBundle("Hello Fragment C"),
                true,
            )
        }

        binding.fragmentBButtonBack.setOnClickListener {
            (requireActivity() as Navigate).popBackStackTo(
                MainActivity.DIRECTION.FRAGMENT_A,
            )
        }

        return binding.root
    }

    companion object {
        fun newInstance(bundle: Bundle?): FragmentB {
            return FragmentB().apply {
                arguments = bundle
            }
        }
    }
}