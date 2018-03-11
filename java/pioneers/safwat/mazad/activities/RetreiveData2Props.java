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
import android.widget.TextView;
import android.widget.Toast;

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

public class RetreiveData2Props extends AppCompatActivity {

    List<DataAdapter2> ListOfdataAdapter;

    RecyclerView recyclerView;

    private FloatingActionButton fab,fabcars,fabprops;

   String HTTP_JSON_URL = "http://192.168.100.8/mazad/ItemJsonData.php";
    //String HTTP_JSON_URL = "http://192.168.43.215/mazad/ItemJsonData.php";
  //  String HTTP_JSON_URL = "http://192.168.43.215/android_login_upload_images/ImageJsonData2.php";

    String Image_Auction_JSON = "auction";
    String Bid_Date_JSON = "biddate";
    String Bid_Hour_JSON = "bidhour";
    String Bid_Type_JSON = "proptype";
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
    String yearu,modelu,makeu,warnu,bdateu,btimeu,priceu,engineu,codeu,descu,statusu,millageu,typeu,image1,image2,image3,
            priceu2,priceu3,priceu4,priceu5,winu,winu2,winu3,winu4,winu5,nameuu ;
    String Image_URL_JSON = "image_path1";
    String Image_URL_JSON1 = "image_path2";
    String Image_URL_JSON2 = "image_path3";

    JsonArrayRequest RequestOfJSonArray ;

    RequestQueue requestQueue ;

    View view ;

    int RecyclerViewItemPosition ;

    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    RecyclerView.Adapter recyclerViewadapter;

