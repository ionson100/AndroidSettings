package com.bitnic.settings;



import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;


class BuilderEditText {

    private final Context context;
    private final WrapperSettings ws;
    private final Object o;
    private final IActionSettings iAction;

    public BuilderEditText(Context context, WrapperSettings ws, Object o, IActionSettings iAction  ) {

        this.context = context;
        this.ws = ws;
        this.o = o;
        this.iAction = iAction;
    }
    public LinearLayout build(){
        @SuppressLint("InflateParams")
        LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.settings_editext, null);
        TextView textViewLabel=view.findViewById(R.id.text_big);
        //TextView s=v.findViewById(R.id.text_smail);
        TextView textViewValue =view.findViewById(R.id.settings_value);
        textViewLabel.setTextAppearance(ws.item.leftTextStyle());
        textViewValue.setTextAppearance(ws.item.rightTextStyle());

        float l=ws.leftWeight;
        float r=ws.rightWeight;
        if(ws.item.valueWidthPercent()>0){
            r= (float) (0.01*ws.item.valueWidthPercent());
            l=1-r;
        }
        LinearLayout.LayoutParams params = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, l);
        textViewLabel.setLayoutParams(params);

        LinearLayout.LayoutParams params2 = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, r);
        textViewValue.setLayoutParams(params2);
        if(ws.item.labelStrRes()!=0){
            textViewLabel.setText(ws.item.labelStrRes());
        }else{
            textViewLabel.setText(String.valueOf(ws.item.labelString()));
        }



        LinearLayout host=view.findViewById(R.id.toolTipHost);
        if(ws.item.toolTipStrRes()!=0){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                host.setTooltipText(context.getResources().getText(ws.item.toolTipStrRes()));
            }

        }
        if(ws.item.contentDescription()!=0){
            host.setContentDescription(context.getResources().getString(ws.item.contentDescription()));
        }


        try {
            Object ss=ws.field.get(o);
            if (ss!=null){
                textViewValue.setText(String.valueOf(ss));
            }

        } catch (IllegalAccessException e) {
            Log.e("BuilderEditText","",e);
        }
        view.setOnClickListener(v -> {

            DialogEditText editText=new DialogEditText(context);
            editText.iActionSettings=iAction;
            editText.settings=o;
            editText.ws=ws;






            try {
                editText.show();
            }catch (Exception ds){
                ds.printStackTrace();
            }


        });
        return view;

    }
}
