package com.test.baseshop.fragment_profile.orders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.baseshop.R;
import com.test.baseshop.model_helper.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecyclerViewAdapterOrders extends RecyclerView.Adapter<RecyclerViewAdapterOrders.MyViewHolder> {

    private LayoutInflater inflater;
    private HashMap<String,Object> data_with_items;
    private List<String> keys;
    private Map<String,Integer> numbers_of_orders;
    private Map<String,Integer> total;

    private final static String WEIGHT_SIGN = "%s г.";

    RecyclerViewAdapterOrders(Context context, HashMap<String,Object> data_with_items){
        this.inflater = LayoutInflater.from(context);
        this.data_with_items = data_with_items;

        this.keys = new ArrayList<>();
        this.keys.addAll(data_with_items.keySet());
        this.keys.remove("total");
        this.keys.remove("orders_numbers");

        this.numbers_of_orders = (Map<String, Integer>) data_with_items.get("orders_numbers");
        this.total = (Map<String, Integer>) data_with_items.get("total");
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.fragment_profile_fragment_orders_rv_item,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        String key = keys.get(position);
        List<Item> items = (List<Item>) data_with_items.get(key);
        assert items != null;
        for(Item item:items){
            View item_view = inflater.inflate(R.layout.fragment_profile_fragment_orders_rv_item_in_item, holder.container_LL,false);

            TextView title = item_view.findViewById(R.id.fragment_profile_fragment_addresses_rv_item_in_item_title);
            TextView count_of_items = item_view.findViewById(R.id.fragment_profile_fragment_addresses_rv_item_in_item_count_of_items_for_order);
            TextView price = item_view.findViewById(R.id.fragment_profile_fragment_addresses_rv_item_in_item_price);

            title.setText(item.getTitle());
            price.setText(String.valueOf(item.getTotalPrice()));
            count_of_items.setText(String.valueOf(item.getNumberOfItemForOrder()));

            holder.container_LL.addView(item_view);
        }

        holder.time.setText(key);

        holder.total.setText(String.valueOf((int) Double.parseDouble(String.valueOf(total.get(key)))));
        holder.number_of_order.setText(String.valueOf((int) Double.parseDouble(String.valueOf(numbers_of_orders.get(key)))));

    }


//    private void checkIfBasketIsEmpty(){
//        if(this.total_summ_of_items_for_order == 0){presenter_to_view.tellViewToSetTotalSummOfItemsForOrder(0);}
//    }


    @Override
    public int getItemCount() {
        return keys.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView number_of_order, address, time, total;
        LinearLayout container_LL;
        View parent;
        MyViewHolder(View v) {
            super(v);
            container_LL = v.findViewById(R.id.fragment_profile_fragment_addresses_rv_item_container_items);
            number_of_order = v.findViewById(R.id.fragment_profile_fragment_addresses_rv_item_numberOfOrder);
            address = v.findViewById(R.id.fragment_profile_fragment_addresses_rv_item_time);
            time = v.findViewById(R.id.fragment_profile_fragment_addresses_rv_item_address);
            total = v.findViewById(R.id.fragment_profile_fragment_addresses_rv_item_total);
            parent = v;
        }
    }
}
