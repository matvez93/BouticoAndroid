package com.Boutico.Boutico.presentation.shop;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.Boutico.Boutico.core.estimote.ProximityContentManager;

public class ShopModel {
    private ProximityContentManager proximityContentManager;
    private ImageView logoImage;
    private ListView promoList;
    private RelativeLayout relativeLayout;
    private Context context;

    public ProximityContentManager getProximityContentManager() {
        return proximityContentManager;
    }

    public void setProximityContentManager(ProximityContentManager proximityContentManager) {
        this.proximityContentManager = proximityContentManager;
    }

    public ImageView getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(ImageView logoImage) {
        this.logoImage = logoImage;
    }

    public ListView getPromoList() {
        return promoList;
    }

    public void setPromoList(ListView promoList) {
        this.promoList = promoList;
    }

    public RelativeLayout getRelativeLayout() {
        return relativeLayout;
    }

    public void setRelativeLayout(RelativeLayout relativeLayout) {
        this.relativeLayout = relativeLayout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
