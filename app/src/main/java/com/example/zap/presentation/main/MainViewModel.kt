package com.example.zap.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zap.core.Either
import com.example.zap.domain.Repository
import com.example.zap.presentation.model.ImmobileVO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel(private val repository: Repository) : ViewModel() {

    val dataLiveData = MutableLiveData<Either<HttpException, ArrayList<ImmobileVO>>?>()

    fun getVivaRealData() {
        viewModelScope.launch {
            kotlin.runCatching {
                val result = async { repository.getVivaRealData() }
                dataLiveData.postValue(result.await())
            }
        }
    }

    fun getZapData() {
        viewModelScope.launch {
            kotlin.runCatching {
                val result = async { repository.getZapData() }
                dataLiveData.postValue(result.await())
            }
        }
    }
}
