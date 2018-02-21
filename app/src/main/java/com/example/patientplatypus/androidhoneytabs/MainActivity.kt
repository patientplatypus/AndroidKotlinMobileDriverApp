package com.example.patientplatypus.androidhoneytabs


import android.annotation.SuppressLint
import android.graphics.Movie
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import com.github.kittinunf.fuel.Fuel
import kotlinx.android.synthetic.main.activity_main.*
import com.google.gson.annotations.SerializedName
import com.beust.klaxon.Parser
import com.beust.klaxon.JsonObject
import com.example.patientplatypus.androidhoneytabs.fragments.HomeFragment
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import org.json.JSONObject
import com.squareup.picasso.Picasso
import io.reactivex.disposables.Disposable
//import com.example.patientplatypus.androidhoneytabs.KeyValue
import kotlinx.android.synthetic.main.fragment_home.*


class MainActivity: AppCompatActivity(){

    var mainMessage: Disposable?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main)
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Setup toolbar
        setSupportActionBar(toolbar)
        getSupportActionBar()?.hide();
        // Setup ViewPager

        view_pager.adapter = TabsAdapter(supportFragmentManager)
        // Setup TabLayout
        tab_layout.setupWithViewPager(view_pager)


    }

    public override fun onStart() {
        super.onStart()
        mainMessage = MainEventBus.INSTANCE.toObserverable().subscribe({
            println("MESSAGE SENT FROM MAINACTIVITY")
            println(it)
        })
    }

    public override fun onStop() {
        println("THIS IS THE ONSTOP METHOD")
        mainMessage?.dispose()
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
        val testEvent = MainEventBus.Companion.KeyValue(key = "testkey", value = "keyvalue")
        MainEventBus.INSTANCE.send(testEvent)
        weatherRequests()
    }

    private fun weatherRequests(){

        Fuel.get("http://httpbin.org/get").responseString { request, response, result ->
            //do something with response
            result.fold({ d ->
                println("test value of Fuel: " + d)
                //do something with data
            }, { err ->
                //do something with error
            })
        }

        "http://api.openweathermap.org/data/2.5/weather?q=Austin,us&appid=abd532567a4d169ee04f6597584ba084".httpGet().responseJson { _, _, result ->
            //do something with response
            result.fold(success = { json ->
                println("success from json request to weatherAPI")
                val weatherArray = result.get().obj().getJSONArray("weather").get(0) as JSONObject
                println("value of main in weatherArray:" + weatherArray["main"])
                println("value of description in weatherArray:" + weatherArray["description"])
                println("value of icon in weatherArray: " + weatherArray["icon"])
                val mainWeatherEvent = MainEventBus.Companion.KeyValue(key="mainWeather", value = weatherArray["main"].toString())
                val descriptionWeatherEvent = MainEventBus.Companion.KeyValue(key="descriptionWeather", value = weatherArray["description"].toString())
                val weatherurl = "http://openweathermap.org/img/w/"+weatherArray["icon"].toString()+".png"
                val iconWeatherEvent = MainEventBus.Companion.KeyValue(key="iconWeather", value = weatherurl)

                println("BEFORE SENDING EVENTBUS ITEMS")
                MainEventBus.INSTANCE.send(mainWeatherEvent)
                MainEventBus.INSTANCE.send(descriptionWeatherEvent)
                MainEventBus.INSTANCE.send(iconWeatherEvent)
                println("AFTER SENDING EVENTBUS ITEMS")

                val testEvent2 = MainEventBus.Companion.KeyValue(key = "testkey2", value = "keyvalue2")
                MainEventBus.INSTANCE.send(testEvent2)

            }, failure = { error ->
                Log.e("qdp error", error.toString())
            })
        }

        Log.d("aftertag","after fuel Request")
    }

}


