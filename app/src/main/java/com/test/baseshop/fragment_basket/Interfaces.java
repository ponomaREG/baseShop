package com.test.baseshop.fragment_basket;


import android.content.Context;

import com.test.baseshop.model_helper.Item;


import java.util.List;

public interface Interfaces {

    interface View {
        void setAdapter(RecyclerViewAdapterBasket adapter);
        void removeItemForOrder(int position);
        void setNumberOfItemForOrderAndCalculateTotalPriceOfEachItem(int position, int number_of_item_for_order, int price);
        void setNewTotalSummOfItemsForOrder(int new_total_summ);
        void showDescOfItemForOrder(int position);
        void hideDescOfItemForOrder(int position);
        void updateRecycleView();
    }

    interface Presenter {
        void OnOrderButtonClick();
        void getDataOfBasketInfo(Context context);
        interface ConnectionBetweenViewAndRecyclerList {
            void tellViewToRemoveItemForOrder(int position);
            void tellViewWhatDatasetUpdated();
            void tellViewToSetNumberOfItemForOrderAndCalculateTotalPriceOfEachItem(int position, int number_of_item_for_order, int price);
            void tellViewToShowLLContainerWithDesc(int position);
            void tellViewToHideLLContainerWithDesc(int position);
            void tellViewToSetTotalSummOfItemsForOrder(int new_total_summ);
        }

        interface ConnectionBetweenModelAndRecyclerList {
            void tellModelToSetNewNumberOfItemsForOrder(int item_id, int new_count_of_items_for_order);
        }
    }

    interface Model {

        interface Basket {
            List<Item> getDataOFBasketInfoRemote(int user_id);
            void sendNewNumberOfItemsForOrder(int user_id, int item_id, int count_of_items_for_order);
        }

        interface Photo {
            void setImageInBackground(Item item);
        }


    }
}
