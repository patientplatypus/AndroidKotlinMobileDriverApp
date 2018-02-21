package com.example.patientplatypus.androidhoneytabs.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.patientplatypus.androidhoneytabs.R
import android.widget.TextView
import com.example.patientplatypus.androidhoneytabs.TabsAdapter
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONObject
//import org.greenrobot.eventbus.EventBus
import android.widget.Toast
import com.google.android.gms.wearable.MessageEvent
import io.reactivex.disposables.Disposable

//import org.greenrobot.eventbus.ThreadMode
//import org.greenrobot.eventbus.Subscribe






/**
 * A simple [Fragment] subclass.
 */



class HomeFragment : Fragment() {
    var mainMessage: Disposable?=null
    var weathermain: String = "weathermain"
    var weatherdescription: String = "weatherdescription"
    var weatherurl: String = "weatherurl"

//    companion object {
//        fun newInstance(weathermain: String, weatherdescription: String, weatherurl: String) = HomeFragment().apply {
//            arguments = Bundle(2).apply {
//               putString("weathermain", weathermain)
//            }
//        }
//    }


    var weathermainView: TextView?=null
    var weatherdescriptionView:  TextView?=null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater!!.inflate(R.layout.fragment_home, container, false)
//        val bdl: Bundle = arguments
//        val weatherGetArgs =  bdl.getString("key")
//        println("&&&&&*******&&&&&&")
//        println("getarguments: " + weatherGetArgs)
//        println("&&&&&*******&&&&&&")

//        callWeather()

        val weathermainView = view.findViewById<TextView>(R.id.textweathermain)
        val weatherdescriptionView = view.findViewById<TextView>(R.id.textWeatherDescription)
//        println("!!!after setting weathermainView in onCreateView!!!")
//        println("value of weathermainView " + weathermainView?.text.toString())

//        val weathermain = arguments.getString("weathermain")
//        println("weathermainARGUMENTS: " + arguments.getString("weathermain"))
//        weathermainView.setText("YOLO DAWG")

        println("***********************************************")
        println("value of weathermainView in onCreateView BEFORE setting: " + weathermainView.text)
        println("***********************************************")
        weathermainView.setText(weathermain)
        weatherdescriptionView.setText(weatherdescription)
        println("***********************************************")
        println("value of weathermainView in onCreateView AFTER setting: " + weathermainView.text)
        println("***********************************************")
        return view
    }

    override fun onStart() {
        super.onStart()
        println("INSIDE HOMEFRAGMENT ONSTART")
        mainMessage = MainEventBus.INSTANCE.toObserverable().subscribe({
            println("Value of IT IN HOMEFRAGMENT: " + it)
            if (it.key=="mainWeather"){
                weathermain = it.value
                println("INSIDE ONSTART IN HOMEFRAGMENT AND IT.VALUE" + it.value)
                val ft = fragmentManager.beginTransaction()
                ft.detach(this).attach(this).commit()
            }else if (it.key=="descriptionWeather"){
                weatherdescription = it.value
                val ft = fragmentManager.beginTransaction()
                ft.detach(this).attach(this).commit()
            }else if (it.key=="iconWeather"){
                weatherurl = it.value
                val ft = fragmentManager.beginTransaction()
                ft.detach(this).attach(this).commit()
            }
        })
    }

    override fun onStop() {
        mainMessage?.dispose()
        super.onStop()
    }


//    fun callWeather(){
//        "http://api.openweathermap.org/data/2.5/weather?q=Austin,us&appid=abd532567a4d169ee04f6597584ba084".httpGet().responseJson { _, _, result ->
//            result.fold(success = { json ->
//                println("success from json request to weatherAPI")
//                val weatherArray = result.get().obj().getJSONArray("weather").get(0) as JSONObject
//                println("value of main in weatherArray:" + weatherArray["main"])
//                println("value of description in weatherArray:" + weatherArray["description"])
//                println("value of icon in weatherArray: " + weatherArray["icon"])
//                weathermain = weatherArray["main"].toString()
////                val ft = fragmentManager.beginTransaction()
////                ft.detach(this).attach(this).commit()
//            }, failure = { error ->
//                Log.e("qdp error", error.toString())
//            })
//        }
//    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun onMessageEvent(event: MessageEvent) {
//        weathermain = event.message
//        val ft = fragmentManager.beginTransaction()
//        ft.detach(this).attach(this).commit()
//    }


//    fun newInstance(weathermain: String, weatherdescription: String, weatherurl: String): Fragment {
//        val f = HomeFragment()
//        println("&&&& inside newInstance &&&&")
//        println("value of weathermain: " + weathermain)
//        println("value of weatherdescription: " + weatherdescription)
//        println("value of weatherurl: " + weatherurl)
//        f.weathermain = weathermain
//        f.weatherdescription = weatherdescription
//        f.weatherurl = weatherurl
//        return f
//    }

//    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        println("INSIDE ONVIEWCREATED")
////        weathermainView?.setText("WAZZUP")
//        println("***********************************************")
//        println("value of weathermainView in onViewCreated: " + weathermainView?.text)
//        println("***********************************************")
//    }

//        fun getWeather(someString: String){
//          println("&&&&& INSIDE GETWEATHER &&&&&" + someString)
//        }

//    fun getWeather(args: Bundle){
//        val weatherMain = args.getString("weathermain")
//        val weatherDescription = args.getString("weatherdescription")
//        val timer = Timer()
//        timer.schedule(timerTask {
//            println("value of weathermainView BEFORE SETTING: " + weathermainView?.text.toString())
//            println("value of weatherdescriptionView BEFORE SETTING: " + weatherdescriptionView?.text.toString())
//            weathermainView?.text =  args.getString("weathermain")
//            weatherdescriptionView?.text = args.getString("weatherdescription")
//            println("value of weathermainView " + weathermainView?.text.toString())
//            println("value of weatherdescriptionView " + weatherdescriptionView?.text.toString())
//        }, 1000)
//
//        println("Value of weatherMain: " + weatherMain)
//        println("Value of weatherDescription: " + weatherDescription)
//
//    }
//
//    override fun onStart() {
//        super.onStart()
//        EventBus.getDefault().register(this)
//    }
//
//    override fun onStop() {
//        EventBus.getDefault().unregister(this)
//        super.onStop()
//    }
}
