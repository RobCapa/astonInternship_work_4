package com.example.work_4_2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.work_4_2.data.Contact
import com.example.work_4_2.repositories.ContactRepository
import com.example.work_4_2.MainActivity
import com.example.work_4_2.interfaces.Navigate
import com.example.work_4_2.databinding.FragmentListContactsBinding
import com.example.work_4_2.recycler.ContactRecyclerAdapter

class ListContactsFragment : Fragment() {
    private lateinit var binding: FragmentListContactsBinding
    private val contactAdapter = ContactRecyclerAdapter(::openEditContactFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(REQUEST_EDIT_CONTACT_TAG) { _, bundle ->
            val contactWasUpdated = bundle.getBoolean(CONTACT_WAS_UPDATED_RESULT)
            if (contactWasUpdated) {
                refreshItemsInRecycler()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListContactsBinding.inflate(inflater, container, false)

        with(binding.fragListContactsRecyclerView) {
            adapter = contactAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        refreshItemsInRecycler()

        return binding.root
    }

    private fun refreshItemsInRecycler() {
        contactAdapter.updateListItems(ContactRepository.getContacts())
    }

    private fun openEditContactFragment(contact: Contact) {
        (requireActivity() as Navigate).navTo(
            MainActivity.DIRECTION.EDIT_CONTACT_FRAG,
            EditContactFragment.createBundle(contact),
            true
        )
    }

    companion object {
        const val REQUEST_EDIT_CONTACT_TAG = "REQUEST_EDIT_CONTACT_TAG"
        const val CONTACT_WAS_UPDATED_RESULT = "CONTACT_WAS_UPDATED_RESULT"

        fun newInstance(bundle: Bundle?): ListContactsFragment {
            return ListContactsFragment().apply {
                arguments = bundle
            }
        }
    }
}