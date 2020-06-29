package com.bhanu.nasaphotooftheday.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bhanu.nasaphotooftheday.R


/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        gotoDashboardScreen()
    }
    private fun gotoDashboardScreen(){
        Handler().postDelayed({
            navigateToDashboard()
        },SPLASH_DELAY)
    }
    private fun navigateToDashboard(){
        try {
            findNavController().navigate(R.id.action_splashFragment_to_clubFragment)
        } catch (e: Exception) {
            Log.e("Splash","exception: ${e.message}")
        }
    }

    companion object{
        private const val SPLASH_DELAY:Long = 2000
    }
}