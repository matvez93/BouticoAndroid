package database;

/**
 * Created by Kevin on 2016-01-15.
 */

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Background Async Task to Create new product
 * */
class CreateNewUser extends AsyncTask<String, String, String> {

    JSONParser jsonParser = new JSONParser();

    // url to create new product
    private String url_create_user = "http://boutico.ca/database/addUser2.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    /**
     * Before starting background thread Show Progress Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * Creating product
     * */
    protected String doInBackground(String... args) {
        String username = "username2";
        String firstname = "firstname1";
        String lastname = "lastname1";
        String email = "email";
        String birthday = "1993-09-08";
        String gender = "M";
        String facebook_id = "AAA44324DDFGD123";

        // Building Parameters

        ContentValues values = new ContentValues();
        values.put("firstname",firstname);
        values.put("lastname",lastname);
        values.put("birthday",birthday.toString());
        values.put("email", email);
        values.put("gender", gender);
        values.put("facebook_id", facebook_id);

        // getting JSON Object
        // Note that create product url accepts POST method
        JSONObject json = jsonParser.makeHttpRequest(url_create_user,
                "POST", values);

        // check log cat fro response
        Log.d("Create Response", json.toString());

        // check for success tag
        try {
            int success = json.getInt(TAG_SUCCESS);

            if (success == 1) {

            } else {
                // failed to create product
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * After completing background task Dismiss the progress dialog
     * **/
    protected void onPostExecute(String file_url) {
        // dismiss the dialog once done

    }
}
