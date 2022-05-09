package com.example.zap.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zap.core.Either
import com.example.zap.domain.Repository
import com.example.zap.presentation.model.ImmobileVO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException

class DetailsViewModel(private val repository: Repository) : ViewModel() {

    val immobileLiveData = MutableLiveData<Either<HttpException, ImmobileVO?>>()

    fun getImmobile(id: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                val result = async { repository.getImmobileById(id) }
                immobileLiveData.postValue(result.await())
            }
        }
    }




















    dadofhhdas
    adasdd asd
    asd
    da
    dasd
    asd
    asd
    asf
    afdas
    fd
    qawrfq
}
