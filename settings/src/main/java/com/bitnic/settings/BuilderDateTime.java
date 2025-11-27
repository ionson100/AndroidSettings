package com.bitnic.settings;


import static com.bitnic.settings.Utils.convertToLocalDateTimeViaInstant;
import static com.bitnic.settings.Utils.convertToLocalDateViaInstant;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


class BuilderDateTime {

    private final Context context;
    private final WrapperSettings ws;
    private final Object object;
    private final IActionSettings iAction;

    Object value;

    public BuilderDateTime(Context context, WrapperSettings ws, Object o, IActionSettings iAction) {

        this.context = context;
        this.ws = ws;
        this.object = o;
        this.iAction = iAction;
    }


    public LinearLayout build() {
        @SuppressLint("InflateParams")
        LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.settings_editext, null);
        TextView textViewLabel = view.findViewById(R.id.text_big);
        //TextView s=v.findViewById(R.id.text_smail);
        TextView textViewValue = view.findViewById(R.id.settings_value);
        textViewLabel.setTextAppearance(ws.item.leftTextStyle());
        textViewValue.setTextAppearance(ws.item.rightTextStyle());

        float l = ws.leftWeight;
        float r = ws.rightWeight;
        if (ws.item.valueWidthPercent() > 0) {
            r = (float) (0.01 * ws.item.valueWidthPercent());
            l = 1 - r;
        }
        LinearLayout.LayoutParams params = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, l);
        textViewLabel.setLayoutParams(params);

        LinearLayout.LayoutParams params2 = new TableRow.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, r);
        textViewValue.setLayoutParams(params2);
        if (ws.item.labelStrRes() != 0) {
            textViewLabel.setText(ws.item.labelStrRes());
        } else {
            textViewLabel.setText(String.valueOf(ws.item.labelString()));
        }


        LinearLayout host = view.findViewById(R.id.toolTipHost);
        if (ws.item.toolTipStrRes() != 0) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                host.setTooltipText(context.getResources().getText(ws.item.toolTipStrRes()));
            }

        }
        if (ws.item.contentDescription() != 0) {
            host.setContentDescription(context.getResources().getString(ws.item.contentDescription()));
        }


        try {
            value = ws.field.get(object);

            String strDate = String.valueOf(value);

            String format = ws.item.dateFormat();
            if (value == null) {
                value = new Date(0);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (value instanceof LocalDate) {
                    LocalDate localDate = (LocalDate) value;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ws.item.dateFormat());
                    strDate = localDate.format(formatter);

                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (value instanceof LocalDateTime) {
                    LocalDateTime localDate = (LocalDateTime) value;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ws.item.dateFormat());
                    strDate = localDate.format(formatter);

                }
            }
            if (value instanceof Date) {
                Date date = (Date) value;
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                strDate = formatter.format(date);

            }
            if (value instanceof Long) {
                Date date = new Date((Long) value);
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                strDate = formatter.format(date);

            }

            textViewValue.setText(strDate);


        } catch (IllegalAccessException e) {
            Log.e("BuilderEditText", "", e);
        }
        view.setOnClickListener(v -> {
            if (value == null) {
                value = new Date(0);
            }
            if (value instanceof Date) {
                Date date = (Date) value;

                final Calendar c = Calendar.getInstance();
                c.setTime(date);
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                int hour = c.get(Calendar.HOUR);
                int min = c.get(Calendar.MINUTE);
                init(view, year, month, day, hour, min);

            }
            if (value instanceof Long) {
                Date date = new Date((Long) value);
                final Calendar c = Calendar.getInstance();
                c.setTime(date);
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                int hour = c.get(Calendar.HOUR);
                int min = c.get(Calendar.MINUTE);
                init(view, year, month, day, hour, min);

            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (value instanceof LocalDate) {
                    LocalDate localDate = (LocalDate) value;
                    init(view, localDate.getYear(), localDate.lengthOfMonth(), localDate.getDayOfMonth(), -1, -1);
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (value instanceof LocalDateTime) {
                    LocalDateTime localDate = (LocalDateTime) value;
                    init(view, localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth(), localDate.getHour(), localDate.getMinute());
                }
            }


        });

        return view;

    }


    void init(LinearLayout view, int year, int month, int day, int hour, int min) {


        final View dialogView = View.inflate(context, R.layout.date_time_picker, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        dialogView.findViewById(R.id.bt_cancel).setOnClickListener(v -> {
            alertDialog.dismiss();
        });
        DatePicker datePicker = dialogView.findViewById(R.id.date_picker);
        datePicker.init(year, month, day, null);
        TimePicker timePicker = dialogView.findViewById(R.id.time_picker);
        if (hour >= 0 && min >= 0) {
            timePicker.setHour(hour);
            timePicker.setMinute(min);
        }
        dialogView.findViewById(R.id.bt_ok).setOnClickListener(view1 -> {


            Calendar calendar = new GregorianCalendar(
                    datePicker.getYear(),
                    datePicker.getMonth(),
                    datePicker.getDayOfMonth(),
                    timePicker.getCurrentHour(),
                    timePicker.getCurrentMinute());

            if (iAction != null) {
                Object val = null;
                if (ws.field.getType() == Date.class) {
                    val = calendar.getTime();

                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (ws.field.getType() == LocalDate.class) {
                        val = convertToLocalDateViaInstant(calendar.getTime());

                    } else if (ws.field.getType() == LocalDateTime.class) {

                        val = convertToLocalDateTimeViaInstant(calendar.getTime());

                    }
                } else if (ws.field.getType() == long.class || ws.field.getType() == Long.class) {

                    val = calendar.getTimeInMillis();
                }


                try {
                    ws.field.set(object, val);
                } catch (IllegalAccessException e) {
                    Log.e("settings", "dateTimePicker", e);
                }
                iAction.action(new ResultUpdate(ws.field.getName(), val));
            }


            alertDialog.dismiss();
        });
        alertDialog.setView(dialogView);
        alertDialog.show();
    }


}
