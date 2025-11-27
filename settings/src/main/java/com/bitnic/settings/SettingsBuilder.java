package com.bitnic.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Settings panel display class
 */
public class SettingsBuilder {

     float leftWeight =0.8f, rightWeight =0.2f;
    private final Context context;
    private final Object settings;
    private final IActionSettings onUpdateAction;
    private ListView v;
    private listAdapterSettings adapter;



    /**
     *
     * Constructor
     * @param context Context app
     * @param settings Object settings
     * @param listView ListView
     * @param onUpdateAction IActionSettings
     */
    public SettingsBuilder(AppCompatActivity context, Object settings, ListView listView, IActionSettings onUpdateAction){

        this.context = context;
        this.settings = settings;
        this.onUpdateAction = onUpdateAction;
        this.v=listView;
        SettingsHost host=settings.getClass().getAnnotation(SettingsHost.class);
        if(host!=null){
            this.leftWeight= host.leftWeight();
            this.rightWeight=host.rightWeight();
        }
        activate();
    }
    @SuppressLint("InflateParams")
    private void activate(){
        if(v==null){
            v = (ListView) LayoutInflater.from(context).inflate(R.layout.settings_host, null);
        }

        List<WrapperSettings>  settingsList=new ArrayList<>();

        List<Field> fields = getAllFields(settings.getClass());
        for (Field field : fields) {
            SettingItem item=field.getAnnotation(SettingItem.class);
            if(item!=null){
                settingsList.add(new WrapperSettings(field,item, leftWeight, rightWeight));
            }
        }
        settingsList.sort(Comparator.comparingInt(o -> o.item.index()));
        if(!settingsList.isEmpty()){
            adapter=new listAdapterSettings(context,0,settingsList,settings, onUpdateAction);
            v.setAdapter(adapter);
        }

    }


    /**
     * Menu update
     */
    public void  refresh(){
        adapter.notifyDataSetChanged();
    }

    private static List<Field> getAllFields(Class clazz) {

        List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));

        Class superClazz = clazz.getSuperclass();
        if (superClazz != null) {
            fields.addAll(getAllFields(superClazz));
        }

        return fields;
    }

}
