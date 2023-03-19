package com.mlio.mylifeinorder.mylifeinorder1.activity.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mlio.mylifeinorder.mylifeinorder1.R;
import com.mlio.mylifeinorder.mylifeinorder1.model.LioItem;

import java.util.List;

public class LioAdapter extends RecyclerView.Adapter<LioAdapter.LioViewHolder> {

    private List<LioItem> lioItems;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class LioViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public LioViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.lioImageView);
            textView = itemView.findViewById(R.id.lioTextView);

            itemView.setOnClickListener(view -> {
                if(listener != null) {
                    int position = getAbsoluteAdapterPosition();

                    if(position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    public LioAdapter(List<LioItem> lioItems) {
        this.lioItems = lioItems;
    }

    @NonNull
    @Override
    public LioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lio_item, parent, false);
        LioViewHolder lvh = new LioViewHolder(v, mListener);

        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull LioViewHolder holder, int position) {
        LioItem currentItem = lioItems.get(position);

        holder.imageView.setImageResource(currentItem.getmIconResource());
        holder.textView.setText(currentItem.getmText());
    }

    @Override
    public int getItemCount() {
        return lioItems.size();
    }
}
