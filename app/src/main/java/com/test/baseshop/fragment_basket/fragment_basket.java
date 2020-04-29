package com.test.baseshop.fragment_basket;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.test.baseshop.R;
import com.test.baseshop.fragment_basket.offer_order.BottomSheetOfferOrder;

import java.util.Objects;

//TODO:CHOOSE FRAGMENT
public class fragment_basket extends Fragment implements Interfaces.View{

    private Interfaces.Presenter basket_presenter;
    private RecyclerView rv;
    private RecyclerViewAdapterBasket adapter;


    public fragment_basket() {
    }

    // TODO: Rename and change types and number of parameters
    public static fragment_basket newInstance() {
        return new fragment_basket();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basket, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initOclOfButtonForOrder();
        initRecyclerView();
        basket_presenter.getDataOfBasketInfo(getContext());
    }

    private void initPresenter(){
        this.basket_presenter = new fragment_basket_presenter(this);
    }


    private void initOclOfButtonForOrder(){
        Button button_for_order = Objects.requireNonNull(getView()).findViewById(R.id.fragment_basket_button_for_order);
        button_for_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basket_presenter.OnOrderButtonClick(adapter.getItems().size());
            }
        });
    }

    private void initRecyclerView(){
        rv = Objects.requireNonNull(getView()).findViewById(R.id.fragment_basket_recycler_view);
    }

    @Override
    public void setAdapter(RecyclerViewAdapterBasket adapter) {
        this.adapter = adapter;
        rv.setHasFixedSize(true);
        rv.setItemViewCacheSize(20);
        rv.setDrawingCacheEnabled(true);
        rv.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);

    }

    @Override
    public void removeItemForOrder(int position) {
        View v = Objects.requireNonNull(rv.getLayoutManager()).findViewByPosition(position);
        rv.removeView(v);
        this.adapter.notifyItemRemoved(position);
//        rv.getLayoutManager().removeView(v);
    }

    @Override
    public void setNumberOfItemForOrderAndCalculateTotalPriceOfEachItem(int position, int number_of_item_for_order, int price) {
        View v = Objects.requireNonNull(rv.getLayoutManager()).findViewByPosition(position);
        TextView count_of_items_for_order = Objects.requireNonNull(v).findViewById(R.id.fragment_basket_item_count_of_items_for_order);
        TextView count_of_items_for_order_in_desc = v.findViewById(R.id.fragment_basket_item_desc_count_of_item);
        TextView total_price_of_current_item = v.findViewById(R.id.fragment_basket_item_price);

        count_of_items_for_order.setText(String.valueOf(number_of_item_for_order));
        count_of_items_for_order_in_desc.setText(String.valueOf(number_of_item_for_order));
        total_price_of_current_item.setText(String.valueOf(number_of_item_for_order * price));
    }

    @Override
    public void setNewTotalSummOfItemsForOrder(int new_total_summ) {
        TextView total_summ_of_items_for_order = Objects.requireNonNull(getView()).findViewById(R.id.fragment_basket_total_summ);
        total_summ_of_items_for_order.setText(String.valueOf(new_total_summ));
    }

    @Override
    public void showDescOfItemForOrder(int position) {
        View v = Objects.requireNonNull(rv.getLayoutManager()).findViewByPosition(position);
        assert v != null;
        v.findViewById(R.id.fragment_basket_item_LL_desc_container).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDescOfItemForOrder(int position) {
        View v = Objects.requireNonNull(rv.getLayoutManager()).findViewByPosition(position);
        assert v != null;
        v.findViewById(R.id.fragment_basket_item_LL_desc_container).setVisibility(View.GONE);
    }

    @Override
    public void updateRecycleView() {
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void clearBasket() {
        this.adapter.getItems().clear();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showOfferOfOrder(BottomSheetOfferOrder bottomSheetOfferOrder) {
        bottomSheetOfferOrder.setCancelable(true);
        assert getFragmentManager() != null;
        bottomSheetOfferOrder.show(getChildFragmentManager(),BottomSheetOfferOrder.TAG);
    }


}
