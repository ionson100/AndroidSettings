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


@SettingsHost(leftWeight = 0.8f, rightWeight = 0.2f) //Настройка ширины название-значение по умолчанию 0.8 - 0.2
public class MySettings {

    // for usage
    // add gradle.property android.nonFinalResIds=false
    @SuppressLint("NonConstantResourceId")
    @SettingItem(
            toolTipStrRes = R.string.tt1,
            type = SettingType.BOOLEAN,
            index = 1,
            labelString = "Show private route")
    public boolean usePrivateRoute;

    @SuppressLint("NonConstantResourceId")
    @SettingItem(
            toolTipStrRes = R.string.tt1,
            type = SettingType.EDIT_TEXT,
            index = 2,
            labelStrRes = R.string.preficRoute)
    public String prefixNanePrivateRoute = "none";

    @SuppressLint("NonConstantResourceId")
    @SettingItem(
            toolTipStrRes = R.string.tt1,
            type = SettingType.COLOR,
            index = 4,
            labelString = "Color line route")
    public String colorLineRoute = "#42b0f5";

    @SuppressLint("NonConstantResourceId")
    @SettingItem(
            toolTipStrRes = R.string.tt1,
            type = SettingType.EDIT_TEXT,
            index = 8,
            labelString = "line Route Thickness",
            InputType = InputType.TYPE_CLASS_NUMBER)
    public int lineRouteThickness = 4;

    @SettingItem(
            type = SettingType.TITLE,
            labelString = "Группа настроек",
            index = 8
    )
    public String title;

    @SettingItem(
            toolTipStrRes = R.string.tt1,
            type = SettingType.SPINNER,
            spinnerList = "1,2,3,4,5",
            valueWidthPercent = 40,
            index = 9,
            labelString = "Simple value"
    )
    public int value = 3;

    @SettingItem(
            toolTipStrRes = R.string.tt1,
            type = SettingType.DATE,
            index = 11,
            labelString = "My date"
    )
    public Date date;

    @SettingItem(
            toolTipStrRes = R.string.tt1,
            type = SettingType.DATETIME,
            index = 11,
            labelString = "DATETIME localTime",
            dateFormat = " yyyy-MM-dd HH:mm",
            valueWidthPercent = 40
    )
    public LocalDateTime dateTime = LocalDateTime.now();

    @SettingItem(
            toolTipStrRes = R.string.tt1,
            type = SettingType.DATETIME,
            index = 11,
            labelString = "DATETIME date",
            dateFormat = " yyyy-MM-dd HH:mm",
            valueWidthPercent = 40
    )
    public Date dateTime3;

    @SettingItem(

            labelString = "Очистка кеша",
            type = SettingType.BUTTON,
            buttonText = "Очистить",
            index = 15,
            toolTipStrRes = R.string.clearCashe,
            buttonConfirm = "Очистить кеш, с возможной потерей данных?"
    )
    public int buttonAction;

    @SettingItem(
            labelString = "Просто нажми",
            type = SettingType.BUTTON_NOT_CONFIRM,
            index = 16,
            toolTipStrRes = R.string.clearCashe
    )
    public int buttonPress;
}
