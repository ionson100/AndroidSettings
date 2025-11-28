package com.bitnic.androidsettings

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bitnic.settings.ResultUpdate
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

            var res: ResultUpdate = o as ResultUpdate;
            if(res.fieldName.equals("buttonAction")&&res.value==true){
                Toast.makeText(this, "Очистка кеша", Toast.LENGTH_SHORT).show()
            }
            if(res.fieldName.equals("buttonAction")&&res.value==false){
                Toast.makeText(this, "Отказался чистить кеш", Toast.LENGTH_SHORT).show()
            }

            settingsBuilder.refresh();
            return@SettingsBuilder null;

        })
    }
}