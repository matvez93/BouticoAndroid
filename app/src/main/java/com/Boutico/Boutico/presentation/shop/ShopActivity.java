package com.Boutico.Boutico.presentation.shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Boutico.Boutico.R;
import com.Boutico.Boutico.core.estimote.BeaconID;
import com.Boutico.Boutico.core.estimote.EstimoteCloudBeaconDetails;
import com.Boutico.Boutico.core.estimote.EstimoteCloudBeaconDetailsFactory;
import com.Boutico.Boutico.core.estimote.ProximityContentManager;
import com.Boutico.Boutico.facade.ShopFacade;
import com.estimote.sdk.SystemRequirementsChecker;
import com.estimote.sdk.cloud.model.Color;
import database.logEnteredRegion;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ShopActivity extends AppCompatActivity {

    private static final String TAG = "ShopActivity";

    private static final Map<Color, Integer> BACKGROUND_COLORS = new HashMap<>();

    private ShopModel shopModel;
    private ShopFacade shopFacade;

    static {
        BACKGROUND_COLORS.put(Color.ICY_MARSHMALLOW, android.graphics.Color.rgb(109, 170, 199));
        BACKGROUND_COLORS.put(Color.BLUEBERRY_PIE, android.graphics.Color.rgb(98, 84, 158));
        BACKGROUND_COLORS.put(Color.MINT_COCKTAIL, android.graphics.Color.rgb(155, 186, 160));
    }

    private static final int BACKGROUND_COLOR_NEUTRAL = android.graphics.Color.rgb(160, 169, 172);

    private ProximityContentManager proximityContentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_layout);
        //Setup graphics
        ImageView logo = (ImageView) findViewById(R.id.shop_logo);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById((R.id.relativeLayout));
        ListView promoList = (ListView) findViewById(R.id.promo_list);
        this.shopModel = new ShopModel();
        //Setup Facade
        this.shopFacade = new ShopFacade(shopModel);
        this.shopFacade.initShopModel(this.getApplicationContext(),logo,promoList,relativeLayout);
        this.shopFacade.initShop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!SystemRequirementsChecker.checkWithDefaultDialogs(this)) {
            Log.e(TAG, "Can't scan for beacons, some pre-conditions were not met");
            Log.e(TAG, "Read more about what's required at: http://estimote.github.io/Android-SDK/JavaDocs/com/estimote/sdk/SystemRequirementsChecker.html");
            Log.e(TAG, "If this is fixable, you should see a popup on the app's screen right now, asking to enable what's necessary");
        } else {
            Log.d(TAG, "Starting ProximityContentManager content updates");
            proximityContentManager.startContentUpdates();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Stopping ProximityContentManager content updates");
        //proximityContentManager.stopContentUpdates();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        proximityContentManager.destroy();
    }
}
