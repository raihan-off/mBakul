package org.d3if3033.mbakul.ui.daftarBarang

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3033.mbakul.model.DaftarBarang
import org.d3if3033.mbakul.network.DaftarBarangApi

class DaftarBarangViewModel: ViewModel() {

    private val data = MutableLiveData<List<DaftarBarang>>()
    private val status = MutableLiveData<DaftarBarangApi.ApiStatus>()

    init {
        retriveData()
    }

    private fun retriveData(){
        viewModelScope.launch (Dispatchers.IO){
            status.postValue(DaftarBarangApi.ApiStatus.LOADING)
            try {
                data.postValue(DaftarBarangApi.service.getDaftarBarang())
                status.postValue(DaftarBarangApi.ApiStatus.SUCCESS)
            }catch (e: Exception){
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.postValue(DaftarBarangApi.ApiStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<List<DaftarBarang>> = data

    fun getStatus(): LiveData<DaftarBarangApi.ApiStatus> = status
}