package pioneers.safwat.mazad.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by safwa on 12/7/2017.
 */

public class ImageAdapter2 {

    public static ImageAdapter2 imageAdapter2;

    public Network networkOBJ2 ;

    public RequestQueue requestQueue3;

    public ImageLoader Imageloader3;

    public Cache cache3 ;

    public static Context context3;

    LruCache<String, Bitmap> LRUCACHE = new LruCache<String, Bitmap>(30);

    private ImageAdapter2(Context context) {

        this.context3 = context;

        this.requestQueue3 = RequestQueueFunction();

        Imageloader3 = new ImageLoader(requestQueue3, new ImageLoader.ImageCache() {

            @Override
            public Bitmap getBitmap(String URL) {

                return LRUCACHE.get(URL);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

                LRUCACHE.put(url, bitmap);
            }
        });
    }

    public ImageLoader getImageLoader() {

        return Imageloader3;
    }

    public static ImageAdapter2 getInstance(Context SynchronizedContext) {

        if (imageAdapter2 == null) {

            imageAdapter2 = new ImageAdapter2(SynchronizedContext);
        }
        return imageAdapter2;
    }

    public RequestQueue RequestQueueFunction() {

        if (requestQueue3 == null) {

            cache3 = new DiskBasedCache(context3.getCacheDir());

            networkOBJ2 = new BasicNetwork(new HurlStack());

            requestQueue3 = new RequestQueue(cache3, networkOBJ2);

            requestQueue3.start();
        }
        return requestQueue3;
    }
}