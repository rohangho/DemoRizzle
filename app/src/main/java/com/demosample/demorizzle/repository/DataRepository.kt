package com.demosample.demorizzle.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.demosample.demorizzle.model.BaseResponse
import com.google.gson.Gson
import java.io.IOException
import java.nio.charset.Charset

class DataRepository {
    private fun readJSONFromAsset(): String? {
        var json: String? = null
        json = try {
            val `is` = context!!.assets.open("abc.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    //    JSONObject obj = new JSONObject(readJSONFromAsset());
    val details: MutableLiveData<BaseResponse>
        get() {
            val listData = MutableLiveData<BaseResponse>()
            val gson = Gson()
            //    JSONObject obj = new JSONObject(readJSONFromAsset());
            val baseResponse = gson.fromJson(readJSONFromAsset(), BaseResponse::class.java)
            listData.value = baseResponse
            return listData
        }

    companion object {
        private var myApiRepo: DataRepository? = null
        private var context: Application? = null
        fun getInstance(application: Application?): DataRepository? {
            if (myApiRepo == null) {
                run {
                    myApiRepo = DataRepository()
                    context = application
                }
            }
            return myApiRepo
        }
    }
}