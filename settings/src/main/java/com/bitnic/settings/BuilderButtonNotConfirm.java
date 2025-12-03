package com.bitnic.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


class BuilderButtonNotConfirm extends BaseBuilder implements View.OnClickListener {

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

       BuildLeftLabel(textViewLabel,ws,view);








       LinearLayout host=view.findViewById(R.id.toolTipHost);
       host.setOnClickListener(this);
       BuildToolTip(host,ws,context);





       return view;

   }

    @Override
    public void onClick(View v) {
        if(iAction!=null){
            iAction.action(new ResultUpdate(ws.field.getName(),true));
        }
    }


}
