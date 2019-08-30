package com.example.clicker;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends Activity {

    TextView txtResponse;
    TextView questionTxt;
    Button choiceA;
    Button choiceB;
    Button choiceC;
    Button choiceD;
    String count;
    int results = 0;
    Button changeChoice;
    Button nextQuestion;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        choiceA = (Button) findViewById(R.id.button);
        choiceB = (Button) findViewById(R.id.button2);
        choiceC = (Button) findViewById(R.id.button3);
        choiceD = (Button) findViewById(R.id.button4);
        changeChoice = (Button) findViewById(R.id.button5);
        submit = (Button) findViewById(R.id.button6);
        nextQuestion = (Button) findViewById(R.id.button8);

        txtResponse = (TextView) findViewById(R.id.txtResponseId);
        questionTxt = (TextView) findViewById(R.id.questionNo);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Pop.class);
                i.putExtra("MY_KEY", results);
                startActivity(i);
                //startActivity(new Intent(MainActivity.this,Pop.class));
            }
        });
    }



    public void btnOnClickHandler(View v){
        if (choiceA.isPressed()){
            count = "A";
            results += 1;
        } else if(choiceB.isPressed()) {
            count = "B";
        } else if(choiceC.isPressed()){
            count = "C";
        } else if(choiceD.isPressed()){
            count = "D";
        }
        new HttpTask().execute("http://10.0.2.2:9999/clicker/select?questionNo=" + questionTxt.getText().toString() + "&choice=" + count); // Send HTTP request
        Toast.makeText(this, "Choice Selected", Toast.LENGTH_LONG).show(); // Toast a message

        choiceA.setEnabled(false);
        choiceB.setEnabled(false);
        choiceC.setEnabled(false);
        choiceD.setEnabled(false);
    }

    public void changeChoiceOnClickHandler(View v) {
        if (changeChoice.isPressed()) {
            choiceA.setEnabled(true);
            choiceB.setEnabled(true);
            choiceC.setEnabled(true);
            choiceD.setEnabled(true);

            new HttpTask().execute("http://10.0.2.2:9999/clicker/delete?id=1001&questionNo=" + questionTxt.getText().toString()); // Send HTTP request
            Toast.makeText(this, "Choice Deleted", Toast.LENGTH_LONG).show(); // Toast a message
        }
    }

    public void nextQuestionOnClickHandler(View v){
        CharSequence temp = questionTxt.getText();
        // if question 10, make next button disable
        if(nextQuestion.isPressed()){
            if(Integer.parseInt(temp.toString()) == 9){
                nextQuestion.setEnabled(false);
                questionTxt.setText(Integer.toString(Integer.parseInt(temp.toString()) + 1) );
                choiceA.setEnabled(true);
                choiceB.setEnabled(true);
                choiceC.setEnabled(true);
                choiceD.setEnabled(true);
            }  else {
                nextQuestion.setEnabled(true);
                questionTxt.setText(Integer.toString(Integer.parseInt(temp.toString()) + 1) );
                choiceA.setEnabled(true);
                choiceB.setEnabled(true);
                choiceC.setEnabled(true);
                choiceD.setEnabled(true);
            }
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
