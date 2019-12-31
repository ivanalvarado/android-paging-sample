package com.ivanalvarado.template.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ivanalvarado.template.database.entity.UserEntity
import com.ivanalvarado.template.network.models.NetworkError
import com.ivanalvarado.template.repository.UsersRepository
import com.ivanalvarado.template.repository.models.UsersResult
import javax.inject.Inject

class UsersViewModel @Inject constructor(private val repository: UsersRepository) :
    ViewModel() {

    private val trigger = MutableLiveData<String>()

    private val usersResult: LiveData<UsersResult> = Transformations.map(trigger) {
        repository.getUsers()
    }

    val users: LiveData<PagedList<UserEntity>> = Transformations.switchMap(usersResult) {
        it.data
    }

    val networkErrors: LiveData<NetworkError> = Transformations.switchMap(usersResult) {
        it.networkError
    }

    fun getUsers(query: String) {
        trigger.postValue(query)
    }
}