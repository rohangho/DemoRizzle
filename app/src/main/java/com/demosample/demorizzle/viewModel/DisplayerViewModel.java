package com.demosample.demorizzle.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.demosample.demorizzle.model.BaseResponse;
import com.demosample.demorizzle.repository.DataRepository;

public class DisplayerViewModel extends AndroidViewModel {
    private MutableLiveData<BaseResponse> mutableLiveData;
    private DataRepository myApiRepo;
    private Application application;

    public DisplayerViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }


    public void init() {

        myApiRepo = DataRepository.getInstance(application);
        mutableLiveData = myApiRepo.getDetails();
    }


    public LiveData<BaseResponse> getDetailsRepo() {
        return mutableLiveData;
    }

}
