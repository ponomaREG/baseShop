package com.test.baseshop.fragment_basket;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.baseshop.R;
import com.test.baseshop.model_helper.Item;

import java.util.List;

public class RecyclerViewAdapterBasket extends RecyclerView.Adapter<RecyclerViewAdapterBasket.MyViewHolder> {

    private int total_summ_of_items_for_order = 0;
    private LayoutInflater inflater;
    private List<Item> items;
    private com.test.baseshop.fragment_basket.Interfaces.Model.Photo model_photo;
    private com.test.baseshop.fragment_basket.Interfaces.Presenter.ConnectionBetweenViewAndRecyclerList presenter_to_view;
    private com.test.baseshop.fragment_basket.Interfaces.Presenter.ConnectionBetweenModelAndRecyclerList presenter_to_model;

    private final static String WEIGHT_SIGN = "%s г.";

    RecyclerViewAdapterBasket(Context context, List<Item> items, Interfaces.Model.Photo model, Interfaces.Presenter presenter){
        this.inflater = LayoutInflater.from(context);
        this.items = items;
        this.model_photo = model;
        this.presenter_to_view = (Interfaces.Presenter.ConnectionBetweenViewAndRecyclerList) presenter;
        this.presenter_to_model = (Interfaces.Presenter.ConnectionBetweenModelAndRecyclerList) presenter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.fragment_basket_rv_item,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Item current_item = items.get(position);

        holder.item_title.setText(current_item.getTitle());
        holder.item_count_of_items_for_order.setText(String.valueOf(current_item.getNumberOfItemForOrder()));

        int total_summ_of_current_item_for_order = current_item.getPrice() * current_item.getNumberOfItemForOrder();
        this.total_summ_of_items_for_order += total_summ_of_current_item_for_order;
        holder.item_price.setText(String.valueOf(total_summ_of_current_item_for_order));

        holder.count_of_item_for_order.setText(String.valueOf(current_item.getNumberOfItemForOrder()));
        holder.desc.setText(current_item.getDesc());
        holder.weight.setText(String.format(WEIGHT_SIGN, current_item.getWeight()));

        model_photo.setImageWithPicasso(current_item.setImageView(holder.image_of_item));
//        model_photo.setImageInBackground(items.get(position).setImageView(holder.image_of_item));

        holder.plus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = items.get(position);
                int count_of_item_for_order = item.getNumberOfItemForOrder() + 1;
                item.setNumberOfItemForOrder(count_of_item_for_order);
                total_summ_of_items_for_order += item.getPrice();
                updateInViewAndInModel(position, count_of_item_for_order, item.getPrice(), total_summ_of_items_for_order);
            }
        });
        holder.minus_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item item = items.get(position);
                int count_of_item_for_order = item.getNumberOfItemForOrder() - 1;
                item.setNumberOfItemForOrder(count_of_item_for_order);
                total_summ_of_items_for_order -= item.getPrice();
                Log.d("total_summ_of_", total_summ_of_items_for_order+"");
                Log.d("total_summ_of_asd", item.getPrice()+"");
                updateInViewAndInModel(position, count_of_item_for_order, item.getPrice(), total_summ_of_items_for_order);
                if (count_of_item_for_order == 0) {
                    prepareToDatasetChangedNotify();
                    presenter_to_view.tellViewToRemoveItemForOrder(position);
                    items.remove(position);
                    presenter_to_view.tellViewWhatDatasetUpdated();
                }
            }
        });

        View.OnClickListener ocl_for_make_visible = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((Integer) v.getTag() == View.VISIBLE) {
                    v.setTag(View.GONE);
                    presenter_to_view.tellViewToHideLLContainerWithDesc(position);
                } else {
                    v.setTag(View.VISIBLE);
                    presenter_to_view.tellViewToShowLLContainerWithDesc(position);
                }
            }
        };
        holder.item_LL_container.setOnClickListener(ocl_for_make_visible);
//        holder.desc_LL_container.setOnClickListener(ocl_for_make_visible);
        holder.item_LL_container.setTag(View.GONE);
//        holder.desc_LL_container.setTag(View.GONE);

        if(getItemCount() - 1 == position) presenter_to_view.tellViewToSetTotalSummOfItemsForOrder(total_summ_of_items_for_order);

    }

    private void prepareToDatasetChangedNotify(){
        this.total_summ_of_items_for_order = 0;
    }

//    private void checkIfBasketIsEmpty(){
//        if(this.total_summ_of_items_for_order == 0){presenter_to_view.tellViewToSetTotalSummOfItemsForOrder(0);}
//    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView desc, weight, count_of_item_for_order;
        TextView item_title, item_price, item_count_of_items_for_order;
        ImageView image_of_item, plus_icon, minus_icon;
        LinearLayout desc_LL_container;
        RelativeLayout item_LL_container;
        MyViewHolder(View v) {
            super(v);
            item_LL_container = v.findViewById(R.id.fragment_basket_item_LL_container);

            item_title = v.findViewById(R.id.fragment_basket_item_title);
            item_price = v.findViewById(R.id.fragment_basket_item_price);
            item_count_of_items_for_order = v.findViewById(R.id.fragment_basket_item_count_of_items_for_order);



            desc_LL_container = v.findViewById(R.id.fragment_basket_item_LL_desc_container);

            desc = v.findViewById(R.id.fragment_basket_item_desc_description);
            weight = v.findViewById(R.id.fragment_basket_item_desc_weight);
            count_of_item_for_order = v.findViewById(R.id.fragment_basket_item_desc_count_of_item);

            image_of_item = v.findViewById(R.id.fragment_basket_item_desc_icon);
            plus_icon = v.findViewById(R.id.fragment_basket_item_desc_icon_plus);
            minus_icon = v.findViewById(R.id.fragment_basket_item_desc_icon_minus);
        }
    }

    private void updateInViewAndInModel(int position, int count_of_item_for_order, int price, int new_total_summ){
        presenter_to_view.tellViewToSetNumberOfItemForOrderAndCalculateTotalPriceOfEachItem(position,count_of_item_for_order,price);
        presenter_to_view.tellViewToSetTotalSummOfItemsForOrder(new_total_summ);
        presenter_to_model.tellModelToSetNewNumberOfItemsForOrder(
                items.get(position).getId(),
                count_of_item_for_order
        );
    }
}
