package com.test.baseshop.fragment_basket;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.test.baseshop.model_helper.Item;

import java.util.List;
import java.util.Objects;

public class fragment_basket_presenter implements Interfaces.Presenter,Interfaces.Presenter.ConnectionBetweenModelAndRecyclerList,Interfaces.Presenter.ConnectionBetweenViewAndRecyclerList {

    private com.test.baseshop.fragment_basket.Interfaces.View view;
    private com.test.baseshop.fragment_basket.Interfaces.Model.Basket model;
    private int USER_ID;

    fragment_basket_presenter(fragment_basket view){
        this.view = view;
        this.model = new fragment_basket_model();
        SharedPreferences sh = Objects.requireNonNull(view.getContext()).getSharedPreferences("AUTH_PREF",Context.MODE_PRIVATE);
        USER_ID = sh.getInt("USER_ID",0);
    }


    @Override
    public void OnOrderButtonClick() {
        Log.d("BUTTON FOR ORDER CLICK","1");
    }

    @Override
    public void getDataOfBasketInfo(Context context) {
        List<Item> list_of_items = model.getDataOFBasketInfoRemote(USER_ID);
        RecyclerViewAdapterBasket adapter = new RecyclerViewAdapterBasket(context,list_of_items,(Interfaces.Model.Photo) model,this);
        view.setAdapter(adapter);
        view.updateRecycleView();
    }

    @Override
    public void tellModelToSetNewNumberOfItemsForOrder(int item_id, int new_count_of_items_for_order) {
        model.sendNewNumberOfItemsForOrder(USER_ID,item_id,new_count_of_items_for_order);
    }


    @Override
    public void tellViewToRemoveItemForOrder(int position) {
        view.removeItemForOrder(position);
//        view.updateRecycleView();
    }

    @Override
    public void tellViewWhatDatasetUpdated() {
        view.updateRecycleView();
    }

    @Override
    public void tellViewToSetNumberOfItemForOrderAndCalculateTotalPriceOfEachItem(int position, int number_of_item_for_order, int price) {
        view.setNumberOfItemForOrderAndCalculateTotalPriceOfEachItem(position, number_of_item_for_order, price);
    }

    @Override
    public void tellViewToShowLLContainerWithDesc(int position) {
        view.showDescOfItemForOrder(position);
    }

    @Override
    public void tellViewToHideLLContainerWithDesc(int position) {
        view.hideDescOfItemForOrder(position);
    }

    @Override
    public void tellViewToSetTotalSummOfItemsForOrder(int new_total_summ) {
        view.setNewTotalSummOfItemsForOrder(new_total_summ);
    }
}
