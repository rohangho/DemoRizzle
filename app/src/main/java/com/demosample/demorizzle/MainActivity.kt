package com.demosample.demorizzle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demosample.demorizzle.adapter.MyDisplayAdapter
import com.demosample.demorizzle.model.BaseResponse
import com.demosample.demorizzle.model.DisplayModel
import com.demosample.demorizzle.viewModel.DisplayerViewModel


class MainActivity : AppCompatActivity() {
    private var displayViewModel: DisplayerViewModel? = null
    private lateinit var myAdapter: MyDisplayAdapter
    private lateinit var recylerdisplayer: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recylerdisplayer = findViewById(R.id.recycler);
        recylerdisplayer.layoutManager = GridLayoutManager(this, 2)


        displayViewModel = ViewModelProvider(this).get(DisplayerViewModel::class.java)
        displayViewModel!!.init()
        displayViewModel!!.getDetailsRepo().observe(this, this::updateUi)
    }

    private fun updateUi(detailsList: BaseResponse) {
       var celebrityContent:ArrayList<DisplayModel> = ArrayList()
        var carContent:ArrayList<DisplayModel> = ArrayList()
        var allContent:ArrayList<DisplayModel> = ArrayList()
       var counter =0;
        var counter1 = 0
        for(i in 0 until detailsList.data!!.celebrities!!.nodes!!.size-1)
        {
            var imgLink = detailsList.data!!.celebrities!!.nodes!!.get(i).link!!.substring(0,detailsList.data!!.celebrities!!.nodes!!.get(i).link!!.indexOf("?"))
           celebrityContent.add(DisplayModel(imgLink,detailsList.data!!.celebrities!!.nodes!!.get(i).name))
        }

        for(i in 0 until detailsList.data!!.cars!!.nodes!!.size-1)
        {
            var imgLink = detailsList.data!!.cars!!.nodes!!.get(i).link!!.substring(0,detailsList.data!!.cars!!.nodes!!.get(i).link!!.indexOf("?"))
            carContent.add(DisplayModel(imgLink,detailsList.data!!.cars!!.nodes!!.get(i).name))
        }
        for(i in 0 until celebrityContent.size+carContent.size)
        {
            if(i%2 != 0)
            {
               allContent.add(celebrityContent.get(counter))
                counter ++
            }
            else
            {
                allContent.add(carContent.get(counter1))
                counter1++
            }
        }
        myAdapter = MyDisplayAdapter(this,allContent)
        recylerdisplayer.adapter = myAdapter
    }
}