package com.example.notandi.easypayprototype;

/**
 * Created by erla on 08/05/15.
 */
public class ProductRecord {

    String mName;
    int mPrice;

    ProductRecord( String name, int price ) {
        mName = name;
        mPrice = price;
    }

    String getName() { return mName; }

    int getPrice() { return mPrice; }
}
