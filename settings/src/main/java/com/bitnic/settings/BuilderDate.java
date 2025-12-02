package com.bitnic.settings;


import static com.bitnic.settings.Utils.convertToLocalDateTimeViaInstant;
import static com.bitnic.settings.Utils.convertToLocalDateViaInstant;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


class BuilderDate {

    private final Context context;
    private final WrapperSettings ws;
    private final Object object;
    private final IActionSettings iAction;

    Object value;

    public BuilderDate(Context context, WrapperSettings ws, Object o, IActionSettings iAction) {

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

            if (value == null) {
                value = new Date(0);

            }



            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (value instanceof LocalDate) {
                    LocalDate localDate = (LocalDate) value;
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ws.item.dateFormat());
                    strDate= localDate.format(formatter);

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
                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat(ws.item.dateFormat());
                strDate = formatter.format(date);
            }
            if (value instanceof Long) {
                Date date = new Date((long) value);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat(ws.item.dateFormat());
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
                getDatePickerDialog(year, month, day).show();

            }
            if (value instanceof Long) {
                Date date = new Date((long) value);
                final Calendar c = Calendar.getInstance();
                c.setTime(date);
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                getDatePickerDialog(year, month, day).show();
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (value instanceof LocalDateTime) {
                    LocalDateTime localDate = (LocalDateTime) value;
                    getDatePickerDialog(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()).show();
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (value instanceof LocalDate) {
                    LocalDate localDate = (LocalDate) value;
                    getDatePickerDialog(localDate.getYear(), localDate.lengthOfMonth(), localDate.getDayOfMonth()).show();
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (value instanceof LocalDateTime) {
                    LocalDateTime localDate = (LocalDateTime) value;
                    getDatePickerDialog(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()).show();
                }
            }


        });
        return view;

    }


    @NonNull
    private DatePickerDialog getDatePickerDialog(int year, int month, int day) {

        return  new DatePickerDialog(
                context,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    if (iAction != null) {
                        Object val = null;
                        try {
                            if (ws.field.getType() == Date.class) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year1, monthOfYear, dayOfMonth);
                                val = calendar.getTime();


                            } else if (ws.field.getType() == long.class || ws.field.getType() == Long.class) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year1, monthOfYear, dayOfMonth);
                                val = calendar.getTime().getTime();
                            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                if (ws.field.getType() == LocalDate.class) {
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.set(year1, monthOfYear, dayOfMonth);
                                    val = convertToLocalDateViaInstant(calendar.getTime());

                                } else if (ws.field.getType() == LocalDateTime.class) {
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.set(year1, monthOfYear, dayOfMonth);
                                    val = convertToLocalDateTimeViaInstant(calendar.getTime());

                                }
                            }
                            ws.field.set(object, val);
                        } catch (IllegalAccessException e) {
                            Log.e("settings", "spinner", e);
                        }
                        iAction.action(new ResultUpdate(ws.field.getName(), val));
                    }
                },
                year,
                month,
                day
        );
    }


}
