package com.example.hiltproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.facebook.flipper.BuildConfig
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        var CAT_FACT = "cat_fact"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initFragment()
    }
    private fun initFragment(){
        supportFragmentManager?.beginTransaction()
            ?.replace(
                R.id.cat_fact_fragment_container,
                MainFragment.newInstance(
                    3
                )
            )
            ?.commit()
    }
}