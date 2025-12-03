package com.bitnic.settings;



import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;


class BuilderEditText extends BaseBuilder {

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

        BuildLeftLabel(textViewLabel,ws,view);
        BuildToolTip(view.findViewById(R.id.toolTipHost),ws,context);
        BuildRightText(textViewValue,ws);









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
