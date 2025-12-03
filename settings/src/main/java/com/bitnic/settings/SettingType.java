package com.bitnic.settings;

/**
 * Setting display types
 */
public enum SettingType {

    /**
     * The value is displayed as a string; a dialog is called for editing.
     * <pre>
     * {@code
     * @SettingItem(
     * type = SettingType.EDIT_TEXT,
     * index = 2 ,
     * toolTipStrRes = R.string.tool22,
     * hint = R.string.hint22,
     * contentDescription = R.string.content22,
     * labelStrRes = R.string.prefixPrivate)
     * public String prefixNanePrivateRoute="none";
     * }
     * </pre>
     */
    EDIT_TEXT,

    /**
     *
     * <pre>
     * {@code
     * @SettingItem(
     * type = SettingType.BOOLEAN,
     * index = 1,
     * toolTipStrRes = R.string.tool21,
     * contentDescription = R.string.content21,
     * labelStrRes = R.string.usePrivate)
     * public boolean usePrivateRoute;
     * }
     * </pre>
     */
    BOOLEAN,

    /**
     * Color settings. The color palette dialog is called for editing.
     * <pre>
     * {@code
     * @SettingItem(
     * type = SettingType.COLOR,
     * index = 4,
     * labelStrRes = R.string.colorLineRoute)
     * public String colorLineRoute="#42b0f5";
     * }
     * </pre>
     */
    COLOR,

    /**
     *
     * Settings where the value is selected from a drop-down list
     * <pre>
     * {@code
     * @SettingItem(
     * type = SettingType.SPINNER,
     * spinnerList = "1,2,3,4,5,6",
     * index = 10,
     * labelString = "Simple int"
     * )
     * public int simpleInt=2;
     * }
     * </pre>
     */
    SPINNER,

    /**
     *
     * Date setting, calls the date picker dialog (Date, LocalDate, LocalDateTime, long)
     * <pre>
     * {@code
     * @SettingItem(
     * type = SettingType.DATE,
     * index = 11,
     * labelString = "My date"
     * )
     * public Date date= new Date();
     * }
     * </pre>
     */
    DATE,

    /**
     * Sets the date and time; the date and time selection dialog is called.
     * (Date, LocalDate, LocalDateTime, long)
     * <pre>
     * {@code
     * @SettingItem(
     * type = SettingType.DATETIME,
     * index = 11,
     * labelString = "My local date",
     * dateFormat = " yyyy-MM-dd HH:mm",
     * valueWidthPercent = 40
     * )
     * public LocalDateTime dateTime= LocalDateTime.now();
     * }
     * </pre>
     */
    DATETIME,


    /**
     *Ability to perform actions
     * <pre>
     * {@code
     *  @SettingItem(
     *             labelString = "Clearing the cache",
     *             type = SettingType.BUTTON,
     *             buttonText = "Очистить",
     *             index = 15,
     *             toolTipStrRes = R.string.clearCache,
     *             buttonConfirm = "Clear cache, with possible data loss?"
     *  )
     *   public int buttonAction;
     * }
     * </pre>
     */
    BUTTON,

    /**
     * Simple clickable menu
     * <pre>
     * {@code
     *  @SettingItem(
     *             labelString = "Press my",
     *             type = SettingType.BUTTON_NOT_CONFIRM,
     *             index = 15,
     *             toolTipStrRes = R.string.clearCache,
     *  )
     *   public int buttonAction;
     * }
     * </pre>
     */
    BUTTON_NOT_CONFIRM,

    TITLE

}