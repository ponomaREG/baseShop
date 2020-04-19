package com.test.baseshop.fragment_menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.baseshop.R;


import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<Item> items;

    private final static String MONEY_SIGN = "%sР", WEIGHT_SIGN = "%s г.";

    RecyclerViewAdapter(Context context, List<Item> items){
        this.inflater = LayoutInflater.from(context);
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.fragment_menu_rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.desc.setText(items.get(position).getDesc());
        holder.price.setText(String.format(MONEY_SIGN,items.get(position).getPrice()));
        holder.weight.setText(String.format(WEIGHT_SIGN,items.get(position).getWeight()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc, price, weight;
        MyViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.fragment_menu_rv_item_title);
            desc = v.findViewById(R.id.fragment_menu_rv_item_description);
            price = v.findViewById(R.id.fragment_menu_rv_item_price);
            weight = v.findViewById(R.id.fragment_menu_rv_item_weight);
        }
    }
}
