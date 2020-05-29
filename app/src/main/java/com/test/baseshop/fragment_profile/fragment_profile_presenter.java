package com.test.baseshop.fragment_profile;

import android.view.View;

import com.test.baseshop.R;
import com.test.baseshop.fragment_profile.orders.fragment_orders;
import com.test.baseshop.fragment_profile.info.fragment_info;
import com.test.baseshop.fragment_profile.addresses.fragment_addresses;

public class fragment_profile_presenter implements Interfaces.Presenter {

    private Interfaces.View view;
    private View current_view;
    private fragment_info fragment_info;
    private fragment_addresses fragment_addresses;
    private fragment_orders fragment_orders;


    fragment_profile_presenter(Interfaces.View view) {
        this.view = view;
        this.fragment_addresses = new fragment_addresses();
        this.fragment_info = new fragment_info();
        this.fragment_orders = new fragment_orders();
    }




    @Override
    public void OnSectionItemClick(View v) {
        clickOnSectionView(v);
        switch (v.getId()){
            case R.id.fragment_profile_section_orders:
                view.showPage(this.fragment_orders);
                break;
            case R.id.fragment_profile_section_info:
                view.showPage(this.fragment_info);
                break;
            case R.id.fragment_profile_section_addresses:
                view.showPage(this.fragment_addresses);
                break;
//            default:
//                view.showPage(this.fragment_info);
//                break;
        }
    }

    @Override
    public void getStartPage() {
        view.initStartPage(this.fragment_info);
    }

    private void clickOnSectionView(View v){
        if(current_view != null) current_view.setSelected(false);
        current_view = v;
        current_view.setSelected(true);
    }
}
