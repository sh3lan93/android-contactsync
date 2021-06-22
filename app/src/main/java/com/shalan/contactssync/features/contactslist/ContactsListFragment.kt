package com.shalan.contactssync.features.contactslist

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.shalan.base.fragment.singlelist.BaseSingleListFragment
import com.shalan.contactssync.R
import com.shalan.contactssync.callbacks.ContactsLoaderCallback
import com.shalan.contactssync.core.database.models.ContactEntity
import com.shalan.contactssync.core.uimodels.Contact
import com.shalan.contactssync.databinding.FragmentContactsListBinding
import com.shalan.contactssync.services.ContactsObserver
import org.koin.android.ext.android.get


class ContactsListFragment :
    BaseSingleListFragment<Contact, ContactsListViewModel, FragmentContactsListBinding, ContactsAdapter>(
        R.layout.fragment_contacts_list, ContactsListViewModel::class
    ) {


    companion object {
        private val CONTACTS_URI = ContactsContract.Contacts.CONTENT_URI
        private val READ_CONTACTS_PERMISSION = Manifest.permission.READ_CONTACTS
        private val CONTACTS_LOADER_ID = 101
    }

    private lateinit var contactsLoader: Loader<Cursor>

    private val onContactsLoaded: (contacts: Set<ContactEntity>) -> Unit = { contacts ->
        viewmodel.updateSavedContactsList(contacts.toList())
        LoaderManager.getInstance(this).destroyLoader(CONTACTS_LOADER_ID)
    }

    private val contactsLoaderCallback: ContactsLoaderCallback by lazy {
        ContactsLoaderCallback(requireContext(), onContactsLoaded)
    }

    private val contactsObserver by lazy {
        ContactsObserver(schedulerService = get(), useCase = get(), context = requireContext())
    }

    private val contactsPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                onContactsPermissionGranted()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.txt_read_contacts_message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    private val contactsAdapter by lazy {
        ContactsAdapter()
    }

    override fun onCreateInit(savedInstanceState: Bundle?) {
        super.onCreateInit(savedInstanceState)

        requestReadingContactsPermission()

        getRecyclerView().addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun getAdapter(): ContactsAdapter = contactsAdapter

    override fun getRecyclerView(): RecyclerView = binding.rvContacts

    override fun getSwipeRefresh(): SwipeRefreshLayout? = null

    override fun showLoading() {
        binding.loader.visibility = VISIBLE
        binding.rvContacts.visibility = GONE
    }

    override fun showError(error: String?) {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        binding.loader.visibility = GONE
        binding.rvContacts.visibility = VISIBLE
    }

    override fun showData(data: List<Contact>?) {
        contactsAdapter.submitList(data)
    }

    override fun showEmptyData() {
        Toast.makeText(requireContext(), getString(R.string.txt_empty_data), Toast.LENGTH_SHORT)
            .show()
    }

    private fun requestReadingContactsPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                READ_CONTACTS_PERMISSION
            ) == PackageManager.PERMISSION_GRANTED -> {
                onContactsPermissionGranted()
            }
            shouldShowRequestPermissionRationale(READ_CONTACTS_PERMISSION) -> Toast.makeText(
                requireContext(),
                getString(R.string.txt_read_contacts_message),
                Toast.LENGTH_SHORT
            ).show()
            else -> {
                contactsPermissionLauncher.launch(READ_CONTACTS_PERMISSION)
            }
        }
    }

    private fun onContactsPermissionGranted() {
        startReadingContacts()
        registerContactsObserver()
    }

    private fun registerContactsObserver() {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N)
            requireContext().contentResolver.registerContentObserver(
                CONTACTS_URI,
                true,
                contactsObserver
            )
    }

    private fun startReadingContacts() {
        if (!this::contactsLoader.isInitialized)
            contactsLoader = LoaderManager.getInstance(this)
                .initLoader(CONTACTS_LOADER_ID, null, contactsLoaderCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LoaderManager.getInstance(this).destroyLoader(CONTACTS_LOADER_ID)
    }
}