package com.demoapp.demoappbox.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.demoapp.demoappbox.R;
import com.demoapp.demoappbox.model.User;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ViewHolder>{
    List<User> list;
    OnItemLongClickListener longClickListener;
    public interface OnItemLongClickListener {
        void onItemLongClick(User user);
    }

    public ListUserAdapter(List<User> list, OnItemLongClickListener longClickListener) {
        this.list = list;
        this.longClickListener = longClickListener;
    }
    @NonNull
    @Override
    public ListUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUserAdapter.ViewHolder holder, int position) {
        User user = list.get(position);
        holder.textName.setText("Name:" + user.getName());
        holder.textRoll.setText("RollNumber:" + user.getRoll_number());
        holder.textSubject.setText("Subject:" + user.getSubject());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longClickListener.onItemLongClick(user);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textRoll, textSubject;
        ViewHolder(View v) {
            super(v);
            textName = v.findViewById(R.id.textName);
            textRoll = v.findViewById(R.id.textRoll);
            textSubject = v.findViewById(R.id.textSubject);
        }
    }
}
