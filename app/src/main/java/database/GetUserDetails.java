package database;

import android.app.Activity;
import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kevin on 2016-01-15.
 */
public class GetUserDetails extends AsyncTask<String, String, String> {
    JSONParser jsonParser = new JSONParser();
    private Activity mActivity;


    private String url_get_user_details = "http://boutico.ca/database/getUser.php";
    /**
     * Before starting background thread Show Progress Dialog
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
    public GetUserDetails(Activity activity)
    {
        this.mActivity = activity;
    }
    /**
     * Getting product details in background thread
     */
    protected String doInBackground(String... params) {

        // updating UI from Background Thread

        mActivity.runOnUiThread(new Runnable() {
            public void run() {
                // Check for success tag
                int success;
                try {
                    String facebook_id = "asdasdasdsadsa";
                    // Building Parameters
                    ContentValues values = new ContentValues();
                    values.put("facebook_id", facebook_id);

                    // getting product details by making HTTP request
                    // Note that product details url will use GET request
                    JSONObject json = jsonParser.makeHttpRequest(
                            url_get_user_details, "GET", values);

                    // check your log for json response
                    Log.d("Single User Details", json.toString());

                    // json success tag
                    success = json.getInt("success");
                    if (success == 1) {
                        // successfully received product details
                        JSONArray userObj = json
                                .getJSONArray("users"); // JSON Array

                        // get first product object from JSON Array
                        JSONObject user = userObj.getJSONObject(0);

                        // product with this pid found
                        // Edit Text

                        // display product data in EditText
                        //txtName.setText(product.getString(TAG_NAME));
                        //GET USER NAME : String name = user.getString("name");

                    } else {
                        // product with pid not found
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return null;
    }

    /**
     * After completing background task Dismiss the progress dialog
     **/
    protected void onPostExecute(String file_url) {
        // dismiss the dialog once got all details

    }
}
