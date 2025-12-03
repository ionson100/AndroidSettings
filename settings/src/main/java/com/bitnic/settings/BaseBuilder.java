package com.bitnic.settings;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class BaseBuilder {
    public void BuildLeftLabel(TextView textView, WrapperSettings ws, LinearLayout view){
        if(ws.item.labelStrRes()!=0){
            textView.setText(ws.item.labelStrRes());
        }else{
            textView.setText(String.valueOf(ws.item.labelString()));
        }
        if(ws.item.leftTextStyle()==0){
            textView.setTextAppearance(R.style.text_1);
        }else{
            textView.setTextAppearance(ws.item.leftTextStyle());
        }
        float l=ws.leftWeight;
        float r=ws.rightWeight;
        if(ws.item.valueWidthPercent()>0){
            l=1-r;
        }
        LinearLayout.LayoutParams params = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, l);
        textView.setLayoutParams(params);
    }

    public void BuildToolTip(LinearLayout  host, WrapperSettings ws, Context context){
        if(ws.item.toolTipStrRes()!=0){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                host.setTooltipText(context.getResources().getText(ws.item.toolTipStrRes()));
            }

        }
        if(ws.item.contentDescription()!=0){
            host.setContentDescription(context.getResources().getString(ws.item.contentDescription()));
        }
    }

    public void BuildRightText(TextView view,WrapperSettings ws){

        if(ws.item.leftTextStyle()==0){
            view.setTextAppearance(R.style.text_2);
        }else{
            view.setTextAppearance(ws.item.leftTextStyle());
        }

        float r=ws.rightWeight;
        if(ws.item.valueWidthPercent()>0){
            r= (float) (0.01*ws.item.valueWidthPercent());
        }
        LinearLayout.LayoutParams params2 = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, r);
        view.setLayoutParams(params2);

    }
}
