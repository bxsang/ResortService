package com.sang.resortservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sang.resortservice.api.Feedback;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyViewHolder> {
    private Context context;
    private List<Feedback> feedbackList;
    private LayoutInflater layoutInflater;

    public FeedbackAdapter(Context context, List<Feedback> feedbackList) {
        this.context = context;
        this.feedbackList = feedbackList;
        layoutInflater = LayoutInflater.from(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView comment, star;

        public MyViewHolder(View itemView) {
            super(itemView);
            star = itemView.findViewById(R.id.tv_star);
            comment = itemView.findViewById(R.id.tv_feedback_comment);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,comment.getText(),Toast.LENGTH_SHORT).show();
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
        View item = layoutInflater.inflate(R.layout.list_feedback, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Feedback feedback = feedbackList.get(position);
        holder.star.setText(String.valueOf(feedback.getStar()));
        holder.comment.setText(feedback.getComment());
    }

    @Override
    public int getItemCount() {
        return feedbackList.size();
    }
}
