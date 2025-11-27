package com.bitnic.settings;

import android.text.InputType;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class-level annotation, describes how this field will be displayed
 * in the settings panel
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SettingItem {

    /**
     * Setting display type
     *
     * @return SettingsType
     */
    SettingType type();

    /**
     * Setting display index in the settings panel
     * @return int
     */
    int index() default 0;

    /**
     * Setting name
     * @return String name setting
     */
    String labelString() default "";

    /**
     * Setting name (string resource reference)
     * @return string resource reference
     */
    @StringRes int labelStrRes() default 0;

    /**
     * ToolTip panel settings (string resource reference)
     * @return string resource reference
     */
    @StringRes int toolTipStrRes() default 0;

    /**
     * Setting name style
     * @return style resource reference
     */
    @StyleRes int leftTextStyle() default 0;

    /**
     * Value display style
     * @return style resource reference
     */
    @StyleRes int rightTextStyle() default 0;

    /**
     * A string of spinner values; values ​​must be separated by commas.
     * @return String
     * <pre>
     * {@code
     * @SettingItem(
     * type = SettingType.SPINNER,
     * spinnerList = "1,2,3,4,5",
     * valueWidthPercent = 40,
     * index = 9,
     * labelString = "Simple"
     * )
     * public int value;
     * }
     * </pre>
     */
    String spinnerList() default "1,2,3,4";

    /**
     * Width of the value part in the setting line
     * @return String
     * <pre>
     * {@code
     * @SettingItem(
     * type = SettingType.SPINNER,
     * spinnerList = "1,2,3,4,5",
     * valueWidthPercent = 40,
     * index = 9,
     * labelString = "Simple"
     *)
     * public int value;
     * }
     * </pre>
     */
    int valueWidthPercent() default 0;

    /**
     * Displayed date format string
     * @return String
     * <pre>
     * {@code
     * @SettingItem(
     * type = SettingType.DATETIME,
     * index = 11,
     * labelString = "My local date",
     * dateFormat = "yyyy-MM-dd HH:mm",
     * valueWidthPercent = 40
     * )
     * public LocalDateTime dateTime= LocalDateTime.now();
     * }
     * </pre>
     */
    String dateFormat() default "yyyy-MM-dd";

    /**
     * Edit box hint (string resource reference)
     * @return int (string resource reference)
     */
    @StringRes int hint() default 0;

    /**
     * Accessibility setting description (string resource reference)
     * @return int (string resource reference)
     */
    @StringRes int contentDescription() default 0;

    /**
     * Option to save an empty string when editing a setting.
     * @return boolean
     *
     * <pre>
     * {@code
     * @SettingItem(
     * type = SettingType.EDIT_TEXT,
     * index = 14 ,
     * valueWidthPercent = 40,
     * hint = R.string.app_name,
     * contentDescription = R.string.app_name,
     * InputType = InputType.TYPE_CLASS_NUMBER,
     * useTextEmpty = true,
     * labelString = "myINTEGER")
     * public Integer myInt;
     * }
     * </pre>
     */
    boolean useTextEmpty() default false;

    /**
     * Bit definitions for an integer defining the basic content type of text held in an Editable object.
     * Supported classes may be combined with variations and flags to indicate desired behaviors.
     * @return int
     *
     * <pre>
     * {@code
     * @SettingItem(
     * type = SettingType.EDIT_TEXT,
     * index = 8,
     * labelStrRes = R.string.routeLineThickness,
     * InputType = InputType.TYPE_CLASS_NUMBER)
     * public int lineRouteThickness=4;
     * }
     * </pre>
     */
    int InputType() default InputType.TYPE_CLASS_TEXT;

}