package com.bitnic.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;


class BuilderButton extends BaseBuilder implements View.OnClickListener {

   private final Context context;
   private final WrapperSettings ws;
   private final Object o;
   private final IActionSettings iAction;

    private TextView textViewLabel;

   public BuilderButton(Context context, WrapperSettings ws, Object o, IActionSettings iAction  ) {

       this.context = context;
       this.ws = ws;
       this.o = o;
       this.iAction = iAction;
   }

   public LinearLayout build(){
       @SuppressLint("InflateParams")
       LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.settings_button, null);
       textViewLabel=view.findViewById(R.id.settings_text_big);

       @SuppressLint("UseSwitchCompatOrMaterialCode")
       final TextView button =view.findViewById(R.id.settings_value);

       button.setOnClickListener(this);

       BuildLeftLabel(textViewLabel,ws,view);
       BuildToolTip(view.findViewById(R.id.toolTipHost),ws,context);
       BuildRightText(button,ws);

       if(ws.item.buttonTextStrRes()==0){
           button.setText(ws.item.buttonText());
       }else{
           button.setText(ws.item.buttonTextStrRes());
       }

       return view;

   }

    @Override
    public void onClick(View v) {
       String confirm;
       if(ws.item.buttonConfirmStrRes()!=0){
           confirm=context.getResources().getString(ws.item.buttonConfirmStrRes());
       }else{
           confirm=ws.item.buttonConfirm();
       }
       if(!confirm.isEmpty())
        new AlertDialog.Builder(context)
                .setTitle(textViewLabel.getText())
                .setMessage(confirm)
                .setPositiveButton(context.getResources().getString(android.R.string.yes), (dialog, which) -> {
                    if(iAction!=null){
                        iAction.action(new ResultUpdate(ws.field.getName(),true));
                    }
                })
                .setNegativeButton(android.R.string.no, (dialog, which) -> {
                    if(iAction!=null){
                        iAction.action(new ResultUpdate(ws.field.getName(),false));
                    }
                })
                .show();
    }


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
