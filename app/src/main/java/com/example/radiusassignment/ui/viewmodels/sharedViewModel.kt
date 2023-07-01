package com.example.radiusassignment.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.radiusassignment.data.remote.repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class sharedViewModel @Inject constructor(): ViewModel() {

    private val mutableLiveData = MutableLiveData<HashMap<String,String>?>()
    fun getId() : LiveData<HashMap<String, String>?> {
        return mutableLiveData
    }
    fun setId(facilityID : String, optionID : String){
        var map : HashMap<String,String>? = HashMap<String,String>()
        if (mutableLiveData.value!=null){
            map = mutableLiveData.value
        }
        map?.put(facilityID,optionID)
        mutableLiveData.value=map
    }

}