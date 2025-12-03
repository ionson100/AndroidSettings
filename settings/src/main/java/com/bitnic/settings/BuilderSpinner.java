package com.bitnic.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.RequiresApi;


class BuilderSpinner extends BaseBuilder  implements AdapterView.OnItemSelectedListener  {

   private final Context context;
   private final WrapperSettings ws;
   private final Object object;
   private final IActionSettings iAction;
   private String[] ls=new String[0];
   boolean isStart=false;



   public BuilderSpinner(Context context, WrapperSettings ws, Object o, IActionSettings iAction  ) {

       this.context = context;
       this.ws = ws;
       this.object = o;
       this.iAction = iAction;
   }

   @RequiresApi(api = Build.VERSION_CODES.O)
   public LinearLayout build(){
       @SuppressLint("InflateParams")
       LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.settings_spinner, null);
       TextView textViewLabel=view.findViewById(R.id.settings_text_big);

       @SuppressLint("UseSwitchCompatOrMaterialCode")
       final Spinner spinner=view.findViewById(R.id.settings_value);


       LinearLayout host=view.findViewById(R.id.toolTipHost);
       BuildLeftLabel(textViewLabel,ws,view);
       BuildToolTip(host,ws,context);
       host.setOnClickListener(v -> {
           spinner.performClick();
       });



       float l=ws.leftWeight;
       float r=ws.rightWeight;
       if(ws.item.valueWidthPercent()>0){
           r= (float) (0.01*ws.item.valueWidthPercent());

       }


       LinearLayout.LayoutParams params2 = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, r);
       spinner.setLayoutParams(params2);
       spinner.setOnItemSelectedListener(this);
       ls=ws.item.spinnerList().split(",");


       ArrayAdapter<String> ad = new ArrayAdapter<>(
               context,
               //android.R.layout.simple_spinner_item,
               R.layout.spinner_item,
               ls
       );

       // Set simple layout resource file for each item of spinner
       ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

       // Set the ArrayAdapter (ad) data on the Spinner which binds data to spinner
       spinner.setAdapter(ad);
       try {
           Object ss=ws.field.get(object);
           if (ss!=null){
               String val=String.valueOf(ss);
               for (int i = 0; i < ls.length; i++) {
                   if(ls[i].equals(val)){
                       spinner.setSelection(i);
                       break;
                   }

               }
           }

       } catch (IllegalAccessException e) {
           Log.e("BuilderEditText","",e);
       }




       return view;

   }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       if(isStart==false){
           isStart=true;
           return;
       }
       Object value=ls[position];
        if(iAction!=null){
            try {
                if(ws.field.getType()==int.class){
                    value=Integer.parseInt(ls[position]);
                }else  if(ws.field.getType()==double.class){
                    value=Double.parseDouble(ls[position]);
                }else if(ws.field.getType()==float.class){
                    value=Float.parseFloat(ls[position]);
                }
                ws.field.set(object,value);
            } catch (IllegalAccessException e) {
                Log.e("settings","spinner",e);
            }
           var sd= iAction.action(new ResultUpdate(ws.field.getName(),value));
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
