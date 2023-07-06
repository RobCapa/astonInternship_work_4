package com.example.work_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.work_4.databinding.FragmentDBinding

class FragmentD : Fragment() {
    private lateinit var binding: FragmentDBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDBinding.inflate(inflater, container, false)

        binding.fragmentDButtonB.setOnClickListener {
            (requireActivity() as Navigate).popBackStackTo(
                MainActivity.DIRECTION.FRAGMENT_B,
            )
        }

        return binding.root
    }

    companion object {
        fun newInstance(bundle: Bundle?): FragmentD {
            return FragmentD().apply {
                arguments = bundle
            }
        }
    }
}