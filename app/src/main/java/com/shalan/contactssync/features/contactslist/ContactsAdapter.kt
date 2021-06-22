package com.shalan.contactssync.features.contactslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.shalan.base.adapter.BaseAdapter
import com.shalan.contactssync.R
import com.shalan.contactssync.core.uimodels.Contact
import com.shalan.contactssync.databinding.ContactItemViewBinding

/**
 * Created by Mohamed Shalan on 21/06/2021
 */

class ContactsAdapter : BaseAdapter<Contact, ContactViewHolder>(diffUtil) {

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Contact>() {
            override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean =
                oldItem == newItem

        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder =
        DataBindingUtil.inflate<ContactItemViewBinding>(
            LayoutInflater.from(parent.context), R.layout.contact_item_view, parent, false
        ).let { viewBinding ->
            ContactViewHolder(viewBinding)
        }
}