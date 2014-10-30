package jp.tsur.imageloaderlibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.androidquery.AQuery;
import com.bumptech.glide.Glide;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;


public class MyActivity extends Activity {

    private static final String IMAGE_URL = "http://www.uphyca.com/img/logo_s.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // Picasso
        Picasso picasso = new Picasso.Builder(this).build();

        // デバッグ
        picasso.setIndicatorsEnabled(true);
        picasso.setLoggingEnabled(true);

        ImageView picassoImageView = (ImageView) findViewById(R.id.picasso_image_view);
        picasso.load(IMAGE_URL).placeholder(R.drawable.ic_launcher).into(picassoImageView);


        // UniversalImageLoader
        DisplayImageOptions defaultOptions = new DisplayImageOptions
                .Builder()
                .cacheInMemory(true)
                .showImageOnLoading(R.drawable.ic_launcher)
                .resetViewBeforeLoading(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(this)
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        com.nostra13.universalimageloader.core.ImageLoader imageLoader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
        imageLoader.init(config);
        ImageView uilImageView = (ImageView) findViewById(R.id.uil_image_view);
        imageLoader.displayImage(IMAGE_URL, uilImageView);

        // Volley
        NetworkImageView volleyImageView = (NetworkImageView) findViewById(R.id.volley_image_view);
        RequestQueue queue = Volley.newRequestQueue(this);
        volleyImageView.setDefaultImageResId(R.drawable.ic_launcher);
        volleyImageView.setImageUrl(IMAGE_URL, new com.android.volley.toolbox.ImageLoader(queue, new BitmapCache()));

        // UrlImageViewHelper
        ImageView uivhImageView = (ImageView) findViewById(R.id.uivh_image_view);
        UrlImageViewHelper.setUrlDrawable(uivhImageView, IMAGE_URL, R.drawable.ic_launcher);

        // Android Query
        AQuery aq = new AQuery(this);
        aq.id(R.id.aq_image_view).image(IMAGE_URL, true, false, 100, R.drawable.ic_launcher);

        // Glide
        Glide.with(this)
                .load(IMAGE_URL)
                .placeholder(R.drawable.ic_launcher)
                .into((ImageView) findViewById(R.id.glide_image_view));


       findViewById(R.id.glide_button).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MyActivity.this, GlideActivity.class);
               startActivity(intent);
           }
       });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
