package com.bitnic.settings;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.List;


 class listAdapterSettings extends ArrayAdapter<WrapperSettings> {

    private final Context context;
    private final Object settings;
    private final IActionSettings onUpdateaction;

    public listAdapterSettings(Context context, int resource, List<WrapperSettings> objects, Object settings, IActionSettings onUpdateaction) {
        super(context, resource, objects);
        this.context = context;
        this.settings = settings;
        this.onUpdateaction = onUpdateaction;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View mView = new LinearLayout(context);
        final WrapperSettings p = getItem(position);

        switch (p.item.type()){
            case EDIT_TEXT:{
               mView=new BuilderEditText(context, p, settings, onUpdateaction).build();
                break;
            }
            case BOOLEAN:{
                mView=new BuilderBoolean(context, p, settings, onUpdateaction).build();
                break;
            }

            case COLOR:{
                mView=new BuilderColor(context, p, settings, onUpdateaction).build();
                break;
            }
            case SPINNER:{
                mView=new BuilderSpinner(context, p, settings, onUpdateaction).build();
                break;
            }
            case DATE:{
                mView=new BuilderDate(context, p, settings, onUpdateaction).build();
                break;
            }
            case DATETIME:{
                mView=new BuilderDateTime(context, p, settings, onUpdateaction).build();
                break;
            }
            case BUTTON:{
                mView=new BuilderButton(context, p, settings, onUpdateaction).build();
                break;
            }

        }

        return mView;
    }
}
