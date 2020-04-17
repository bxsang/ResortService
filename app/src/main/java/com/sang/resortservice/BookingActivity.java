package com.sang.resortservice;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputEditText;
import com.sang.resortservice.api.APIClient;
import com.sang.resortservice.api.BookingResponse;
import com.sang.resortservice.api.GetRoomIdResponse;
import com.sang.resortservice.api.GetRoomTypesResponse;
import com.sang.resortservice.api.GetRoomsResponse;
import com.sang.resortservice.api.Room;
import com.sang.resortservice.api.RoomType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookingActivity extends AppCompatActivity {

    MaterialDatePicker.Builder<Pair<Long, Long>> builder;

    TextInputEditText editTextCustomerName;
    TextInputEditText editTextcustomerPhone;
    MaterialRadioButton chkGender;
    RadioGroup radioGroupCustomerGender;
    TextInputEditText editTextCustomerEmail;
    TextInputEditText editTextCustomerAddress;

    AutoCompleteTextView roomTypes;
    AutoCompleteTextView roomsName;
    Button btnDatePicker;
    TextView tvDate1;
    TextView tvDate2;

    ExtendedFloatingActionButton btnBook;

    Long timestampDate1;
    Long timestampDate2;
    int roomId = 0;

    Retrofit retrofit;
    APIClient client;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        editTextCustomerName = findViewById(R.id.et_customer_name);
        editTextcustomerPhone = findViewById(R.id.et_customer_phone);
        radioGroupCustomerGender = findViewById(R.id.rg_gender);
        editTextCustomerEmail = findViewById(R.id.et_customer_email);
        editTextCustomerAddress = findViewById(R.id.et_customer_address);

        roomTypes = findViewById(R.id.tv_room_type);
        roomsName = findViewById(R.id.tv_room_name);
        btnDatePicker = findViewById(R.id.btn_date_picker);
        tvDate1 = findViewById(R.id.tv_date1);
        tvDate2 = findViewById(R.id.tv_date2);
        btnBook = findViewById(R.id.btn_book);

        prepareRetrofit();
        initRoomTypes();

        roomTypes.setOnItemClickListener((parent, view, position, id) -> {
            String selectedType = roomTypes.getAdapter().getItem(position).toString();
            initRooms(selectedType);
        });

        roomsName.setOnItemClickListener((parent, view, position, id) -> {
            String selectedRoom = roomsName.getAdapter().getItem(position).toString();
            getRoomId(selectedRoom);
        });

        btnDatePicker.setOnClickListener(v -> {
            builder = MaterialDatePicker.Builder.dateRangePicker();
            builder.setTitleText(getString(R.string.select_date));
            MaterialDatePicker<Pair<Long, Long>> picker = builder.build();
            picker.addOnPositiveButtonClickListener(selection -> {
                timestampDate1 = selection.first;
                timestampDate2 = selection.second;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
                Date date1 = new Date(selection.first);
                Date date2 = new Date(selection.second);

                tvDate1.setText(getString(R.string.date_from) + "\n" + sdf.format(date1));
                tvDate2.setText(getString(R.string.date_to) + "\n" + sdf.format(date2));
            });
            picker.show(getSupportFragmentManager(), picker.toString());
        });

        btnBook.setOnClickListener(v -> {
            showCofirmDialog();
        });
    }

    private void prepareRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        client = retrofit.create(APIClient.class);
    }

    private void initRoomTypes() {
        Call<GetRoomTypesResponse> call = client.getRoomTypes();

        call.enqueue(new Callback<GetRoomTypesResponse>() {
            @Override
            public void onResponse(Call<GetRoomTypesResponse> call, Response<GetRoomTypesResponse> response) {
                roomTypesMapping(response.body().getRoomTypes());
            }

            @Override
            public void onFailure(Call<GetRoomTypesResponse> call, Throwable t) {
                Toast.makeText(BookingActivity.this, getString(R.string.connection_failed), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void roomTypesMapping(List<RoomType> listRoomTypes) {
        List<String> items = new ArrayList<>();
        for (int i=0; i<listRoomTypes.size(); i++) {
            items.add(listRoomTypes.get(i).getType());
        }
        ArrayAdapter<String> roomTypeAdapter = new ArrayAdapter<>(BookingActivity.this, R.layout.layout_selection, items);
        roomTypes.setAdapter(roomTypeAdapter);
    }

    private void initRooms(String type) {
        Call<GetRoomsResponse> call = client.getRooms(type);

        call.enqueue(new Callback<GetRoomsResponse>() {
            @Override
            public void onResponse(Call<GetRoomsResponse> call, Response<GetRoomsResponse> response) {
                RoomsMapping(response.body().getRoom());
            }

            @Override
            public void onFailure(Call<GetRoomsResponse> call, Throwable t) {
                Toast.makeText(BookingActivity.this, getString(R.string.connection_failed), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void RoomsMapping(List<Room> listRooms) {
        List<String> items = new ArrayList<>();
        for (int i=0; i<listRooms.size(); i++) {
            items.add(listRooms.get(i).getName());
        }
        ArrayAdapter<String> roomNameAdapter = new ArrayAdapter<>(BookingActivity.this, R.layout.layout_selection, items);
        roomsName.setAdapter(roomNameAdapter);
    }

    private void getRoomId(String roomName) {
        Call<GetRoomIdResponse> call = client.getRoomId("",roomName);

        call.enqueue(new Callback<GetRoomIdResponse>() {
            @Override
            public void onResponse(Call<GetRoomIdResponse> call, Response<GetRoomIdResponse> response) {
                setRoomId(response.body());
            }

            @Override
            public void onFailure(Call<GetRoomIdResponse> call, Throwable t) {
                Toast.makeText(BookingActivity.this, getString(R.string.connection_failed), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setRoomId(GetRoomIdResponse response) {
        roomId = response.getId();
    }

    private BookingValues getValues() {
        String customerName = editTextCustomerName.getText().toString();
        String customerPhone = editTextcustomerPhone.getText().toString();
        getCheckedGender();
        String customerGender = chkGender.getText().toString();
        String customerEmail = editTextCustomerEmail.getText().toString();
        String customerAddress = editTextCustomerAddress.getText().toString();

        return new BookingValues(customerName, customerPhone, customerGender, customerEmail, customerAddress, roomId, timestampDate1, timestampDate2);
    }

    private void getCheckedGender() {
        int chkGenderId = radioGroupCustomerGender.getCheckedRadioButtonId();
        chkGender = findViewById(chkGenderId);
    }

    private void book(BookingValues values) {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIClient client = builder.create(APIClient.class);
        Call<BookingResponse> call = client.book(
                values.customerName,
                values.customerPhone,
                values.customerGender,
                values.customerEmail,
                values.customerAddress,
                values.roomId,
                values.date1.toString(),
                values.date2.toString()
        );

        call.enqueue(new Callback<BookingResponse>() {
            @Override
            public void onResponse(Call<BookingResponse> call, Response<BookingResponse> response) {
                Toast.makeText(BookingActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BookingResponse> call, Throwable t) {
                Toast.makeText(BookingActivity.this, "Booking failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showCofirmDialog() {
        new MaterialAlertDialogBuilder(BookingActivity.this)
                .setTitle(getString(R.string.cofirm))
                .setMessage(getString(R.string.booking_cofirm))
                .setPositiveButton(getString(R.string.cofirm), (dialog, which) -> {
                    BookingValues values = getValues();
                    book(values);
                })
                .setNegativeButton(getString(R.string.cancle), (dialog, which) -> {
                })
                .show();
    }
}
