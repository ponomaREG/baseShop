package com.test.baseshop.fragment_menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.baseshop.R;


import java.util.HashMap;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private List<Item> items;
    private Interfaces.Model.Photo model_photo;
    private Interfaces.Presenter.ConnectionBetweenViewAndRecyclerList presenter_to_view;
    private Interfaces.Presenter.ConnectionBetweenModelAndRecyclerList presenter_to_model;
    private HashMap<Integer,Integer> items_which_already_in_busket;

    private final static String MONEY_SIGN = "%sР", WEIGHT_SIGN = "%s г.";

    RecyclerViewAdapter(Context context, List<Item> items, Interfaces.Model.Photo model, Interfaces.Presenter presenter_to_view){
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        this.model_photo = model;
        this.presenter_to_view = (Interfaces.Presenter.ConnectionBetweenViewAndRecyclerList) presenter_to_view;
        this.presenter_to_model = (Interfaces.Presenter.ConnectionBetweenModelAndRecyclerList) presenter_to_view;
        this.items_which_already_in_busket = this.presenter_to_model.tellModelToGetBasketOfItemsForOrder();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.fragment_menu_rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.desc.setText(items.get(position).getDesc());
        holder.price.setText(String.format(MONEY_SIGN,items.get(position).getPrice()));
        holder.weight.setText(String.format(WEIGHT_SIGN,items.get(position).getWeight()));
        model_photo.setImageInBackground(items.get(position).setImageView(holder.image_of_item));


        if(this.items_which_already_in_busket.containsKey(items.get(position).getId())){
            holder.minus_icon.setVisibility(View.VISIBLE);
            holder.number_of_item_for_order.setVisibility(View.VISIBLE);
            int count_of_items_for_already_whichs_already_in_busket = this.items_which_already_in_busket.get(items.get(position).getId());
            holder.number_of_item_for_order.setText(String.valueOf(count_of_items_for_already_whichs_already_in_busket));
            items.get(position).setNumberOfItemForOrder(count_of_items_for_already_whichs_already_in_busket);
//        }
        }

//        if((items.get(position).getNumberOfItemForOrder() != 0)){
//            presenter_to_view.tellViewToShowMinusIconAndNumberOfItemForOrder(position);
//            presenter_to_view.tellViewToSetNumberOfItemForOrder(position,items.get(position).getNumberOfItemForOrder());
//        }

        holder.plus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = items.get(position);
                int count_of_item_for_order = item.getNumberOfItemForOrder();
                count_of_item_for_order++;
                item.setNumberOfItemForOrder(count_of_item_for_order);
                if(count_of_item_for_order == 1){
                    presenter_to_view.tellViewToShowMinusIconAndNumberOfItemForOrder(position);
                }
                updateInViewAndInModel(position, count_of_item_for_order);
            }
        });
        holder.minus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = items.get(position);
                int count_of_item_for_order = item.getNumberOfItemForOrder();
                count_of_item_for_order--;
                item.setNumberOfItemForOrder(count_of_item_for_order);
                if(count_of_item_for_order == 0){
                    presenter_to_view.tellViewToHideMinusIconAndNumberOfItemForOrder(position);
                }
                updateInViewAndInModel(position, count_of_item_for_order);
            }
        });

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc, price, weight, number_of_item_for_order;
        ImageView image_of_item, plus_icon, minus_icon;
        MyViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.fragment_menu_rv_item_title);
            desc = v.findViewById(R.id.fragment_menu_rv_item_description);
            price = v.findViewById(R.id.fragment_menu_rv_item_price);
            weight = v.findViewById(R.id.fragment_menu_rv_item_weight);
            image_of_item = v.findViewById(R.id.fragment_menu_rv_item_icon);
            number_of_item_for_order = v.findViewById(R.id.fragment_menu_rv_item_count_of_item);
            plus_icon = v.findViewById(R.id.fragment_menu_rv_item_icon_plus);
            minus_icon = v.findViewById(R.id.fragment_menu_rv_item_icon_minus);
        }
    }

    private void updateInViewAndInModel(int position, int count_of_item_for_order){
        presenter_to_view.tellViewToSetNumberOfItemForOrder(position,count_of_item_for_order);
        if(count_of_item_for_order != 0)
        presenter_to_model.tellModelToSetNewNumberOfItemsForOrder(
                items.get(position).getId(),
                count_of_item_for_order
        );
    }


//    private android.view.View.OnClickListener getOclForIconsPlusAndMinus(){
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(v.getId() == R.id.fragment_menu_rv_item_icon_plus){
//                    items.get()
//                }else if (v.getId() == R.id.fragment_menu_rv_item_icon_minus){
//
//                }
//            }
//        };
//    }
}
