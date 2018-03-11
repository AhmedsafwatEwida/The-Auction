package pioneers.safwat.mazad.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pioneers.safwat.mazad.R;

/**
 * Created by safwa on 12/7/2017.
 */

public class RetreiveData2 extends AppCompatActivity {

    List<DataAdapter> ListOfdataAdapter;

    RecyclerView recyclerView;

    private FloatingActionButton fab,fabcars,fabprops;

 //  String HTTP_JSON_URL = "http://192.168.100.8/mazad/ItemJsonData.php";
    //String HTTP_JSON_URL = "http://192.168.43.215/mazad/ItemJsonData.php";
  //  String HTTP_JSON_URL = "http://192.168.43.215/android_login_upload_images/ImageJsonData2.php";

    String Image_Auction_JSON = "auction";
    String Bid_Date_JSON = "biddate";
    String Bid_Hour_JSON = "bidhour";
    String Bid_Type_JSON = "type";
    String Bid_Winner_JSON = "bidwin";
    String Bid_Winner_JSON1 = "bidwin1";
    String Bid_Winner_JSON2 = "bidwin2";
    String Bid_Winner_JSON3 = "bidwin3";
    String Bid_Winner_JSON4 = "bidwin4";
    String Bid_Status_JSON = "status";
    String Image_Price_JSON = "price";
    String Image_Price_JSON1 = "price1";
    String Image_Price_JSON2 = "price2";
    String Image_Price_JSON3 = "price3";
    String Image_Price_JSON4 = "price4";
    String Image_NoBid_JSON = "nobid";
    String Image_Name_JSON = "email";
    String Image_ID_JSON = "id";
    String Image_Descr_JSON = "descr";
    List<String> IdList = new ArrayList<>();
    String Image_URL_JSON = "image_path1";

    JsonArrayRequest RequestOfJSonArray ;

    RequestQueue requestQueue ;

    View view ;

    int RecyclerViewItemPosition ;

    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    RecyclerView.Adapter recyclerViewadapter;

