package com.example.clicker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;



public class Login extends Activity {

    Button login;
    EditText username;
    EditText password;
    TextView txtResponse;
    //TextView id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.button7);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        txtResponse = (TextView) findViewById(R.id.txtResponse);
        // id = (TextView) findViewById(R.id.textView2);



    }

    public void btnOnClickHandler(View v){
        if(login.isPressed()){
            //new HttpTask().execute("http://10.0.2.2:9999/clicker/login?id=" + id.getText().toString() + "&username=" + username.getText().toString() + "&password=" + password.getText().toString()); // Send HTTP request
            new HttpTask().execute("http://10.0.2.2:9999/clicker/login?username=" + username.getText().toString() + "&password=" + password.getText().toString()); // Send HTTP request
            Toast.makeText(this, "SEND", Toast.LENGTH_LONG).show(); // Toast a message

            //id.setText(Integer.toString(Integer.parseInt(id.toString()) + 1) );
            //new HttpTask().execute("http://10.0.2.2:9999/clicker/login?id=" + id.getText().toString() + "&username=" + username.getText().toString() + "&password=" + password.getText().toString()); // Send HTTP request
            startActivity(new Intent(Login.this, MainActivity.class));


        }
    }
    // Run the HTTP request in a background thread, separating from the main UI thread
    private class HttpTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strURLs) {
            URL url = null;
            HttpURLConnection conn = null;
            try {
                url = new URL(strURLs[0]);
                conn = (HttpURLConnection) url.openConnection();
                // Get the HTTP response code (e.g., 200 for "OK", 404 for "Not found")
                // and pass a string description in result to onPostExecute(String result)
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {  // 200
                    return "OK (" + responseCode + ")";
                } else {
                    return "Fail (" + responseCode + ")";
                }
            } catch (IOException e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }

        // Displays the result of the AsyncTask.
        // The String result is passed from doInBackground().
        @Override
        protected void onPostExecute(String result) {
            txtResponse.setText(result);  // put it on TextView
        }
    }
}
