package com.ivanalvarado.template.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.ivanalvarado.template.database.cache.UsersLocalCache
import com.ivanalvarado.template.database.entity.UserEntity
import com.ivanalvarado.template.network.models.NetworkError
import com.ivanalvarado.template.network.services.StackOverflowService
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class UsersBoundaryCallback(
    private val service: StackOverflowService,
    private val cache: UsersLocalCache
) : PagedList.BoundaryCallback<UserEntity>() {

    private val _networkErrors = MutableLiveData<NetworkError>()

    val networkErrors: LiveData<NetworkError>
        get() = _networkErrors

    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    override fun onItemAtEndLoaded(itemAtEnd: UserEntity) {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        service.fetchUsers()
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = { response ->
                    Log.d("BoundaryCallback", "OnNext")
                    if (response.isSuccessful) {
                        val userEntities = response.body()?.users?.map { user ->
                            user.toUserEntity()
                        }

                        userEntities?.let {
                            cache.insert(userEntities)
                        }
                    } else {
                        _networkErrors.postValue(NetworkError(response.code(), response.message()))
                    }
                },
                onError = { t ->
                    Log.d("BoundaryCallback", "OnError")
                    _networkErrors.postValue(NetworkError(500, t.localizedMessage))
                }
            ).dispose()
    }
}