    ArrayList<String> ImageTitleNameArrayListForClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.retreive_data);

        ImageTitleNameArrayListForClick = new ArrayList<>();

        ListOfdataAdapter = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerView.setHasFixedSize(true);

        layoutManagerOfrecyclerView = new LinearLayoutManager(this);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fabcars = (FloatingActionButton) findViewById(R.id.fabcars);
        fabprops = (FloatingActionButton) findViewById(R.id.fabprops);

        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);

        JSON_HTTP_CALL();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent i = new Intent(getApplicationContext(),
                       RetreiveUsers.class);
                startActivity(i);

            }
        });
        fabcars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/

                Intent i = new Intent(getApplicationContext(),
                        RetreiveData2Cars.class);
                startActivity(i);

            }
        });
        fabprops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent i = new Intent(getApplicationContext(),
                        RetreiveData2Props.class);
                startActivity(i);

            }
        });

        // Implementing Click Listener on RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(RetreiveData2.this, new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

              /*  view = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(view != null && gestureDetector.onTouchEvent(motionEvent)) {

                    //Getting RecyclerView Clicked Item value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(view);
                    Intent i = new Intent(getApplicationContext(),
                            Retreiveone2.class);
                   i.putExtra("ListViewValue", IdList.get(RecyclerViewItemPosition).toString());
                    startActivity(i);
                    // Showing RecyclerView Clicked Item value using Toast.
                    Toast.makeText(RetreiveData2.this, ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
                }*/

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });


    }

    public void JSON_HTTP_CALL(){

        RequestOfJSonArray = new JsonArrayRequest(AppConfig.HTTP_JSON_URL_itemjson,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        ParseJSonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(RetreiveData2.this);

        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            DataAdapter GetDataAdapter2 = new DataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);
                // if (json.getString((Image_Name_JSON)).equalsIgnoreCase("ahmed"))
                GetDataAdapter2.setImageTitle("Name:"+json.getString(Image_Name_JSON));
                GetDataAdapter2.setImagePrice("Price:"+json.getString(Image_Price_JSON));
                GetDataAdapter2.setImagePrice1("Price1:"+json.getString(Image_Price_JSON1));
                GetDataAdapter2.setImagePrice2("Price2:"+json.getString(Image_Price_JSON2));
                GetDataAdapter2.setImagePrice3("Price3:"+json.getString(Image_Price_JSON3));
                GetDataAdapter2.setImagePrice4("Price4:"+json.getString(Image_Price_JSON4));
                GetDataAdapter2.setBidDate("Bid Date:"+json.getString(Bid_Date_JSON));
                GetDataAdapter2.setItemType("Item Type:"+json.getString(Bid_Type_JSON));
                GetDataAdapter2.setNoBid("Bid:"+json.getString(Image_NoBid_JSON));
                GetDataAdapter2.setItemStatus("Status:"+json.getString(Bid_Status_JSON));
                GetDataAdapter2.setBidWin("Win1:"+json.getString(Bid_Winner_JSON));
                GetDataAdapter2.setBidWin1("Win2:"+json.getString(Bid_Winner_JSON1));
                GetDataAdapter2.setBidWin2("Win3:"+json.getString(Bid_Winner_JSON2));
                GetDataAdapter2.setBidWin3("Win4:"+json.getString(Bid_Winner_JSON3));
                GetDataAdapter2.setBidWin4("Win5:"+json.getString(Bid_Winner_JSON4));
                GetDataAdapter2.setItemHour("Bid Time:"+json.getString(Bid_Hour_JSON));
                GetDataAdapter2.setItemAuction("Auction:"+json.getString(Image_Auction_JSON));
                GetDataAdapter2.setImageID("Item Code#:"+json.getString(Image_ID_JSON));
                GetDataAdapter2.setItemDescr("Description:"+json.getString(Image_Descr_JSON));
                GetDataAdapter2.setNoBid("NO.of Bids:"+json.getString(Image_NoBid_JSON));
                try {
                    if (json.getString(Image_NoBid_JSON)==null)
                    {   GetDataAdapter2.setImagePrice("Price:"+json.getString(Image_Price_JSON));
                    GetDataAdapter2.setBidWin("Win:"+json.getString(Bid_Winner_JSON));}
                    else if(json.getString(Image_NoBid_JSON)!=null)
                    {
                        if (Integer.parseInt(json.getString(Image_NoBid_JSON))==1)
                        {  GetDataAdapter2.setImagePrice("Price:"+json.getString(Image_Price_JSON));
                        GetDataAdapter2.setBidWin("Win:"+json.getString(Bid_Winner_JSON));}
                        else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==2)
                    {  GetDataAdapter2.setImagePrice("Price1:"+json.getString(Image_Price_JSON1));
                        GetDataAdapter2.setBidWin("Win1:"+json.getString(Bid_Winner_JSON1));}
                        else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==3)
                    {  GetDataAdapter2.setImagePrice("Price2:"+json.getString(Image_Price_JSON2));
                        GetDataAdapter2.setBidWin("Win2:"+json.getString(Bid_Winner_JSON2));}
                        else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==4)
                    {   GetDataAdapter2.setImagePrice("Price3:"+json.getString(Image_Price_JSON3));
                        GetDataAdapter2.setBidWin("Win3:"+json.getString(Bid_Winner_JSON3));}

                        else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==5)
                    {  GetDataAdapter2.setImagePrice("Price4:"+json.getString(Image_Price_JSON4));
                        GetDataAdapter2.setBidWin("Win4:"+json.getString(Bid_Winner_JSON4));}
                }

               }
                catch(NumberFormatException nfe) {
                    // Log exception.

                }
                IdList.add(json.getString("id").toString());
                // Adding image title name in array to display on RecyclerView click event.
                ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                //ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                GetDataAdapter2.setImageUrl(json.getString(Image_URL_JSON));
                // ListOfdataAdapter.add(GetDataAdapter2);
               // ListOfdataAdapter.add(GetDataAdapter2);
            } catch (JSONException e) {

                e.printStackTrace();
            }

            ListOfdataAdapter.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapteritemsuser(ListOfdataAdapter, this);

        recyclerView.setAdapter(recyclerViewadapter);
    }
}
