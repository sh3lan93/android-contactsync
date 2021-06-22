package com.shalan.contactssync.features.contactslist

import com.shalan.base.viewholder.BaseViewHolder
import com.shalan.contactssync.core.uimodels.Contact
import com.shalan.contactssync.databinding.ContactItemViewBinding

/**
 * Created by Mohamed Shalan on 21/06/2021
 */

class ContactViewHolder(binding: ContactItemViewBinding) :
    BaseViewHolder<ContactItemViewBinding, Contact>(binding) {

    override fun bind(item: Contact) {
        binding.item = item
    }
}