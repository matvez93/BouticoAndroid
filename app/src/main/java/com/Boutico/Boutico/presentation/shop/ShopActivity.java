package com.Boutico.Boutico.presentation.shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.Boutico.Boutico.R;
import com.Boutico.Boutico.core.estimote.BeaconID;
import com.Boutico.Boutico.core.estimote.EstimoteCloudBeaconDetails;
import com.Boutico.Boutico.core.estimote.EstimoteCloudBeaconDetailsFactory;
import com.Boutico.Boutico.core.estimote.ProximityContentManager;
import com.estimote.sdk.SystemRequirementsChecker;
import com.estimote.sdk.cloud.model.Color;
import database.logEnteredRegion;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ShopActivity extends AppCompatActivity {

    private static final String TAG = "ShopActivity";

    private static final Map<Color, Integer> BACKGROUND_COLORS = new HashMap<>();

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
        setContentView(R.layout.activity_main);

        proximityContentManager = new ProximityContentManager(this,
                Arrays.asList(
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 46553, 22384),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 18525, 18420),
                        new BeaconID("B9407F30-F5F8-466E-AFF9-25556B57FE6D", 39357, 45812)),
                new EstimoteCloudBeaconDetailsFactory());

        proximityContentManager.setListener(new ProximityContentManager.Listener() {
            @Override
            public void onContentChanged(Object content) {
                String text;
                ImageView imgview;
                Integer backgroundColor;
                if (content != null) {
                    EstimoteCloudBeaconDetails beaconDetails = (EstimoteCloudBeaconDetails) content;
                    text = "Vous êtes chez " + beaconDetails.getBeaconName();

                    //LOG ENTERED REGION
                    //TODO GET USER_ID
                    String user_id = "15423";
                    //TODO GET BEACON_ID IN DATABASE FROM UUID
                    String beacon_id = content.toString();
                    new logEnteredRegion().execute(user_id,beacon_id);

                    backgroundColor = BACKGROUND_COLORS.get(beaconDetails.getBeaconColor());
                    ImageView img= (ImageView) findViewById(R.id.image);
                    ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.tiger);

                } else {
                    text = "Aucun magasin à proximité.";
                    ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.beacon);
                    backgroundColor = null;
                }
                ((TextView) findViewById(R.id.textView)).setText(text);
                findViewById(R.id.relativeLayout).setBackgroundColor(
                        backgroundColor != null ? backgroundColor : BACKGROUND_COLOR_NEUTRAL);
            }
        });
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
        proximityContentManager.stopContentUpdates();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        proximityContentManager.destroy();
    }
}
