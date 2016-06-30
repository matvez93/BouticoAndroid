package com.Boutico.Boutico.facade;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.Boutico.Boutico.core.estimote.BeaconID;
import com.Boutico.Boutico.core.estimote.EstimoteCloudBeaconDetailsFactory;
import com.Boutico.Boutico.core.estimote.ProximityContentManager;
import com.Boutico.Boutico.core.shop.manager.ShopManager;
import com.Boutico.Boutico.presentation.shop.ShopModel;

import java.util.Arrays;

public class ShopFacade {
    private ShopModel shopModel;
    private ShopManager shopManager;

    public ShopFacade(ShopModel shopModel){
        this.shopModel = shopModel;
    }
    public void initShopModel(Context context, ImageView logo, ListView promoList, RelativeLayout relativeLayout){

        this.shopModel.setLogoImage(logo);
        this.shopModel.setPromoList(promoList);
        this.shopModel.setRelativeLayout(relativeLayout);
        this.shopModel.setContext(context);
    }
    public void initShop(){
        this.shopManager = new ShopManager(shopModel);
        addBeacons();
        this.shopManager.setListeners();
    }
    private void addBeacons(){
        this.shopModel.setProximityContentManager(new ProximityContentManager(this.shopModel.getContext(),
                Arrays.asList(
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 46553, 22384),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 18525, 18420),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 39357, 45812)),
                new EstimoteCloudBeaconDetailsFactory()));
    }
}
