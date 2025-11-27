package com.bitnic.settings;



import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Objects;

public class NewDialogEditText extends Dialog {


    @NonNull
    private final Context context;
    public IActionSettings iActionSettings;
    public WrapperSettings ws;
    private EditText editText;
    public Object settings;
    public NewDialogEditText(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.settings_dialog_edit_text);

        var sd=findViewById(R.id.title_dialog);

        if (this.ws.item.labelStrRes() != 0) {
            ((TextView) findViewById(R.id.title_dialog)).setText(this.ws.item.labelStrRes());
        } else {
            ((TextView) findViewById(R.id.title_dialog)).setText(this.ws.item.labelString());
        }

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dialogWidth = (int) (displayMetrics.widthPixels * 0.9); // 80% от ширины экрана
        var view=findViewById(R.id.base_123);
        LinearLayout.LayoutParams params = new TableRow.LayoutParams(dialogWidth,LinearLayout.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);



        try {
            editText = findViewById(R.id.edit_text);
            editText.setInputType(ws.item.InputType());
            if (ws.item.hint() != 0) {
                editText.setHint(ws.item.hint());
            }
            if (ws.item.contentDescription() != 0) {
                editText.setContentDescription(getContext().getResources().getString(ws.item.contentDescription()));
            }
            Object s = ws.field.get(settings);
            if (s != null) {
                editText.setText(String.valueOf(s));
            }

        } catch (IllegalAccessException e) {
            Log.e("DialogEditText", Objects.requireNonNull(e.getMessage()));
        }

        findViewById(R.id.bt_close).setOnClickListener(v1 -> dismiss());

        findViewById(R.id.bt_save).setOnClickListener(v2 -> {
            if (ws.item.useTextEmpty() == false) {
                if (editText.getText().toString().trim().isEmpty()) {
                    editText.setError("Пустое поле!");
                    return;
                }
            }

            try {
                String str = editText.getText().toString().trim();
                if (ws.field.getType() == Integer.class) {
                    if (str.isEmpty()) {
                        ws.field.set(settings, null);
                    } else {
                        ws.field.set(settings, Integer.parseInt(str));
                    }
                } else if (ws.field.getType() == Float.class) {
                    if (str.isEmpty()) {
                        ws.field.set(settings, null);
                    } else {
                        ws.field.set(settings, Float.parseFloat(str));
                    }
                } else if (ws.field.getType() == Double.class) {
                    if (str.isEmpty()) {
                        ws.field.set(settings, null);
                    } else {
                        ws.field.set(settings, Double.parseDouble(str));
                    }
                } else if (ws.field.getType() == Long.class) {
                    if (str.isEmpty()) {
                        ws.field.set(settings, null);
                    } else {
                        ws.field.set(settings, Long.parseLong(str));
                    }
                } else if (ws.field.getType() == int.class) {
                    ws.field.set(settings, Integer.parseInt(str));
                } else if (ws.field.getType() == double.class) {
                    ws.field.set(settings, Double.parseDouble(str));
                } else if (ws.field.getType() == float.class) {
                    ws.field.set(settings, Float.parseFloat(str));
                } else if (ws.field.getType() == long.class) {
                    ws.field.set(settings, Long.parseLong(str));
                } else if (ws.field.getType() == BigDecimal.class) {
                    if (str.isEmpty()) {
                        ws.field.set(settings, null);
                    } else
                        ws.field.set(settings, new BigDecimal(str));
                } else {
                    ws.field.set(settings, str);
                }


                iActionSettings.action(new ResultUpdate(ws.field.getName(), str));
                dismiss();
            } catch (Exception e) {
                Log.e("DialogEditText", Objects.requireNonNull(e.getMessage()));
            }


        });
        editText.requestFocus();



    }

}
