package com.example.hotelbookingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    //Referencing

    private TextView tvHotel;


    private Spinner spinLocation;
    private Spinner spinRoomType;
    private EditText etAdults, etChildren, etRoom;
    private Button btnCalculate;

    TextView tvindate, tvoutdate;
    Button simpledatepicker1, simpledatepicker2;
    TextView tvDays, tvError, tvLocation, tvRoomType, tvInDate, tvOutDate, tvAdults, tvChildren, tvRoom, tvSErvice, tvTax, tvTotal;




    int year2, year3;
    int month2, month3;
    int day2, day3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for full screen and hiding title bar from the window

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //progressBar = findViewById(R.id.ProgressBar);
        tvHotel = findViewById(R.id.tvHotelBook);
        //Binding
        spinLocation = (Spinner) findViewById(R.id.spinLocation);
        spinRoomType = (Spinner) findViewById(R.id.spinRoomType);
        etAdults = (EditText) findViewById(R.id.etAdults);
        etChildren = (EditText) findViewById(R.id.etChildren);
        etRoom = (EditText) findViewById(R.id.etRoom);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        tvError = (TextView) findViewById(R.id.tverror);
        tvindate = findViewById(R.id.tvindate);
        tvoutdate = findViewById(R.id.tvoutdate);
        simpledatepicker1 = findViewById(R.id.simpledatepicker1);
        simpledatepicker2 = findViewById(R.id.simpledatepicker2);
        //scroll view outputs data binding
        tvDays = (TextView) findViewById(R.id.tvDays);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvRoomType = (TextView) findViewById(R.id.tvRoomType);

        tvAdults = (TextView) findViewById(R.id.tvAdults);
        tvChildren = (TextView) findViewById(R.id.tvChildren);
        tvRoom = (TextView) findViewById(R.id.tvRoom);
        tvSErvice = (TextView) findViewById(R.id.tvService);
        tvTax = (TextView) findViewById(R.id.tvTax);
        tvTotal = (TextView) findViewById(R.id.tvTotal);



        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);

//passing an array to the room type in spinner
        String location[] = {"Bhaktapur", "Pokhara", "Chitwan"};
        ArrayAdapter adapter = new ArrayAdapter<>
                (
                        this,
                        android.R.layout.simple_list_item_1, location
                );
        spinLocation.setAdapter(adapter);

        //passing an array to the room type in spinner
        String roomType[] = {"Deluxe", "AC", "Platinum"};
        ArrayAdapter adapterRoom = new ArrayAdapter<>
                (
                        this,android.R.layout.select_dialog_singlechoice, roomType
                );
        spinRoomType.setAdapter(adapterRoom);

        simpledatepicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCalendar1();
            }
        });


        simpledatepicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCalendar2();

            }
        });

    }
    private void loadCalendar2() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "Checked Out At::" + month + "/" + dayOfMonth + "/" + year;
                month3 = month;
                day3 = dayOfMonth;
                year3 = year;
                tvoutdate.setText(date);
            }

        }, year, month, day);
        datePickerDialog.show();
    }


    private void loadCalendar1() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "Checked In At::" + month + "/" + dayOfMonth + "/" + year;
                month2 = month;
                day2 = dayOfMonth;
                year2 = year;
                tvindate.setText(date);
            }

        }, year, month, day);
        datePickerDialog.show();


        //button mode

        //validation should be done at first

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validations for widgets
                if (TextUtils.isEmpty(tvindate.getText())) {
                    tvError.setText("Please enter check in Date ");
                    return;
                }
                else if (TextUtils.isEmpty(tvoutdate.getText())) {
                    tvError.setText("Please enter checked out date ");
                    return;
                }
                else if(TextUtils.isEmpty(etAdults.getText()))
                {
                    etAdults.setError("Enter number of adults");
                    return;
                }
                else if(TextUtils.isEmpty(etChildren.getText()))
                {
                    etChildren.setError("Enter number of children");
                    return;
                }
                else if(TextUtils.isEmpty(etRoom.getText()))
                {
                    etRoom.setError("Enter number of rooms");
                    return;
                }

                tvLocation.setText("Location : "+spinLocation.getSelectedItem().toString());
                tvRoomType.setText("Room Type : "+spinRoomType.getSelectedItem().toString());

                tvAdults.setText("Number of Adults : "+etAdults.getText().toString());
                tvChildren.setText("Number of Children : "+etChildren.getText().toString());
                tvRoom.setText("Number of Rooms : "+etRoom.getText().toString());

                //Dates conversion for number of days
                Calendar cal1 = Calendar.getInstance();
                Calendar cal2 = Calendar.getInstance();
                cal1.set(year2, month2, day2);
                cal2.set(year3, month3, day3);
                long millis1 = cal1.getTimeInMillis();
                long millis2 = cal2.getTimeInMillis();
                long diff = millis2 - millis1;
                long diffDays = (diff / (86400000));

                //Calculation part
                int  numRoom = Integer.parseInt(etRoom.getText().toString());
                double price;
                double TotalPrice;
                double ServiceCharge;
                double GrandTotal;


                String roomType = spinRoomType.getSelectedItem().toString();

                if (roomType == "Deluxe") {
                       //Room Price Per Night:"+"2000
                    price = 2000;
                    TotalPrice = price * numRoom * diffDays;
                    ServiceCharge = ((0.10) * TotalPrice) +TotalPrice;
                    GrandTotal = ((0.13) * ServiceCharge) + ServiceCharge;

                    tvDays.setText("Stayed Days : "+ diffDays);
                    tvRoom.setText(("Rooms booked : "+ numRoom));
                    tvTotal.setText("Total : "+TotalPrice);
                    tvSErvice.setText("Price after service charge : "+ServiceCharge);
                    tvTax.setText("Price after tax inclusion : "+GrandTotal);


                    Toast.makeText(MainActivity.this, "Total cost : " + GrandTotal, Toast.LENGTH_SHORT).show();


                } else if (roomType == "AC") {

                    price = 3000;
                    TotalPrice = price * numRoom * diffDays;
                    ServiceCharge = ((0.10) * TotalPrice) +TotalPrice;
                    GrandTotal = ((0.13) * ServiceCharge) + ServiceCharge;

                    tvDays.setText("Stayed Days : "+ diffDays);
                    tvRoom.setText(("Rooms booked : "+ numRoom));
                    tvTotal.setText("Total : "+TotalPrice);
                    tvSErvice.setText("Price after service charge : "+ServiceCharge);
                    tvTax.setText("Price after tax inclusion : "+GrandTotal);


                    Toast.makeText(MainActivity.this, "Total cost : " + GrandTotal, Toast.LENGTH_SHORT).show();

                } else if (roomType == "Platinum") {
                    price = 4000;

                    TotalPrice = price * numRoom * diffDays;
                    ServiceCharge = ((0.10) * TotalPrice) +TotalPrice;
                    GrandTotal = ((0.13) * ServiceCharge) + ServiceCharge;

                    tvDays.setText("Stayed Days : "+ diffDays);
                    tvRoom.setText(("Rooms booked : "+ numRoom));
                    tvTotal.setText("Total : "+TotalPrice);
                    tvSErvice.setText("Price after service charge : "+ServiceCharge);
                    tvTax.setText("Price after tax inclusion : "+GrandTotal);


                    Toast.makeText(MainActivity.this, "Total cost : " + GrandTotal, Toast.LENGTH_SHORT).show();

                }






            }
        });


    }







}

