package com.bhanu.nasaphotooftheday.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhanu.nasaphotooftheday.model.Apod
import com.bhanu.nasaphotooftheday.network.ApiClient
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.UnknownHostException


/**
 * Created by Bhanu Prakash Pasupula on 25,Jun-2020.
 * Email: pasupula1995@gmail.com
 */
class NasaViewModel(private val apiClient: ApiClient):ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _apod = MutableLiveData<Apod>()
    val apod: LiveData<Apod> = _apod

    private val _errorMsg = MutableLiveData<String>()
    val errorMsg: LiveData<String> = _errorMsg

    fun getApod(date:String){

        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val res = apiClient.getClubs(date = date)
                _apod.postValue(res)
                _isLoading.postValue(false)
            }catch (e: UnknownHostException){
                _isLoading.postValue(false)
                _errorMsg.postValue("No internet connection. Please try again")
            } catch (e: Exception) {
                Log.e("TAG","error: ${e.message}")
                _isLoading.postValue(false)

            }
        }
    }

}