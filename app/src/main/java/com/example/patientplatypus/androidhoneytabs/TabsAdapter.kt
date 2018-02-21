package com.example.patientplatypus.androidhoneytabs

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import com.example.patientplatypus.androidhoneytabs.fragments.HomeFragment
import com.example.patientplatypus.androidhoneytabs.fragments.MusicFragment
import com.example.patientplatypus.androidhoneytabs.fragments.MapFragment




class TabsAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    var musicFragment = MusicFragment()
    val homeFragment = HomeFragment()
    val mapFragment = MapFragment()


    companion object {
        var weathermain: String = "weathermain"
        var weatherdescription: String = "weatherdescription"
        var weatherurl: String = "weatherurl"
    }

    // Title for tabs
    private val fragmentTitles = arrayOf("Home", "Music", "Map")
    // Return the Fragment associated with a specified position.
    override fun getItem(position: Int): Fragment {
//        homeFragment.getWeather("pants")
        when (position) {
            0 -> {
//                println("value of weathermain in TabAdapter "+ weathermain)
//                println("value of weatherdescription in TabAdapter "+ weatherdescription)
//                println("value of weatherurl in TabAdapter "+ weatherurl)
//                return homeFragment.newInstance(weathermain, weatherdescription, weatherurl)
              return HomeFragment()
            }
            1 ->
                return MusicFragment()
            2 ->
                return MapFragment()
            else ->
                return HomeFragment()
        }
    }
    // Return the number of views/fragments available.
    override fun getCount(): Int = 3 // in our case 3 fragments
    // Return title based on position
    override fun getPageTitle(position: Int): CharSequence {
        return fragmentTitles[position]
    }
}