package com.sang.resortservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sang.resortservice.api.Reservation;

import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.MyViewHolder> {
    private Context context;
    private List<Reservation> reservationList;
    private LayoutInflater layoutInflater;

    public ReservationAdapter(Context context, List<Reservation> reservationList) {
        this.context = context;
        this.reservationList = reservationList;
        layoutInflater = LayoutInflater.from(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView customerName, roomName, reservationDate, price, checkinDate, checkoutDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            customerName = itemView.findViewById(R.id.tv_customer_name);
            roomName = itemView.findViewById(R.id.tv_room_name2);
            reservationDate = itemView.findViewById(R.id.tv_reservation_date);
            price = itemView.findViewById(R.id.tv_reservation_price);
            checkinDate = itemView.findViewById(R.id.tv_checkin_date);
            checkoutDate = itemView.findViewById(R.id.tv_checkout_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,roomName.getText(),Toast.LENGTH_SHORT).show();
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
//                    Toast.makeText(context,"Long item clicked " + title.getText() + ,Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.list_reservation, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);
        holder.customerName.setText(reservation.getCustomerName());
        holder.roomName.setText(reservation.getRoomName());
        holder.reservationDate.setText(reservation.getReservationDate());
        holder.price.setText(String.valueOf(reservation.getPrice()));
        holder.checkinDate.setText(reservation.getCheckinDate());
        holder.checkoutDate.setText(reservation.getCheckoutDate());
    }

    @Override
    public int getItemCount() {
        return reservationList.size();
    }
}
