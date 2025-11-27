package com.bitnic.androidsettings;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.InputType;

import androidx.annotation.RequiresApi;


import com.bitnic.settings.SettingItem;
import com.bitnic.settings.SettingType;
import com.bitnic.settings.SettingsHost;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;



@SettingsHost(leftWeight = 50f,rightWeight = 50f)
public class MySettings {



//
//
//    // for usage
//    // add gradle.property android.nonFinalResIds=false
//
//    @SuppressLint("NonConstantResourceId")
//    @SettingItem(
//            type = SettingType.BOOLEAN,
//            index = 1,
//
//            labelString = "sdsd")
//    public boolean usePrivateRoute;
//    @SuppressLint("NonConstantResourceId")
//    @SettingItem(
//            type = SettingType.BOOLEAN,
//            index = 1,
//            //leftTextStyle = R.style.setting_smail_text,
//
//            labelString = "sdsdsd")
//    public boolean usePrivateRoute2;
//
//
//
//
//    @SuppressLint("NonConstantResourceId")
//    @SettingItem(
//
//            type = SettingType.EDIT_TEXT,
//            index = 2 ,
//
//            labelString = "sdsdsd")
//    public String prefixNanePrivateRoute="none";
//
//
//
//
//
//    @SuppressLint("NonConstantResourceId")
//    @SettingItem(
//            type = SettingType.COLOR,
//            index = 4,
//            labelString = "dsfsdf")
//    public String colorLineRoute="#42b0f5";
//
//
//
//
//
//
//
//
//
//
    @SuppressLint("NonConstantResourceId")
    @SettingItem(
            type = SettingType.EDIT_TEXT,
            index = 8,
            labelString = "sfosdifsd",
            InputType = InputType.TYPE_CLASS_NUMBER)
    public int lineRouteThickness=4;
//
//    @SettingItem(
//            type = SettingType.SPINNER,
//            spinnerList = "1,2,3,4,5",
//            valueWidthPercent = 40,
//            index = 9,
//            labelString = "Просто"
//    )
//    public int value;
//
//    @SettingItem(
//            type = SettingType.SPINNER,
//            spinnerList = "1,2,3,4,5,6",
//            index = 10,
//            labelString = "Simple int"
//    )
//    public int simpleInt=2;
//
//    @SettingItem(
//            type = SettingType.SPINNER,
//            spinnerList = "1.3,2.1,3.3,4.4,5.0,6.9",
//            index = 11,
//            labelString = "Просто float"
//    )
//    public float  name2=2.1f;

//    @SettingItem(
//            type = SettingType.DATE,
//            index = 11,
//            labelString = "My date"
//    )
//    public Date date;

//    @SettingItem(
//            type = SettingType.DATE,
//            index = 11,
//            labelString = "My date Local"
//    )
//    public LocalDate date2;
//
//    @SettingItem(
//            type = SettingType.DATE,
//            index = 11,
//            labelString = "My date Local time"
//    )
//    public LocalDateTime date3;
//
//    @SettingItem(
//            type = SettingType.DATE,
//            index = 11,
//            labelString = "My date long"
//    )
//    public long date4;
//
//    @SettingItem(
//            type = SettingType.DATE,
//            index = 11,
//            labelString = "Моя дата local"
//    )
//    public LocalDate dateL= LocalDate.now();
//
//
//
//
//
//
//
//    @SettingItem(
//            type = SettingType.DATETIME,
//            index = 11,
//            labelString = "DATETIME localTime",
//            dateFormat = " yyyy-MM-dd HH:mm",
//            valueWidthPercent = 40
//    )
//    public LocalDateTime dateTime= LocalDateTime.now();
//
//    @SettingItem(
//            type = SettingType.DATETIME,
//            index = 11,
//            labelString = "DATETIME local",
//            valueWidthPercent = 40
//    )
//    public LocalDate dateTime2;
//
    @SettingItem(
            type = SettingType.DATETIME,
            index = 11,
            labelString = "DATETIME date",
            dateFormat = " yyyy-MM-dd HH:mm",
            valueWidthPercent = 40
    )
    public Date dateTime3;
//
//    @SettingItem(
//            type = SettingType.DATETIME,
//            index = 11,
//            labelString = "DATETIME long",
//            dateFormat = " yyyy-MM-dd HH:mm",
//            valueWidthPercent = 40
//    )
//    public long dateTime4;
//
//
//
//    @SuppressLint("NonConstantResourceId")
//    @SettingItem(
//            type = SettingType.EDIT_TEXT,
//            index = 12 ,
//
//            InputType = InputType.TYPE_NUMBER_FLAG_DECIMAL,
//            labelString = "myFloat")
//    public float myFloat=45f;
//
//    @SuppressLint("NonConstantResourceId")
//    @SettingItem(
//            type = SettingType.EDIT_TEXT,
//            index = 13 ,
//            valueWidthPercent = 40,
//            hint = R.string.app_name,
//            contentDescription = R.string.app_name,
//
//            InputType = InputType.TYPE_CLASS_PHONE,
//            useTextEmpty = true,
//            labelString = "myPhone")
//    public String tele;
//
//    @SuppressLint("NonConstantResourceId")
//    @SettingItem(
//            type = SettingType.EDIT_TEXT,
//            index = 14 ,
//            valueWidthPercent = 40,
//            hint = R.string.app_name,
//            contentDescription = R.string.app_name,
//            InputType = InputType.TYPE_CLASS_NUMBER,
//            useTextEmpty = true,
//            labelString = "myINTEGER")
//    public Integer myInt;

}
