package com.ankita.secondtask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ankita.secondtask.R;
import com.ankita.secondtask.modals.CreateResponse;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_EVEN = 1;

    private Context context;
    private ArrayList<CreateResponse.User> userlist;

    public UserItemAdapter(Context context,ArrayList<CreateResponse.User> userlist){
        this.context=context;
        this.userlist=userlist;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_EVEN) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_even, parent, false);
            return new MyEvenViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_odd, parent, false);
            return new MyOddViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyEvenViewHolder) {
            showEven((MyEvenViewHolder) holder, position);
        } else if (holder instanceof MyOddViewHolder) {
            showOdd((MyOddViewHolder) holder, position);
        }
    }

    private void showEven(MyEvenViewHolder holder, int position) {
        CreateResponse.User user = userlist.get(position);
        holder.tvName.setText(user.getName());
        Glide.with(context).load(userlist.get(position).getImage()).into(holder.ivProfile);
        ArrayList<String> images=new ArrayList<>();
        for (String datum : userlist.get(position).getItems()) {
            images.add(datum);
        }
        Glide.with(context).load(images.get(0)).into(holder.ivFirst);
        Glide.with(context).load(images.get(1)).into(holder.ivSecond);
        Glide.with(context).load(images.get(2)).into(holder.ivThird);
        Glide.with(context).load(images.get(2)).into(holder.ivFourth);
    }
    private void showOdd(MyOddViewHolder holder, int position) {
        CreateResponse.User user = userlist.get(position);
        holder.tvName.setText(user.getName());
        Glide.with(context).load(userlist.get(position).getImage()).into(holder.ivProfile);
        ArrayList<String> images=new ArrayList<>();
        for (String datum : userlist.get(position).getItems()) {
            images.add(datum);
        }
        Glide.with(context).load(images.get(0)).into(holder.ivFirst);
        Glide.with(context).load(images.get(1)).into(holder.ivSecond);
        Glide.with(context).load(images.get(2)).into(holder.ivThird);
    }


    @Override
    public int getItemViewType(int position) {
        return userlist.get(position).items.size() %2==0 ? VIEW_TYPE_EVEN : VIEW_TYPE_ITEM;
    }
    @Override
    public int getItemCount() {
        return userlist.size();
    }
    class MyEvenViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageView ivProfile,ivFirst,ivSecond,ivThird,ivFourth;
        public MyEvenViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            ivProfile=itemView.findViewById(R.id.ivProfile);
            ivFirst=itemView.findViewById(R.id.ivFirst);
            ivSecond=itemView.findViewById(R.id.ivSecond);
            ivThird=itemView.findViewById(R.id.ivThird);
            ivFourth=itemView.findViewById(R.id.ivFourth);
        }
    }
    class MyOddViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        ImageView ivProfile,ivFirst,ivSecond,ivThird;
        public MyOddViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            ivProfile=itemView.findViewById(R.id.ivProfile);
            ivFirst=itemView.findViewById(R.id.ivFirst);
            ivSecond=itemView.findViewById(R.id.ivSecond);
            ivThird=itemView.findViewById(R.id.ivThird);
        }
    }
}
