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

public class ImageAdapter1 {

    public static ImageAdapter1 imageAdapter1;

    public Network networkOBJ1 ;

    public RequestQueue requestQueue2;

    public ImageLoader Imageloader2;

    public Cache cache2 ;

    public static Context context2;

    LruCache<String, Bitmap> LRUCACHE = new LruCache<String, Bitmap>(30);

    private ImageAdapter1(Context context) {

        this.context2 = context;

        this.requestQueue2 = RequestQueueFunction();

        Imageloader2 = new ImageLoader(requestQueue2, new ImageLoader.ImageCache() {

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

        return Imageloader2;
    }

    public static ImageAdapter1 getInstance(Context SynchronizedContext) {

        if (imageAdapter1 == null) {

            imageAdapter1 = new ImageAdapter1(SynchronizedContext);
        }
        return imageAdapter1;
    }

    public RequestQueue RequestQueueFunction() {

        if (requestQueue2 == null) {

            cache2 = new DiskBasedCache(context2.getCacheDir());

            networkOBJ1 = new BasicNetwork(new HurlStack());

            requestQueue2 = new RequestQueue(cache2, networkOBJ1);

            requestQueue2.start();
        }
        return requestQueue2;
    }
}