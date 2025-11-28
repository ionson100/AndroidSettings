package com.bitnic.androidsettings

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bitnic.settings.SettingsBuilder

class MainActivity : AppCompatActivity() {


    var mySettings: MySettings = MySettings()
    lateinit var settingsBuilder: SettingsBuilder;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var list_settings: ListView = findViewById<ListView?>(R.id.list_settings)


        settingsBuilder=  SettingsBuilder(this@MainActivity,mySettings, list_settings,{ o ->
            settingsBuilder.refresh();
            return@SettingsBuilder null;

        })
    }
}