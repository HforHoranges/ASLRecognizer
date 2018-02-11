package hforh.oranges.aslrecognizer;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetHTML().execute();
    }
    public class GetHTML extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            String urlLink = "http://www.signasl.org/sign/orange";
            URL url;
            try {
                url = new URL(urlLink);
                Log.d("ocr", "Opening url connection " + urlLink);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader httpInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuffer stringBuffer = new StringBuffer();
                String inputLine;
                while ((inputLine = httpInput.readLine()) != null) {
                    Log.d("OCR", inputLine.toString());

                    stringBuffer.append(inputLine);
                }
                Log.d("ocr", "Closing connection");
                httpInput.close();


            } catch (Exception e) {
                Log.e("ocr", "Could not get string from URL.");
                e.printStackTrace();
            }
            return null;
        }
    }
}