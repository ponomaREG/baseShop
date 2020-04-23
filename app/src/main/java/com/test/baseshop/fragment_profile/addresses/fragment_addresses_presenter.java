package com.test.baseshop.fragment_profile.addresses;

public class fragment_addresses_presenter implements Interfaces.Presenter{
    private Interfaces.View view;
    private Interfaces.Model model;

    fragment_addresses_presenter(fragment_addresses view){
        this.view = view;
        this.model = new fragment_addresses_model(this);

    }
}
