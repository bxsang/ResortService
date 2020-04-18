package com.sang.resortservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sang.resortservice.api.Room;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {
    private Context context;
    private List<Room> roomList;
    private LayoutInflater layoutInflater;

    public RoomAdapter(Context context, List<Room> roomList) {
        this.context = context;
        this.roomList = roomList;
        layoutInflater = LayoutInflater.from(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView roomName, roomStatus, roomType, roomPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            roomName = itemView.findViewById(R.id.tv_room_name);
            roomStatus = itemView.findViewById(R.id.tv_status);
            roomType = itemView.findViewById(R.id.tv_room_type);
            roomPrice = itemView.findViewById(R.id.tv_price);

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
        View item = layoutInflater.inflate(R.layout.list_room, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.roomName.setText(room.getName());
        holder.roomStatus.setText(String.valueOf(room.getStatus()));
        holder.roomType.setText(room.getType());
        holder.roomPrice.setText(String.valueOf(room.getPrice()));
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }
}
