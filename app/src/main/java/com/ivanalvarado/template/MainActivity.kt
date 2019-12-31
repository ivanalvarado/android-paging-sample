package com.ivanalvarado.template

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ivanalvarado.template.viewmodel.UsersViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    /*
     * Step 1: Here as mentioned in Step 5, we need to
     * inject the ViewModelFactory. The ViewModelFactory class
     * has a list of ViewModels and will provide
     * the corresponding ViewModel in this activity
     * */
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        /*
         * Step 2: Remember in our ActivityModule, we
         * defined MainActivity injection? So we need
         * to call this method in order to inject the
         * ViewModelFactory into our Activity
         * */
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        })
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
