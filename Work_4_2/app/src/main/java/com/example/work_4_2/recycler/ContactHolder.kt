package com.example.work_4_2.recycler

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.work_4_2.data.Contact
import com.example.work_4_2.R
import com.example.work_4_2.databinding.ItemContactBinding

class ContactHolder(
    private val binding: ItemContactBinding,
    private val onClickOnItemCallback: (Contact) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Contact) {
        with(item) {
            binding.root.setOnClickListener {
                onClickOnItemCallback(this)
            }

            binding.itemContactTextViewName.text = "$lastName $firstName"
            binding.itemContactTextViewNumber.text = "$number"

            Glide.with(binding.root)
                .load(avatarUrl)
                .error(R.drawable.baseline_search_off)
                .into(binding.itemContactImageViewAvatar)
        }
    }
}