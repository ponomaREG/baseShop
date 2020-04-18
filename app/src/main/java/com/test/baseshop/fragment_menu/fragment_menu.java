package com.test.baseshop.fragment_menu;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.baseshop.R;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_menu extends Fragment implements Interfaces.View{

    private Interfaces.Presenter fragment_menu_presenter;

    private RecyclerViewAdapter adapter;

    public fragment_menu() {
    }

    public static fragment_menu newInstance() {
        return new fragment_menu();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragment_menu_presenter.getSections();
        fragment_menu_presenter.getData(getContext(), com.test.baseshop.fragment_menu.fragment_menu_presenter.BURGERS);

    }

    private void initPresenter(){
        this.fragment_menu_presenter = new fragment_menu_presenter(this);
    }




    @Override
    public void setAdapter(RecyclerViewAdapter adapter) {
        this.adapter = adapter;
        RecyclerView rv = Objects.requireNonNull(getView()).findViewById(R.id.menu_recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
    }

    @Override
    public void updateRecyclerView() {
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void setSections(String[] sections) {
        int position = 0;
        View.OnClickListener ocl = getOclForSectionItem();
        LayoutInflater inflater = this.getLayoutInflater();
        LinearLayout ll_sections = Objects.requireNonNull(getView()).findViewById(R.id.fragment_menu_ll_section);
        for(String section:sections){
            TextView section_view = inflater.inflate(R.layout.fragment_menu_section_item,ll_sections,false).findViewById(R.id.fragment_menu_section_item);
            section_view.setText(section);
            section_view.setTag(position);
            section_view.setOnClickListener(ocl);
            ll_sections.addView(section_view);
            position++;
        }
    }

    private View.OnClickListener getOclForSectionItem() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Display display = Objects.requireNonNull(getActivity()).getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                HorizontalScrollView hr = (Objects.requireNonNull(getView()).findViewById(R.id.fragment_menu_sections_horizontal_scrool_view));
                float offset = (float) (size.x/2.26);
                hr.smoothScrollTo((int) (v.getLeft() - offset),0);

                fragment_menu_presenter.OnSectionItemClick(v);
            }
        };
    }

}
