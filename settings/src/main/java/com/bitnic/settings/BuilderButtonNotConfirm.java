package com.bitnic.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


class BuilderButtonNotConfirm implements View.OnClickListener {

   private final Context context;
   private final WrapperSettings ws;

   private final IActionSettings iAction;

    public BuilderButtonNotConfirm(Context context, WrapperSettings ws,  IActionSettings iAction  ) {

       this.context = context;
       this.ws = ws;

       this.iAction = iAction;
   }

   public LinearLayout build(){
       @SuppressLint("InflateParams")
       LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.settings_button_not_confirm, null);
       TextView textViewLabel = view.findViewById(R.id.settings_text_big);





       if(ws.item.labelStrRes()!=0){
           textViewLabel.setText(ws.item.labelStrRes());
       }else{
           textViewLabel.setText(String.valueOf(ws.item.labelString()));
       }








       LinearLayout host=view.findViewById(R.id.toolTipHost);
       host.setOnClickListener(this);
       if(ws.item.toolTipStrRes()!=0){

           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
               host.setTooltipText(context.getResources().getText(ws.item.toolTipStrRes()));
           }

       }
       if(ws.item.contentDescription()!=0){
           host.setContentDescription(context.getResources().getString(ws.item.contentDescription()));
       }


       textViewLabel.setTextAppearance(ws.item.leftTextStyle());

       textViewLabel.setTextAppearance(ws.item.leftTextStyle());

       return view;

   }

    @Override
    public void onClick(View v) {
        if(iAction!=null){
            iAction.action(new ResultUpdate(ws.field.getName(),true));
        }
    }


}
