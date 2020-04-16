package com.sang.resortservice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.android.volley.RequestQueue;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class BookingActivity extends AppCompatActivity {

    AutoCompleteTextView roomTypes;
    AutoCompleteTextView roomsName;
    Button btnDatePicker;
    MaterialDatePicker.Builder<Pair<Long, Long>> builder;
    TextView tvDate1;
    TextView tvDate2;

    RequestQueue requestQueue;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        roomTypes = findViewById(R.id.sl_room_type);
        roomsName = findViewById(R.id.sl_room_name);
        btnDatePicker = findViewById(R.id.btn_date_picker);
        tvDate1 = findViewById(R.id.tv_date1);
        tvDate2 = findViewById(R.id.tv_date2);

        initRoomTypes();
        getRoomTypes();

        List<String> roomNameItems = new ArrayList<String>();
        roomNameItems.add("A100");
        roomNameItems.add("A101");
        roomNameItems.add("A102");
        ArrayAdapter<String> roomNameAdapter = new ArrayAdapter<>(BookingActivity.this, R.layout.layout_selection, roomNameItems);
        roomsName.setAdapter(roomNameAdapter);

        btnDatePicker.setOnClickListener(v -> {
            builder = MaterialDatePicker.Builder.dateRangePicker();
            builder.setTitleText("Chon ngay");
            MaterialDatePicker<Pair<Long, Long>> picker = builder.build();
            picker.addOnPositiveButtonClickListener(selection -> {
                TimeZone timeZoneUTC = TimeZone.getDefault();
                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime() * 7);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                Date date1 = new Date(selection.first + offsetFromUTC);
                Date date2 = new Date(selection.second + offsetFromUTC);

                tvDate1.setText("Tu ngay:\n" + simpleDateFormat.format(date1));
                tvDate2.setText("Den ngay:\n" + simpleDateFormat.format(date2));
            });
            picker.show(getSupportFragmentManager(), picker.toString());
        });
    }

    private void initRoomTypes() {
        List<String> roomTypeItems = new ArrayList<String>();
        roomTypeItems.add("Single");
        roomTypeItems.add("Double");
        roomTypeItems.add("Deluxe");
        roomTypeItems.add("Presidential");
        roomTypeItems.add("Bungalow");
        ArrayAdapter<String> roomTypeAdapter = new ArrayAdapter<>(BookingActivity.this, R.layout.layout_selection, roomTypeItems);
        roomTypes.setAdapter(roomTypeAdapter);
    }

    private void getRoomTypes() {
//        requestQueue = Volley.newRequestQueue(this);
//        String url = "http://192.168.1.100/resort/api/room.php?get_types=true";
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response ->
//                Log.d("Response", response), error -> Log.d("Error", "error"));
//
//        stringRequest.setTag("abc");
//        requestQueue.add(stringRequest);
    }
}
