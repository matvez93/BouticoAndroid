package com.Boutico.Boutico.utility;

import android.graphics.Color;

import com.Boutico.Boutico.core.promotion.Promotion;

import java.util.ArrayList;

/**
 * Created by Kevin on 2016-06-12.
 */
public class ShopUtility {
    private ShopUtility(){}
    public static String getShopLogoUrl(String beacon_id){
        //TODO: return logo url
        return "url";
    }
    public static int getShopColor(String beacon_id){
        //TODO: return background color
        return Color.YELLOW;
    }
    public static ArrayList<Promotion> getPromotionList(String beacon_id){
        ArrayList<Promotion> promotionList = new ArrayList<Promotion>();
        //TODO get all promotions for a shop and create promotions list
        return promotionList;
    }
}
