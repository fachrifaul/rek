package com.reka.tour.hotel.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.reka.tour.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HotelActivity extends AppCompatActivity {
    @Bind(R.id.ev_kota)
    EditText evKota;

    @Bind(R.id.layout_checkin)
    LinearLayout layoutCheckIn;
    @Bind(R.id.tv_day_checkin)
    TextView tvDayCheckin;
    @Bind(R.id.tv_date_checkin)
    TextView tvDateCheckin;
    @Bind(R.id.tv_month_year_checkin)
    TextView tvMonthYearCheckin;


    @Bind(R.id.layout_checkout)
    LinearLayout layoutCheckOut;
    @Bind(R.id.tv_day_checkout)
    TextView tvDayCheckout;
    @Bind(R.id.tv_date_checkout)
    TextView tvDateCheckout;
    @Bind(R.id.tv_month_year_checkout)
    TextView tvMonthYearCheckout;

    @Bind(R.id.ev_tamu)
    EditText evTamu;
    @Bind(R.id.ev_kamar)
    EditText evKamar;

    @Bind(R.id.tv_cari_hotel)
    TextView tvCariHotel;

    private SimpleDateFormat dayFormatter;
    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat monthYearFormatter;
    private SimpleDateFormat dateDefaultFormatter;
    private Calendar newCalendar;

    private String dateCheckin;
    private String dateCheckout;
    private int HOTEL_KOTA = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        ButterKnife.bind(this);

        ((Toolbar) findViewById(R.id.toolbar)).setNavigationIcon(R.drawable.back);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));


        setValue();
        setCallBack();
    }

    private void setValue() {
        newCalendar = Calendar.getInstance();
        dayFormatter = new SimpleDateFormat("EEEE", new Locale("ind", "IDN"));
        dateFormatter = new SimpleDateFormat("dd", new Locale("ind", "IDN"));
        monthYearFormatter = new SimpleDateFormat("MMM yyyy", new Locale("ind", "IDN"));
        dateDefaultFormatter = new SimpleDateFormat("yyyy-MM-dd");


        final String[] listTamu = new String[6];
        for (int i = 0; i < 6; i++) {
            listTamu[i] = (i + 1) + "";
        }

        evTamu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEvent.ACTION_UP == motionEvent.getAction()) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(HotelActivity.this);
                    builder.setItems(listTamu, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            evTamu.setText(listTamu[item]);
                        }
                    }).create().show();
                }
                return true;
            }
        });

        final String[] listKamar = new String[8];
        for (int i = 0; i < 8; i++) {
            listKamar[i] = (i + 1) + "";
        }

        evKamar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEvent.ACTION_UP == motionEvent.getAction()) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(HotelActivity.this);
                    builder.setItems(listKamar, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            evKamar.setText(listKamar[item]);
                        }
                    }).create().show();
                }
                return true;
            }
        });

    }


    private void setCallBack() {
        tvCariHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickIntent = new Intent(HotelActivity.this,
                        ListHotelActivity.class);
                startActivity(pickIntent);
            }
        });

        evKota.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (MotionEvent.ACTION_UP == motionEvent.getAction()) {

                    Intent pickIntent = new Intent(HotelActivity.this,
                            ChooserHotelActivity.class);
                    startActivityForResult(pickIntent, HOTEL_KOTA);
                }
                return true;
            }
        });

        layoutCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(HotelActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        tvDayCheckin.setText(dayFormatter.format(newDate.getTime()));
                        tvDateCheckin.setText(dateFormatter.format(newDate.getTime()));
                        tvMonthYearCheckin.setText(monthYearFormatter.format(newDate.getTime()));
                        dateCheckin = dateDefaultFormatter.format(newDate.getTime());
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        layoutCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(HotelActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        tvDayCheckout.setText(dayFormatter.format(newDate.getTime()));
                        tvDateCheckout.setText(dateFormatter.format(newDate.getTime()));
                        tvMonthYearCheckout.setText(monthYearFormatter.format(newDate.getTime()));
                        dateCheckout = dateDefaultFormatter.format(newDate.getTime());
                    }

                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == HOTEL_KOTA) {
//                AIRPORT_CODE_D = data.getStringExtra(CommonConstants.AIRPORT_CODE_D);
//                AIRPORT_NAME_D = data.getStringExtra(CommonConstants.AIRPORT_NAME_D);
//                AIRPORT_LOCATION_D = data.getStringExtra(CommonConstants.AIRPORT_LOCATION_D);
//                dariAirportCode.setText(AIRPORT_CODE_D);
//                dariAirportName.setText(AIRPORT_NAME_D + "\n" + AIRPORT_LOCATION_D);
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}