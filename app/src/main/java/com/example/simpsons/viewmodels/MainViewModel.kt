package com.example.simpsons.viewmodels

import android.content.Context
import androidx.annotation.CheckResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.network.ApiResponseStatus
import com.example.network.network.models.ApiModel
import com.example.network.repositories.mappers.ApiRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repository: ApiRepositories
) : ViewModel() {

    private val subModuleList = MutableLiveData<List<ApiModel>>()

    private val _status = MutableLiveData<ApiResponseStatus<List<ApiModel>>>()

    val status: LiveData<ApiResponseStatus<List<ApiModel>>>
        get() = _status

    fun getResponse(search: String = "lisa", limit: Int = 30) {

        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }

        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            _status.postValue(ApiResponseStatus.Loading())
            val resultServer = repository.getSearchPeople(search = search, limit = limit)
            handleResponseStatus(resultServer)
        }
    }

    private fun handleResponseStatus(downloadPs: ApiResponseStatus<List<ApiModel>>) {
        if (downloadPs is ApiResponseStatus.Success) {
            subModuleList.postValue(downloadPs.data)
        }
        _status.postValue(downloadPs)
    }

    @CheckResult
    fun onSubModuleListChange(): LiveData<List<ApiModel>> = subModuleList
}