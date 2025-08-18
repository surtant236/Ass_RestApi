package com.example.ass_restapi.handle;

import com.example.ass_restapi.models.Fruit;

public interface Item_Cart_Fruit_Handle {
    void onIncrease(Fruit fruit);
    void onDecrease(Fruit fruit);
    void onRemove(Fruit fruit);
}