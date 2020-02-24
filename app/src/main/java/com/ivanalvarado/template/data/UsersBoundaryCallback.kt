package com.ivanalvarado.template.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.ivanalvarado.template.database.cache.UsersLocalCache
import com.ivanalvarado.template.database.entity.UserEntity
import com.ivanalvarado.template.network.models.NetworkError
import com.ivanalvarado.template.network.models.UsersResponse
import com.ivanalvarado.template.network.services.StackOverflowService
import com.ivanalvarado.template.util.logDebug
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersBoundaryCallback(
    private val service: StackOverflowService,
    private val cache: UsersLocalCache
) : PagedList.BoundaryCallback<UserEntity>() {

    private var lastRequestedPage = 1
    private var isRequestInProgress = false

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
        if (isRequestInProgress) return

        logDebug("fetchUsers")
        isRequestInProgress = true
        service.fetchUsers(lastRequestedPage).enqueue(object: Callback<UsersResponse>{
            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                _networkErrors.postValue(NetworkError(500, t.localizedMessage))
            }

            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                if (response.isSuccessful) {
                    val userEntities = response.body()?.users?.map { user ->
                        user.toUserEntity()
                    }

                    userEntities?.let {
                        cache.insert(userEntities) {
                            lastRequestedPage++
                            isRequestInProgress = false
                        }
                    }
                } else {
                    _networkErrors.postValue(NetworkError(response.code(), response.message()))
                    isRequestInProgress = false
                }
            }
        })
    }
}