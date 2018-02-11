package hforh.oranges.aslrecognizer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class ASLDisplayActivity extends AppCompatActivity {

    private String youtubeVideoStringBeforeURL ="<html><center><body><iframe width=\"420\" height=\"315\" src=\"";
    private String youtubeVideoStringAfterURL = "\" frameborder=\"0\" allowfullscreen></iframe></body></html>";
    private String youtubeVideoURL = "https://www.youtube.com/embed/zX1q1NLwznY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asldisplay);
    }

    public void DisplayVideo(String url){
        if (url.contains("youtube")){
            DisplayYoutubeVideo(url);
        }
        else{
            DisplayRegularVideo(url);
        }
    }

    private void DisplayRegularVideo(String url) {
        String path = "https://www.youtube.com/watch?v=RlIdWGOVfTQ";
        Uri uri = Uri.parse(path);
        final VideoView vv = (VideoView) findViewById(R.id.videoView3);
        // Start the MediaController
        MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(vv);
        vv.setMediaController(mediacontroller);
        vv.setVideoURI(uri);
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                vv.start();
            }
        });
    }

    private void DisplayYoutubeVideo(String url) {
        String frameVideo = youtubeVideoStringBeforeURL + url + youtubeVideoStringAfterURL;
        Log.d("ASLDISP", frameVideo);
        Log.d("ASLDISP", "<html><center><body><iframe width=\"420\" height=\"315\" src=\"https://www.youtube.com/embed/47yJ2XCRLZs\" frameborder=\"0\" allowfullscreen></iframe></body></html>");

        WebView displayYoutubeVideo = (WebView) findViewById(R.id.youtubeWebView);
        displayYoutubeVideo . setWebViewClient ( new  WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = displayYoutubeVideo . getSettings ();
        webSettings.setJavaScriptEnabled(true);
        displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");
    }
}
