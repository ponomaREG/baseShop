package com.test.baseshop.fragment_menu;

import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.baseshop.R;

import java.util.Objects;

public class fragment_menu extends Fragment implements Interfaces.View{

    private Interfaces.Presenter menu_presenter;

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
        menu_presenter.getSections();
//        menu_presenter.getData(getContext(), com.test.baseshop.fragment_menu.fragment_menu_presenter.ALL);
    }

    private void initPresenter(){
        this.menu_presenter = new fragment_menu_presenter(this);
    }




    @Override
    public void setAdapter(RecyclerViewAdapter adapter) {
        this.adapter = adapter;
        RecyclerView rv = Objects.requireNonNull(getView()).findViewById(R.id.menu_recyclerview);
        rv.setHasFixedSize(true);
        rv.setItemViewCacheSize(20);
        rv.setDrawingCacheEnabled(true);
        rv.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
    }

    @Override
    public void updateRecyclerView() {
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void setSections(int[] sections_codes) {
        View.OnClickListener ocl = getOclForSectionItem();
        LayoutInflater inflater = this.getLayoutInflater();
        LinearLayout ll_sections = Objects.requireNonNull(getView()).findViewById(R.id.fragment_menu_ll_section);
        for(int section_code:sections_codes){
            TextView section_view = inflater.inflate(R.layout.fragment_menu_section_item,ll_sections,false).findViewById(R.id.fragment_menu_section_item);
            section_view.setText(menu_presenter.getTitleOfSectionByCode(section_code));
            section_view.setTag(section_code);
            section_view.setOnClickListener(ocl);
            ll_sections.addView(section_view);
            if(section_code == fragment_menu_presenter.ALL) {
                menu_presenter.OnSectionItemClick(section_view);
                menu_presenter.getData(getContext(),section_code);
            }
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
                menu_presenter.OnSectionItemClick(v);
                menu_presenter.getData(getContext(), (Integer) v.getTag());
            }
        };
    }

}
