package hforh.oranges.aslrecognizer;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

public class MainActivity extends Activity {

    private String textToTranslate;
    private String videoLinks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.gobackbutton)).setVisibility(View.INVISIBLE);
        ((Button)findViewById(R.id.gobackbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getTextToTranslateFromIntent();
        new GetHTML().execute();
    }

    public void getTextToTranslateFromIntent() {
        Intent intent = getIntent();
        textToTranslate = intent.getStringExtra("textToTranslate2");
    }

    public class GetHTML extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            for (String word : textToTranslate.split(" ")) {
                Log.d("ocr", word);
                String urlLink = "http://www.signasl.org/sign/" + word;
                URL url;
                try {
                    url = new URL(urlLink);
                    Log.d("ocr", "Opening url connection " + urlLink);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    BufferedReader httpInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer stringBuffer = new StringBuffer();
                    String inputLine, video = "";
                    while ((inputLine = httpInput.readLine()) != null) {
                        if (inputLine.contains(".mp4")) {
                            video = inputLine.substring(inputLine.lastIndexOf("content=") + 8);
                            video = video.replace("\"", "");
                            Log.d("OCR", video.toString());
                            stringBuffer.append(video);
                            break;
                        }
                        else if ((inputLine.contains("youtube"))){
                            video = inputLine.substring((inputLine.lastIndexOf("src=") + 8));
                            video = video.replace("\"", "");
                            Log.d("OCR", video.toString());
                            stringBuffer.append(video);
                            break;
                        }
                    }
                    if (video == "") {
                        Log.d("OCR", "Sorry no video found, try again");
                    }
                    else {
                        videoLinks = stringBuffer.toString();
                    }
                    Log.d("ocr", "Closing connection");
                    httpInput.close();


                } catch (Exception e) {
                    Log.e("ocr", "Could not get string from URL.");
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (videoLinks == "" || videoLinks == null) {
                ((TextView) findViewById(R.id.statusTextBox)).setText("Sorry no video found :(");
                ((Button)findViewById(R.id.gobackbutton)).setVisibility(View.VISIBLE);
            }
            else {
                startAppropriateActivity();
                finish();
            }
        }
    }

    private void startAppropriateActivity() {
        if (videoLinks.contains("youtube")) {
            Intent intent = new Intent(MainActivity.this, ASLDisplayActivity.class);
            intent.putExtra("videoLinks", videoLinks);
            startActivity(intent);
        }
        else{

        }
    }
}