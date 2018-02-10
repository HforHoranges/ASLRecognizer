package hforh.oranges.aslrecognizer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class ASLDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asldisplay);

        String path = "https://media.signbsl.com/videos/asl/startasl/mp4/orange.mp4";
        Uri uri = Uri.parse(path);
        final VideoView vv = (VideoView) findViewById(R.id.videoView2);
        // Start the MediaController
        MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(vv);
        vv.setMediaController(mediacontroller);
        vv.setVideoURI(uri);
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                vv.start();
            }
        });
    }

    public void DisplayVideo(String url){

    }
}
