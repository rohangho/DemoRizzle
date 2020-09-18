package com.demosample.demorizzle.repository;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.demosample.demorizzle.model.BaseResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class DataRepository {

    private static DataRepository myApiRepo;
    private static Application context;


    public DataRepository() {
    }

    public static DataRepository getInstance(Application application) {
        if (myApiRepo == null) {
            {
                myApiRepo = new DataRepository();
                context = application;
            }

        }
        return myApiRepo;
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("abc.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public MutableLiveData<BaseResponse> getDetails() {
        final MutableLiveData<BaseResponse> listData = new MutableLiveData<>();
        Gson gson = new Gson();
        //    JSONObject obj = new JSONObject(readJSONFromAsset());
        BaseResponse baseResponse = gson.fromJson(readJSONFromAsset(), BaseResponse.class);
        listData.setValue(baseResponse);
        return listData;
    }

}
