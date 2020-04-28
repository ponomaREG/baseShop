package com.test.baseshop.fragment_profile.addresses.add_new;

import com.test.baseshop.model_helper.Address;
import com.test.baseshop.model_helper.Json;

public class fragment_add_new_model implements Interfaces.Model{



    private Json json;

    fragment_add_new_model(){
        this.json = new Json();
    }

    @Override
    public void sendNewInfo(int USER_ID,Address address) {
        json.jsonify_addresses_add(USER_ID,address);
    }
}
