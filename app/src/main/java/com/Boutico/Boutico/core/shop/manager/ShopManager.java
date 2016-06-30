package com.Boutico.Boutico.core.shop.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.Boutico.Boutico.core.estimote.EstimoteCloudBeaconDetails;
import com.Boutico.Boutico.core.estimote.ProximityContentManager;
import com.Boutico.Boutico.presentation.shop.ShopModel;
import com.Boutico.Boutico.utility.LogUtility;
import com.Boutico.Boutico.utility.ShopUtility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ShopManager {
    private ShopModel shopModel;

    public ShopManager(ShopModel shopModel) {
        this.shopModel = shopModel;
    }

    public void setListeners() {
        this.shopModel.getProximityContentManager().setListener(new ProximityContentManager.Listener() {

            @Override
            public void onContentChanged(Object content) {
                String text;
                ImageView imgview;
                Integer backgroundColor;
                if (content != null) {
                    EstimoteCloudBeaconDetails beaconDetails = (EstimoteCloudBeaconDetails) content;
                    text = "Vous êtes chez " + beaconDetails.getBeaconName();

                    //TODO GET USER_ID
                    String user_id = "15423";
                    //TODO GET BEACON_ID IN DATABASE FROM UUID
                    String beacon_id = content.toString();
                    //LOG
                    LogUtility.logEnteredRegion(user_id, beacon_id);

                    updateShopLayout(beacon_id);

                } else {
                    updateShopLayout(null);
                }

            }
        });
    }

    private void updateShopLayout(String beacon_id) {
        if (beacon_id == null) {

        } else {
            //UPDATE SHOP LOGO
            URL url = null;
            try {
                url = new URL(ShopUtility.getShopLogoUrl(beacon_id));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Bitmap bmp = null;
            try {
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.shopModel.getLogoImage().setImageBitmap(bmp);

            //UPDATE Background color
            this.shopModel.getRelativeLayout().setBackgroundColor(ShopUtility.getShopColor(beacon_id));

            //UPDATE PromoList
            //TODO: setPromotionList
        }
    }
}
