package com.test.baseshop.fragment_profile.orders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.baseshop.R;

import java.util.Objects;
//TODO:SHOW ADDRESS

public class fragment_orders extends Fragment implements Interfaces.View{

    private Interfaces.Presenter presenter;


    public fragment_orders() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static fragment_orders newInstance(String param1, String param2) {
        fragment_orders fragment = new fragment_orders();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     return inflater.inflate(R.layout.fragment_profile_fragment_orders,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getData(getContext());
    }

    private void initPresenter(){
        this.presenter = new fragment_orders_presenter(this);
    }

    @Override
    public void setAdapter(RecyclerViewAdapterOrders adapter) {
        RecyclerView rv = Objects.requireNonNull(getView()).findViewById(R.id.fragment_profile_fragment_addresses_rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(),DividerItemDecoration.VERTICAL));
        rv.setAdapter(adapter);
    }
}
