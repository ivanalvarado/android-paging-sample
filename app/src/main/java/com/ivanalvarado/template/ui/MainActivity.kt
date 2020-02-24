package com.ivanalvarado.template.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.ivanalvarado.template.R
import com.ivanalvarado.template.viewmodel.UsersViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: UsersViewModel

    private lateinit var usersList: RecyclerView

    private val adapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersList = findViewById(R.id.users_list)
        usersList.adapter = adapter
        setUpViewModel()
        observe()
        viewModel.getUsers("test")
    }

    private fun setUpViewModel() {
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(UsersViewModel::class.java)
    }

    private fun observe() {
        viewModel.users.observe(this, Observer { users ->
            Log.d(TAG, "${users.size}")
            adapter.submitList(users)
        })
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
