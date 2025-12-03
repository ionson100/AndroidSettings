package com.bitnic.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import yuku.ambilwarna.AmbilWarnaDialog;


class BuilderColor extends BaseBuilder {

    private final Context context;
    private final WrapperSettings ws;
    private final Object o;
    private final IActionSettings iAction;

    public BuilderColor(Context context, WrapperSettings ws, Object o, IActionSettings iAction) {

        this.context = context;
        this.ws = ws;
        this.o = o;
        this.iAction = iAction;
    }

    public LinearLayout build() {
        @SuppressLint("InflateParams") LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.settings_color, null);
        TextView textViewLabel = view.findViewById(R.id.text_big);
        TextView textViewValue = view.findViewById(R.id.text_value);
        BuildLeftLabel(textViewLabel, ws, view);
        BuildToolTip(view.findViewById(R.id.toolTipHost), ws, context);
        BuildRightText(textViewValue,ws);






        try {
            Object ss = ws.field.get(o);
            if (ss != null) {
                textViewValue.setText(String.valueOf(ss));
                try {
                    int color = Color.parseColor(String.valueOf(ss));
                    textViewValue.setBackgroundColor(color);
                } catch (Exception ignored) {

                }
            }

        } catch (IllegalAccessException e) {
            Log.e("BuilderColor", "", e);
        }
        view.setOnClickListener(v -> {

            AmbilWarnaDialog dialog = new AmbilWarnaDialog(context, Color.parseColor(textViewValue.getText().toString()),
                    false, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                @Override
                public void onOk(AmbilWarnaDialog dialog, int color) {
                    String strColor = intToHexString(color, false);
                    textViewValue.setText(strColor);
                    textViewValue.setBackgroundColor(color);

                    if (iAction != null) {
                        try {
                            ws.field.set(o, strColor);
                        } catch (IllegalAccessException e) {
                            Log.e("SettingsColor", "", e);
                        }
                        iAction.action(new ResultUpdate(ws.field.getName(), strColor));
                    }


                }

                @Override
                public void onCancel(AmbilWarnaDialog dialog) {
                    Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();

        });
        return view;

    }

    public String intToHexString(int color, boolean includeAlpha) {
        if (includeAlpha) {
            return String.format("#%08X", color);
        } else {
            return String.format("#%06X", (0xFFFFFF & color));
        }
    }
}
