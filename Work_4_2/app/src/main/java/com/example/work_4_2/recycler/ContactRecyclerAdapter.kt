package com.example.work_4_2.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.work_4_2.data.Contact
import com.example.work_4_2.databinding.ItemContactBinding

class ContactRecyclerAdapter(
    private val onClickOnItemCallback: (Contact) -> Unit,
) : RecyclerView.Adapter<ContactHolder>() {
    private val differ = AsyncListDiffer(this, diffContact)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val binding = ItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContactHolder(binding, onClickOnItemCallback)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    fun updateListItems(items: List<Contact>) {
        differ.submitList(items)
    }

    companion object {
        private val diffContact = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Contact,
                newItem: Contact
            ) = oldItem == newItem
        }
    }
}