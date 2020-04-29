package com.test.baseshop.fragment_basket.offer_order;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.test.baseshop.R;
import com.test.baseshop.fragment_basket.fragment_basket;

import java.util.Objects;


public class BottomSheetOfferOrder extends BottomSheetDialogFragment implements Interfaces.View{

    private bsv_offer_order_presenter presenter;

    public static String TAG = "OfferOrder";

    public static BottomSheetOfferOrder newInstance() {
        return new BottomSheetOfferOrder();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_basket_bsv_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter.getAddresses(getLayoutInflater(), (ViewGroup) view.findViewById(R.id.fragment_basket_bsv_container_addresses));
    }

    private void initPresenter(){
        presenter = new bsv_offer_order_presenter(this);
    }


    @Override
    public void showErrorEmptyAddress() {
        Toast.makeText(getContext(),"Необходимо выбрать адрес",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorStrange() {
        Toast.makeText(getContext(),"Произошла непредвиденная ошибка",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorZeroAddresses() {
        Toast.makeText(getContext(), "У Вас нет адресов", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearBasket() {
        assert getParentFragment() != null;
        ((fragment_basket) getParentFragment()).clearBasket();
    }

    @Override
    public void showDescOfOfferThatUserCanCheckStatusOfOrderInAnotherSection() {
        Toast.makeText(getContext(),"Скоро Вам позвонят.\nСтатус заказа можете посмотреть во вкладке Профиль\n Спасибо за заказ!\n",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addNewAddressCellInContainer(View v) {
        LinearLayout container_addresses = Objects.requireNonNull(getView()).findViewById(R.id.fragment_basket_bsv_container_addresses);
        container_addresses.addView(v);
    }
}
