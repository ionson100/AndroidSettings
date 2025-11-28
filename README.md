#### Библиотека для создания станиц настроек.
[![](https://jitpack.io/v/ionson100/AndroidSettings.svg)](https://jitpack.io/#ionson100/AndroidSettings)
compileSdk 36  minSdk = 24\
Для использования, добавить в целевой проект в файл gradle.property строку android.nonFinalResIds=false\
Разметка  полей настроек основана на атрибуте ```SettingItem```
### Поля аннотации:
 
##### type 
Тип визуализации поля в настройках
```java

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
     *             toolTipStrRes = R.string.clearСache,
     *             buttonConfirm = "Clear cache, with possible data loss?"
     *  )
     *   public int buttonAction;
     * }
     * </pre>
     */
    BUTTON

}
```
```java
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
     * Name settings resource reference
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

    /**
     * Text button
     * @return String
     */
    String buttonText() default "";

    /**
     * text button from string res
     * @return int
     */
    @StringRes int buttonTextStrRes() default 0;

    /**
     * text confirm dialog
     * @return String
     */
    String buttonConfirm() default "";

    /**
     * text confirm dialog from string res
     * @return int
     */
    @StringRes int buttonConfirmStrRes() default 0;

}
```
##### Пример класс настройки

```java
@SettingsHost(leftWeight = 50f, rightWeight = 50f) //Настройка ширины название-значение по умолчанию 0.8 - 0.2
public class MySettings {

    // for usage
    // add gradle.property android.nonFinalResIds=false
    @SuppressLint("NonConstantResourceId")
    @SettingItem(
            type = SettingType.BOOLEAN,
            index = 1,
            labelString = "Show private route")
    public boolean usePrivateRoute;

    @SuppressLint("NonConstantResourceId")
    @SettingItem(
            type = SettingType.EDIT_TEXT,
            index = 2,
            labelStrRes = R.string.preficRoute)
    public String prefixNanePrivateRoute = "none";

    @SuppressLint("NonConstantResourceId")
    @SettingItem(
            type = SettingType.COLOR,
            index = 4,
            labelString = "Color line route")
    public String colorLineRoute = "#42b0f5";

    @SuppressLint("NonConstantResourceId")
    @SettingItem(
            type = SettingType.EDIT_TEXT,
            index = 8,
            labelString = "line Route Thickness",
            InputType = InputType.TYPE_CLASS_NUMBER)
    public int lineRouteThickness = 4;

    @SettingItem(
            type = SettingType.SPINNER,
            spinnerList = "1,2,3,4,5",
            valueWidthPercent = 40,
            index = 9,
            labelString = "Simple value"
    )
    public int value = 3;

    @SettingItem(
            type = SettingType.DATE,
            index = 11,
            labelString = "My date"
    )
    public Date date;

    @SettingItem(
            type = SettingType.DATETIME,
            index = 11,
            labelString = "DATETIME localTime",
            dateFormat = " yyyy-MM-dd HH:mm",
            valueWidthPercent = 40
    )
    public LocalDateTime dateTime = LocalDateTime.now();

    @SettingItem(
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
}
```
#### Вызов настройки
Шаблон:
```xml
  <ListView
    ndroid:scrollbars="vertical"
    ndroid:id="@+id/list_settings"
    ndroid:layout_width="match_parent"
    ndroid:layout_height="match_parent"/>
```
В коде:
```kotlin
class MainActivity : AppCompatActivity() {


    var mySettings: MySettings = MySettings()
    var settingsBuilder: SettingsBuilder;

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
```
##### Подключение
```java

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

dependencies {
    implementation("com.github.ionson100:AndroidSettings:v1.0.2")
}
```
       
