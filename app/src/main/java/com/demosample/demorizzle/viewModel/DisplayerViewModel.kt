package com.demosample.demorizzle.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.demosample.demorizzle.model.BaseResponse
import com.demosample.demorizzle.repository.DataRepository

class DisplayerViewModel(private val application: Application) : ViewModel(
) {
    private var mutableLiveData: MutableLiveData<BaseResponse>? = null
    private var myApiRepo: DataRepository? = null
    fun init() {
        myApiRepo = DataRepository.getInstance(application)
        mutableLiveData = myApiRepo!!.details
    }

    val detailsRepo: LiveData<BaseResponse>?
        get() = mutableLiveData
}