package com.bitnic.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;




 class BuilderBoolean extends BaseBuilder implements CompoundButton.OnCheckedChangeListener {

    private final Context context;
    private final WrapperSettings ws;
    private final Object o;
    private final IActionSettings iAction;

    public BuilderBoolean(Context context, WrapperSettings ws, Object o, IActionSettings iAction  ) {

        this.context = context;
        this.ws = ws;
        this.o = o;
        this.iAction = iAction;
    }

    public LinearLayout build(){
        @SuppressLint("InflateParams")
        LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.settings_boolean, null);
        TextView textViewLabel=view.findViewById(R.id.settings_text_big);

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        final Switch aSwitch=view.findViewById(R.id.settings_value);

        BuildLeftLabel(textViewLabel,ws,view);



        BuildToolTip(view.findViewById(R.id.toolTipHost),ws,context);





        

        float l=ws.leftWeight;
        float r=ws.rightWeight;
        if(ws.item.valueWidthPercent()>0){
            r= (float) (0.01*ws.item.valueWidthPercent());

        }


        LinearLayout.LayoutParams params2 = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, r);
        aSwitch.setLayoutParams(params2);






        try {
            var res=ws.field.get(o);
            if(res!=null){
                aSwitch.setChecked((Boolean)res );
            }

        } catch (IllegalAccessException e) {
            Log.e("BuilderBoolean","",e);
        }
        aSwitch.setOnCheckedChangeListener(this);
        view.setOnClickListener(v -> {
            boolean s=aSwitch.isChecked();
            aSwitch.setChecked(!s);
        });

        return view;

    }

    @Override
    public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
        if(iAction!=null){
            try {
                ws.field.set(o,isChecked);
            } catch (IllegalAccessException e) {
                Log.e("settings","boolean",e);
            }
            iAction.action(new ResultUpdate(ws.field.getName(),isChecked));
        }

    }
}
