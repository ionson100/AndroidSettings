package com.bitnic.settings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.math.BigDecimal;
import java.util.Objects;


 class DialogEditText extends DialogFragment {

    public IActionSettings iActionSettings;

    public WrapperSettings ws;
    private EditText editText;
    public Object settings;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.settings_dialog_edit_text, null);

        if (this.ws.item.labelStrRes() != 0) {
            ((TextView) view.findViewById(R.id.title_dialog)).setText(this.ws.item.labelStrRes());
        } else {
            ((TextView) view.findViewById(R.id.title_dialog)).setText(this.ws.item.labelString());
        }

        try {
            editText = view.findViewById(R.id.edit_text);
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

        view.findViewById(R.id.bt_close).setOnClickListener(v1 -> dismiss());

        view.findViewById(R.id.bt_save).setOnClickListener(v2 -> {
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

        builder.setView(view);


        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);

        return dialog;
    }

    public void show() {
    }
}