    ArrayList<String> ImageTitleNameArrayListForClick;
    ArrayList<String> Image1;
    ArrayList<String> Image2;
    ArrayList<String> Image3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.retreive_dataprops);

        ImageTitleNameArrayListForClick = new ArrayList<>();

        ListOfdataAdapter = new ArrayList<>();
        Image1 = new ArrayList<>();
        Image2 = new ArrayList<>();
        Image3 = new ArrayList<>();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerView.setHasFixedSize(true);

        layoutManagerOfrecyclerView = new LinearLayoutManager(this);



        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);

        JSON_HTTP_CALL();



        // Implementing Click Listener on RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(RetreiveData2Props.this, new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                view = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(view != null && gestureDetector.onTouchEvent(motionEvent)) {
                    View vview = recyclerView.findContainingItemView( view);
                    //  RecyclerView.ViewHolder vview = recyclerView.getChildAdapterPosition(view);
                    TextView carmake = (TextView) vview.findViewById(R.id.ImagemakeTextView);
                    TextView nameu = (TextView) vview.findViewById(R.id.ImageNameTextView);
                    TextView carmodel = (TextView) vview.findViewById(R.id.ImagemodelTextView);
                    TextView carwaranty = (TextView) vview.findViewById(R.id.ImagewarnTextView);
                    TextView carmillage = (TextView) vview.findViewById(R.id.ImagemillageTextView);
                    TextView caryear = (TextView) vview.findViewById(R.id.ImageyearTextView);
                    TextView cartype = (TextView) vview.findViewById(R.id.ImagetypeTextView);
                    TextView biddate = (TextView) vview.findViewById(R.id.ImagebidateTextView);
                    TextView bidtime = (TextView) vview.findViewById(R.id.ImagebidtimeTextView);
                    TextView itemcode = (TextView) vview.findViewById(R.id.ImageIDTextView);
                    TextView price = (TextView) vview.findViewById(R.id.ImagepriceTextView);
                    TextView price2 = (TextView) vview.findViewById(R.id.Imageprice2TextView);
                    TextView price3 = (TextView) vview.findViewById(R.id.Imageprice3TextView);
                    TextView price4 = (TextView) vview.findViewById(R.id.Imageprice4TextView);
                    TextView price5 = (TextView) vview.findViewById(R.id.Imageprice5TextView);
                    TextView win = (TextView) vview.findViewById(R.id.ImagewinTextView);
                    TextView win2 = (TextView) vview.findViewById(R.id.Imagewin2TextView);
                    TextView win3 = (TextView) vview.findViewById(R.id.Imagewin3TextView);
                    TextView win4 = (TextView) vview.findViewById(R.id.Imagewin4TextView);
                    TextView win5 = (TextView) vview.findViewById(R.id.Imagewin5TextView);
                    TextView carengine = (TextView) vview.findViewById(R.id.ImageengineTextView);
                    TextView descr = (TextView) vview.findViewById(R.id.ImagedescTextView);
                    TextView status = (TextView) vview.findViewById(R.id.ImagestatusTextView);
                    //Getting RecyclerView Clicked Item value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(view);
                    Intent i = new Intent(getApplicationContext(),
                            Retreiveone2prop.class);
                   i.putExtra("ListViewValue", IdList.get(RecyclerViewItemPosition).toString());


                    makeu=carmake.getText().toString();
                    modelu=carmodel.getText().toString();
                    nameuu=nameu.getText().toString();
                    yearu=caryear.getText().toString();
                    warnu=carwaranty.getText().toString();
                    bdateu=biddate.getText().toString();
                    btimeu=bidtime.getText().toString();
                    millageu=carmillage.getText().toString();
                    statusu=status.getText().toString();
                    descu=descr.getText().toString();
                    typeu=cartype.getText().toString();
                    engineu=carengine.getText().toString();
                    priceu=price.getText().toString();
                    priceu2=price2.getText().toString();
                    priceu3=price3.getText().toString();
                    priceu4=price4.getText().toString();
                    priceu5=price5.getText().toString();
                    winu=win.getText().toString();
                    winu2=win2.getText().toString();
                    winu3=win3.getText().toString();
                    winu4=win4.getText().toString();
                    winu5=win5.getText().toString();
                    codeu=itemcode.getText().toString();
                    image1=Image1.get(RecyclerViewItemPosition);
                    image2=Image2.get(RecyclerViewItemPosition);
                    image3=Image3.get(RecyclerViewItemPosition);
                    i.putExtra("makeValue", makeu);
                    i.putExtra("modelValue", modelu);
                    i.putExtra("yearValue", yearu);
                    i.putExtra("typeValue", typeu);
                    i.putExtra("stausValue", statusu);
                    i.putExtra("idValue", codeu);
                    i.putExtra("destValue", descu);
                    i.putExtra("dateValue", bdateu);
                    i.putExtra("timeValue", btimeu);
                    i.putExtra("engineValue", engineu);
                    i.putExtra("warnValue", warnu);
                    i.putExtra("millagValue", millageu);
                    i.putExtra("priceValue", priceu);
                    i.putExtra("price2Value", priceu2);
                    i.putExtra("price3Value", priceu3);
                    i.putExtra("price4Value", priceu4);
                    i.putExtra("price5Value", priceu5);
                    i.putExtra("win1Value", winu);
                    i.putExtra("win2Value", winu2);
                    i.putExtra("win3Value", winu3);
                    i.putExtra("win4Value", winu4);
                    i.putExtra("win5Value", winu5);
                    i.putExtra("nameu", nameuu);
                    i.putExtra("image1", image1);
                    i.putExtra("image2", image2);
                    i.putExtra("image3", image3);




                    startActivity(i);
                    // Showing RecyclerView Clicked Item value using Toast.
                    Toast.makeText(RetreiveData2Props.this, ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
                }

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

        RequestOfJSonArray = new JsonArrayRequest(AppConfig.HTTP_JSON_ItemsProp,

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

        requestQueue = Volley.newRequestQueue(RetreiveData2Props.this);

        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            DataAdapter2 GetDataAdapter2 = new DataAdapter2();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);
                // if (json.getString((Image_Name_JSON)).equalsIgnoreCase("ahmed"))
                GetDataAdapter2.setImageTitle(json.getString(Image_Name_JSON));
                GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON));
                GetDataAdapter2.setImagePrice1("Price1:"+json.getString(Image_Price_JSON1));
                GetDataAdapter2.setImagePrice2("Price2:"+json.getString(Image_Price_JSON2));
                GetDataAdapter2.setImagePrice3("Price3:"+json.getString(Image_Price_JSON3));
                GetDataAdapter2.setImagePrice4("Price4:"+json.getString(Image_Price_JSON4));
                GetDataAdapter2.setBidDate(json.getString(Bid_Date_JSON));
                GetDataAdapter2.setItemtype(json.getString(Bid_Type_JSON));
                GetDataAdapter2.setNoBid("Bid:"+json.getString(Image_NoBid_JSON));
                GetDataAdapter2.setItemStatus(json.getString(Bid_Status_JSON));
                GetDataAdapter2.setBidWin(json.getString(Bid_Winner_JSON));
                GetDataAdapter2.setBidWin1("Win1:"+json.getString(Bid_Winner_JSON1));
                GetDataAdapter2.setBidWin2("Win2:"+json.getString(Bid_Winner_JSON2));
                GetDataAdapter2.setBidWin3("Win3:"+json.getString(Bid_Winner_JSON3));
                GetDataAdapter2.setBidWin4("Win4:"+json.getString(Bid_Winner_JSON4));
                GetDataAdapter2.setItembidtime(json.getString(Bid_Hour_JSON));
                GetDataAdapter2.setItemAuction(json.getString(Image_Auction_JSON));
                GetDataAdapter2.setImageID(json.getString(Image_ID_JSON));
                GetDataAdapter2.setItemDescr(json.getString(Image_Descr_JSON));
                GetDataAdapter2.setNoBid("NO.of Bids:"+json.getString(Image_NoBid_JSON));
                try {
                    if (json.getString(Image_NoBid_JSON)==null)
                    {   GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON));
                    GetDataAdapter2.setBidWin(json.getString(Bid_Winner_JSON));}
                    else if(json.getString(Image_NoBid_JSON)!=null)
                    {
                        if (Integer.parseInt(json.getString(Image_NoBid_JSON))==1)
                        {  GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON));
                        GetDataAdapter2.setBidWin(json.getString(Bid_Winner_JSON));}
                        else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==2)
                    {  GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON1));
                        GetDataAdapter2.setBidWin(json.getString(Bid_Winner_JSON1));}
                        else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==3)
                    {  GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON2));
                        GetDataAdapter2.setBidWin(json.getString(Bid_Winner_JSON2));}
                        else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==4)
                    {   GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON3));
                        GetDataAdapter2.setBidWin(json.getString(Bid_Winner_JSON3));}

                        else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==5)
                    {  GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON4));
                        GetDataAdapter2.setBidWin(json.getString(Bid_Winner_JSON4));}
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
                GetDataAdapter2.setImageUrl1(json.getString(Image_URL_JSON1));
                GetDataAdapter2.setImageUrl2(json.getString(Image_URL_JSON2));
                Image1.add(json.getString(Image_URL_JSON));
                Image2.add(json.getString(Image_URL_JSON1));
                Image3.add(json.getString(Image_URL_JSON2));
                // ListOfdataAdapter.add(GetDataAdapter2);
               // ListOfdataAdapter.add(GetDataAdapter2);
            } catch (JSONException e) {

                e.printStackTrace();
            }

            ListOfdataAdapter.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapter3itemadmin(ListOfdataAdapter, this);

        recyclerView.setAdapter(recyclerViewadapter);
    }
}
