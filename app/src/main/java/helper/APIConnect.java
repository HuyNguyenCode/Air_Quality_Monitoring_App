package helper;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.air_quality_monitoring_app.model.UserModel;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class APIConnect extends AppCompatActivity {

    public UserModel sendPostRequest(String url, Map<String, String> postData) {
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            StringBuilder postDataString = new StringBuilder();
            for (Map.Entry<String, String> param : postData.entrySet()) {
                if (postDataString.length() != 0) postDataString.append('&');
                postDataString.append(param.getKey());
                postDataString.append('=');
                postDataString.append(param.getValue());
            }
            byte[] postDataBytes = postDataString.toString().getBytes("UTF-8");
            os.write(postDataBytes);

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                // Parse JSON response
                JSONObject jsonResponse = new JSONObject(connection.getInputStream().toString());
                String accessToken = jsonResponse.optString("access_token");
                int expiresIn = jsonResponse.optInt("expires_in");
                int refreshExpires = jsonResponse.optInt("refresh_expires_in");
                String refreshToken = jsonResponse.optString("refresh_token");
                String tokenType = jsonResponse.optString("token_type");
                int notBeforePolicy = jsonResponse.optInt("not-before-policy");
                String sessionState = jsonResponse.optString("session_state");
                String scope = jsonResponse.optString("scope");

                // Create UserModel
                return new UserModel(accessToken, expiresIn, refreshExpires, refreshToken, tokenType, notBeforePolicy, sessionState, scope);
            }
            else {
                Log.d("Error!", "Cannot create UserAPI");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
