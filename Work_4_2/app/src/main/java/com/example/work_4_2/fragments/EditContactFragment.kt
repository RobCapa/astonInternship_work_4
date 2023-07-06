package com.example.work_4_2.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.bumptech.glide.Glide
import com.example.work_4_2.data.Contact
import com.example.work_4_2.repositories.ContactRepository
import com.example.work_4_2.interfaces.Navigate
import com.example.work_4_2.R
import com.example.work_4_2.databinding.FragmentEditContactBinding
import java.util.Timer
import java.util.TimerTask


class EditContactFragment : Fragment() {
    private lateinit var binding: FragmentEditContactBinding
    private lateinit var contact: Contact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contact = arguments
            ?.getParcelable(CONTACT_EXTRA)
            ?: throw RuntimeException("Contact is missing by CONTACT_EXTRA")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditContactBinding.inflate(inflater, container, false)

        with(contact) {
            binding.fragEditContactEditTextLastName.setText(lastName)
            binding.fragEditContactEditTextFirstName.setText(firstName)
            binding.fragEditContactEditPhoneNumber.setText(number)
            binding.fragEditContactEditTextAvatarUrl.setText(avatarUrl)
            showAvatar(avatarUrl)

            binding.fragEditContactEditTextAvatarUrl.addTextChangedListener(
                getTextWatcherForUpdateAvatar()
            )

            binding.fragEditContactButtonSave.setOnClickListener {
                updateContact()
                setFragmentResult(
                    ListContactsFragment.REQUEST_EDIT_CONTACT_TAG,
                    bundleOf(ListContactsFragment.CONTACT_WAS_UPDATED_RESULT to true)
                )
                popBackStack()
            }

            binding.fragEditContactButtonCancel.setOnClickListener {
                popBackStack()
            }
        }

        return binding.root
    }

    private fun getTextWatcherForUpdateAvatar(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, st: Int, c: Int, a: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            private var timer = Timer()
            override fun afterTextChanged(s: Editable?) {
                timer.cancel()
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        requireActivity().runOnUiThread {
                            showAvatar(s.toString())
                        }
                    }
                }, 600)
            }
        }
    }

    private fun showAvatar(url: String) {
        Glide.with(binding.root)
            .load(url)
            .error(R.drawable.baseline_search_off)
            .into(binding.fragEditContactImageViewAvatar)
    }

    private fun updateContact() {
        ContactRepository.updateContact(
            contact.copy(
                lastName = binding.fragEditContactEditTextLastName.text.toString(),
                firstName = binding.fragEditContactEditTextFirstName.text.toString(),
                number = binding.fragEditContactEditPhoneNumber.text.toString(),
                avatarUrl = binding.fragEditContactEditTextAvatarUrl.text.toString()
            )
        )
    }

    private fun popBackStack() {
        (requireActivity() as Navigate).popBackStackTo(null)
    }

    companion object {
        private const val CONTACT_EXTRA = "CONTACT_EXTRA"

        fun createBundle(contact: Contact): Bundle {
            return bundleOf(
                CONTACT_EXTRA to contact
            )
        }

        fun newInstance(bundle: Bundle?): EditContactFragment {
            return EditContactFragment().apply {
                arguments = bundle
            }
        }
    }
}