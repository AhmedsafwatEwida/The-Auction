package pioneers.safwat.mazad.activities;

import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import pioneers.safwat.mazad.R;
import pioneers.safwat.mazad.fragment.HomeFragment;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by safwa on 12/11/2017.
 */

public class CarsFragmentBidActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Dialog dialog,dialog2;
    ProgressDialog pDialog;
    List<DataAdapter2> ListOfdataAdapter;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    ProgressDialog progressDialog;
    FloatingActionButton updatefast;


  //  String HTTP_JSON_URL = "http://192.168.100.8/mazad/AuctionListprop.php";

    String biddateold,bidhouenew,biddatenew;
    String User_Downpay_JSON = "downpayment";
    String User_Downpay_JSON2 = "downpaymentcar";
    String Image_Email_JSON = "email";
    String Image_Auction_JSON = "auction";
    String Bid_Date_JSON = "biddate";
    String Bid_Hour_JSON = "bidhour";
    String Bid_Type_JSON = "cartype";
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

    String Image_Make_JSON = "carmake";
    String Image_Model_JSON = "carmodel";
    String Image_Year_JSON = "caryear";
    String Image_Warn_JSON = "carwaranty";
    String Image_Millage_JSON = "carmillage";
    String Image_Cartypr_JSON = "cartype";
    String Image_Engine_JSON = "carengine";

    String Image_Descr_JSON = "descr";
    List<String> IdList = new ArrayList<>();
    String Image_URL_JSON = "image_path1";
    String Image_URL_JSON1 = "image_path2";
    String Image_URL_JSON2 = "image_path3";
    String ParseResult ;
    String TempItem;

    private SQLiteHandlerUsers db1;
    private SessionManager session;
    long elapsed;
   String newprice;
   int counter=0;
   int check;
    String auctione,stause,email,userpayment,userpayment2,newuserpayment,userid,newpayment,oldprice,usermobile,bidhourold;
    SimpleDateFormat formatter3 = new SimpleDateFormat("HH:mm");
    SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");
    double userpaymentnumber, pricenumber,pricenumber2,newpricenumber,oldpricenumber;

    String IdHolder,NameHolder,pathholder,Priceholder,AuctionHolder,BidDateHolder,BidWinHolder1,BidWinHolder2,BidWinHolder3,BidHourHolder,ItemypeHolder,

    BidWinHolder4,BidWinHolder,Priceholder1,Priceholder2,Priceholder3,Priceholder4,BidNumberHolder,BidStatusHolder;
    JsonArrayRequest RequestOfJSonArray ;
    HttpParse httpParse = new HttpParse();
    String FinalJSonObject ;
    HashMap<String,String> ResultHash = new HashMap<>();
    RequestQueue requestQueue ;

    View view ;

    int RecyclerViewItemPosition ;

    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    RecyclerView.Adapter recyclerViewadapter;

    ArrayList<String> ImageTitleNameArrayListForClick,PriceArray,Price1Array,Price2Array,Price3Array,Price4Array,WinArray,
            Win1Array,Win2Array,Win3Array,Win4Array,biddateArray,bidtimeArray,bidoneArray,carmodelarray,carmakearray,carwarantyarray,
            caryeararray,cardescriptionarray,cartypearray,carmillagearray,carenginearray;

    String yearu,modelu,makeu,warnu,bdateu,btimeu,priceu,engineu,codeu,descu,statusu,millageu,typeu,image1,image2,image3;
    ArrayList<String> Image1;
    ArrayList<String> Image2;
    ArrayList<String> Image3;

    private HomeFragment.OnFragmentInteractionListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        ImageTitleNameArrayListForClick = new ArrayList<>();
        PriceArray=new ArrayList<>();
        Price1Array = new ArrayList<>();
        Price2Array = new ArrayList<>();
        Price3Array = new ArrayList<>();
        Price4Array = new ArrayList<>();
        WinArray = new ArrayList<>();
        Win1Array = new ArrayList<>();
        Win2Array = new ArrayList<>();
        Win3Array = new ArrayList<>();
        Win4Array = new ArrayList<>();
        biddateArray = new ArrayList<>();
        bidtimeArray = new ArrayList<>();
        bidoneArray = new ArrayList<>();
        Image1 = new ArrayList<>();
        Image2 = new ArrayList<>();
        Image3 = new ArrayList<>();
        ListOfdataAdapter = new ArrayList<>();
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);

                                        JSON_HTTP_CALL();
                                        counter=0;

                                    }
                                }
        );
        db1 = new SQLiteHandlerUsers(getApplicationContext());
        HashMap<String, String> usermazad = db1.getUserDetails();

        // userpayment = usermazad.get("downpayment");
        email = usermazad.get("email");
        usermobile=usermazad.get("mobile");
        if (email==null)
        {  postAlert();}

        JSON_HTTP_CALL_User();

        recyclerView.setHasFixedSize(true);

        layoutManagerOfrecyclerView = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);

        // JSON_HTTP_CALL();
        // Implementing Click Listener on RecyclerView.
      /*  ViewGroup viewGroup = ((ViewGroup)recyclerView.get
        String newprice = ((EditText)viewGroup.getChildAt(0)).getText().toString();*/
      /*  EditText newpriceEditText = (EditText) view.findViewById(R.id.newpriceeditable);
        newprice = newpriceEditText.getText().toString();*/


    }

    public  void  imagedetail (View view){
       // startActivity(new Intent(getApplicationContext(), MainActivityMaster.class));
        RecyclerViewItemPosition = (int) view.getTag();
        View vview = recyclerView.findContainingItemView( view);
        TextView carmake = (TextView) vview.findViewById(R.id.ImagemakeTextView);
        TextView carmodel = (TextView) vview.findViewById(R.id.ImagemodelTextView);
        TextView carwaranty = (TextView) vview.findViewById(R.id.ImagewarnTextView);
        TextView carmillage = (TextView) vview.findViewById(R.id.ImagemillageTextView);
        TextView caryear = (TextView) vview.findViewById(R.id.ImageyearTextView);
        TextView cartype = (TextView) vview.findViewById(R.id.ImagetypeTextView);
        TextView biddate = (TextView) vview.findViewById(R.id.ImagebidateTextView);
        TextView bidtime = (TextView) vview.findViewById(R.id.ImagebidhourTextView);
        TextView itemcode = (TextView) vview.findViewById(R.id.ImageIDTextView);
        TextView price = (TextView) vview.findViewById(R.id.ImagepriceTextView);
        TextView carengine = (TextView) vview.findViewById(R.id.ImageengineTextView);
        TextView descr = (TextView) vview.findViewById(R.id.ImagedescrTextView);
        TextView status = (TextView) vview.findViewById(R.id.ImagestatusTextView);


        Intent i = new Intent(getApplication(), Bidone.class);
        makeu=carmake.getText().toString();
        modelu=carmodel.getText().toString();
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
        i.putExtra("image1", image1);
        i.putExtra("image2", image2);
        i.putExtra("image3", image3);


        startActivity(i);


    }

public  void onPlus(View view){

    RecyclerViewItemPosition = (int) view.getTag();
    View vview = recyclerView.findContainingItemView( view);
    //  RecyclerView.ViewHolder vview = recyclerView.getChildAdapterPosition(view);
    EditText nameEditText = (EditText) vview.findViewById(R.id.newpriceeditable);
    if(check==RecyclerViewItemPosition)
    {
        counter=counter+200;
    nameEditText.setText(String.valueOf(Double.parseDouble(ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition))+counter));
    newprice = nameEditText.getText().toString();
    }
    else
    {
        check=0;
        counter=0;
        check=check+RecyclerViewItemPosition;
    counter=counter+200;
    nameEditText.setText(String.valueOf(Double.parseDouble(ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition))+counter));
    newprice = nameEditText.getText().toString();

    }
}

    public void onClickBotonBorrar(View view) {
        counter=0;

        RecyclerViewItemPosition = (int) view.getTag();
        View vview = recyclerView.findContainingItemView( view);
        //  RecyclerView.ViewHolder vview = recyclerView.getChildAdapterPosition(view);

        EditText nameEditText = (EditText) vview.findViewById(R.id.newpriceeditable);
        newprice = nameEditText.getText().toString();
        //   String newprice = ((EditText)viewGroup.getChildAt(RecyclerViewItemPosition)).getText().toString();
     //   Toast.makeText(getApplicationContext(),  newprice, Toast.LENGTH_LONG).show();

        TempItem = IdList.get(RecyclerViewItemPosition).toString();
        IdHolder=IdList.get(RecyclerViewItemPosition).toString();
        Priceholder = PriceArray.get(RecyclerViewItemPosition);
        Priceholder1 = Price1Array.get(RecyclerViewItemPosition);
        Priceholder2 = Price2Array.get(RecyclerViewItemPosition);
        Priceholder3 = Price3Array.get(RecyclerViewItemPosition);
        Priceholder4 = Price4Array.get(RecyclerViewItemPosition);
        BidDateHolder = biddateArray.get(RecyclerViewItemPosition);
        BidHourHolder = bidtimeArray.get(RecyclerViewItemPosition);
        //   ItemypeHolder = jsonObject.getString("type").toString() ;
        BidNumberHolder = bidoneArray.get(RecyclerViewItemPosition).toString();
        BidWinHolder = WinArray.get(RecyclerViewItemPosition).toString();
        BidWinHolder1 = Win1Array.get(RecyclerViewItemPosition).toString();
        BidWinHolder2 = Win2Array.get(RecyclerViewItemPosition).toString();
        BidWinHolder3 = Win3Array.get(RecyclerViewItemPosition).toString();
        BidWinHolder4 = Win4Array.get(RecyclerViewItemPosition).toString();

        //     HttpWebCallFast(TempItem);
        //   Toast.makeText(getApplicationContext(),BidDateHolder.toString(), Toast.LENGTH_LONG).show();
        bidhourold=BidHourHolder;
        biddateold=BidDateHolder;
        final String dateString =  biddateold;
        final String timeString = bidhourold;
        final  String datadb =dateString+" "+timeString;

      //  Toast.makeText(getApplicationContext(),   BidDateHolder.toString(), Toast.LENGTH_LONG).show();

       /* AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        View mview=getLayoutInflater().inflate(R.layout.bid_dialoug,null);
        TextView oldprice=(TextView)mview.findViewById(R.id.oldpricetext);
        final EditText newpricee=(EditText) mview.findViewById(R.id.newpricetext);
        newpricee.setHint("Add New Price");
        oldprice.setText("Old Price: "+ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition));
        Button updatenewprice=(Button) mview.findViewById(R.id.updatebid);*/

        try {// Getting data from EditText after button click.

            pricenumber = Double.parseDouble(userpayment2);//user downpayment
         //   Toast.makeText(this, String.valueOf(newprice), Toast.LENGTH_LONG).show();
            if (newprice.matches("")||Double.parseDouble(newprice)<= (Double.parseDouble(ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition)))||(Double.parseDouble(newprice) > pricenumber ))
                Toast.makeText(getApplicationContext(), "Wrong Entries", Toast.LENGTH_LONG).show();
            else
            {
                GetDataFromEditText();
                Calendar calendar1 = Calendar.getInstance();
                Calendar calendar2 = Calendar.getInstance();
                Calendar olddate = Calendar.getInstance();
                String currentDate = formatter1.format(calendar1.getTime());
                Date d2=formatter1.parse(datadb);
                Date d1 = formatter1.parse(currentDate);
                Date d3=formatter3.parse(timeString);
                Date d4=formatter3.parse("23:50");
                Date oldate=formatter2.parse(dateString);
                calendar2.setTime(d3);
                olddate.setTime(oldate);
                elapsed = d2.getTime() - d1.getTime();
          //       Toast.makeText(this,String.valueOf(elapsed/(60*1000)), Toast.LENGTH_LONG).show();
              //  Toast.makeText(this,Priceholder1, Toast.LENGTH_LONG).show();

                if((elapsed/(60*1000))<10 && d3.after(d4) &&(elapsed/(60*1000))>0 )
                {
                    calendar2.add(Calendar.MINUTE, 10);
                    d3=calendar2.getTime();
                    olddate.add(Calendar.DATE,1);
                    oldate=olddate.getTime();
                    bidhouenew=formatter3.format(d3.getTime());
                    biddatenew=formatter2.format(oldate.getTime());

                    StudentRecordUpdate(IdHolder, Priceholder, Priceholder1, Priceholder2, Priceholder3,
                            Priceholder4, BidWinHolder, BidWinHolder1, BidWinHolder2,
                            BidWinHolder3, BidWinHolder4, BidNumberHolder,bidhouenew,biddatenew);
                    Toast.makeText(getApplicationContext(),"Recoed Updated", Toast.LENGTH_LONG).show();
                    //    Toast.makeText(getContext(),biddatenew+"  "+bidhouenew, Toast.LENGTH_LONG).show();
                }
                else if ((elapsed/(60*1000))<10 && d3.before(d4)&&(elapsed/(60*1000))>0)
                {
                    calendar2.add(Calendar.MINUTE, 10);
                    d3=calendar2.getTime();
                    //   olddate.add(Calendar.DATE,1);
                    //   oldate=olddate.getTime();
                    bidhouenew=formatter3.format(d3.getTime());
                    biddatenew=formatter2.format(oldate.getTime());

                    StudentRecordUpdate(IdHolder, Priceholder, Priceholder1, Priceholder2, Priceholder3,
                            Priceholder4, BidWinHolder, BidWinHolder1, BidWinHolder2,
                            BidWinHolder3, BidWinHolder4, BidNumberHolder,bidhouenew,biddatenew);
                    Toast.makeText(getApplicationContext(),"Recoed Updated", Toast.LENGTH_LONG).show();
                    //     Toast.makeText(getContext(),biddatenew+"  "+bidhouenew, Toast.LENGTH_LONG).show();

                }
                else if((elapsed/(60*1000))<0)
                {
                    Toast.makeText(this, "Sorry Bid is Closed on That Item ", Toast.LENGTH_LONG).show();
                  /*  AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("ALERT");
                    builder.setMessage("Sorry Bid is Closed on That Item");
                    builder.setCancelable(true);
                    dialog2 = builder.create();
                    dialog2.requestWindowFeature(Window.FEATURE_LEFT_ICON);
                    AlertDialog dialog2 = builder.show();
                    TextView textView = ((TextView) dialog2.findViewById(android.R.id.message));
                    // textView.setTextColor(Color.LTGRAY);
                    textView.setTextSize(16);
                    textView.setGravity(Gravity.CENTER);*/

                }
                else
                {
                  //  Toast.makeText(getApplicationContext(),BidNumberHolder, Toast.LENGTH_LONG).show();
                    bidhouenew=formatter3.format(d3.getTime());
                    biddatenew=formatter2.format(oldate.getTime());
                   // Toast.makeText(getApplicationContext(),BidWinHolder1+"  "+Priceholder1+" "+Priceholder, Toast.LENGTH_LONG).show();
                    StudentRecordUpdate(IdHolder, Priceholder, Priceholder1, Priceholder2, Priceholder3,
                            Priceholder4, BidWinHolder, BidWinHolder1, BidWinHolder2,
                            BidWinHolder3, BidWinHolder4, BidNumberHolder,bidhouenew,biddatenew);
                    Toast.makeText(getApplicationContext(),"Recoed Updated", Toast.LENGTH_LONG).show();

                }
            }
        }
        catch(Exception e){
            Log.e(TAG, "Error", e);
            Toast toast= Toast.makeText(getApplicationContext(), "Wrong Price Entry or DownPayment not Paid", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();

            return;
        }
      startActivity(new Intent(getApplicationContext(), MainActivityMaster.class));
        finish();
      //  JSON_HTTP_CALL();
    }

    private void postAlert() {
        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.auction);

        NotificationCompat.Builder mBuilder2 = new NotificationCompat.Builder(
                this).setAutoCancel(true)
                .setContentTitle("Hurry UP")
                .setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(icon)
                .setContentText("Sign up Now...!");

        NotificationCompat.BigPictureStyle bigPicStyle = new NotificationCompat.BigPictureStyle();
        bigPicStyle.bigPicture(icon);
        bigPicStyle.setBigContentTitle("Sign Up Now....");
        mBuilder2.setStyle(bigPicStyle);

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent1 = new Intent(this, LoginActivity.class);

        // The stack builder object will contain an artificial back stack for
        // the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder1 = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder1.addParentStack(LoginActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder1.addNextIntent(resultIntent1);
        PendingIntent resultPendingIntent1 = stackBuilder1.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder2.setContentIntent(resultPendingIntent1);

        NotificationManager mNotificationManager2 = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // mId allows you to update the notification later on.
        mNotificationManager2.notify(100, mBuilder2.build());

    }

    public void GetDataFromEditText(){
        if (Integer.parseInt(BidNumberHolder)==1)
        {
            Priceholder1 = newprice;
            //  userpaymentnumber = Double.parseDouble( Priceholder1);
            BidWinHolder1=email;
            BidNumberHolder=String.valueOf(2);

        }
        else if (Integer.parseInt(BidNumberHolder)==2)
        {  Priceholder2 = newprice;
            //     userpaymentnumber = Double.parseDouble( Priceholder2);
            BidWinHolder2=email;
            BidNumberHolder=String.valueOf(3);
        }
        else if (Integer.parseInt(BidNumberHolder)==3)
        {  Priceholder3 = newprice;
            //    userpaymentnumber = Double.parseDouble( Priceholder3);
            BidWinHolder3=email;
            BidNumberHolder=String.valueOf(4);
        }

        else if (Integer.parseInt(BidNumberHolder)==4)
        {  Priceholder4 = newprice;
            //     userpaymentnumber = Double.parseDouble( Priceholder4);
            BidWinHolder4=email;
            BidNumberHolder=String.valueOf(5);
        }
        else if (Integer.parseInt(BidNumberHolder)==5)
        {  Priceholder = newprice;
            //    userpaymentnumber = Double.parseDouble( priceholder);
            BidWinHolder=email;
            BidNumberHolder=String.valueOf(1);
        }


    }

    public void JSON_HTTP_CALL(){
        swipeRefreshLayout.setRefreshing(true);
        RequestOfJSonArray = new JsonArrayRequest(AppConfig.HTTP_JSON_URL_listcar,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response == null )
                            return;
                        ListOfdataAdapter.clear();//to resset adapter each time
                        ParseJSonResponse(response);
                        swipeRefreshLayout.setRefreshing(false);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        requestQueue.add(RequestOfJSonArray);

    }
    public void ParseJSonResponse(JSONArray array){
        //  swipeRefreshLayout.setRefreshing(false);
        ImageTitleNameArrayListForClick = new ArrayList<>();
        for(int i = 0; i<array.length(); i++) {

            DataAdapter2 GetDataAdapter2 = new DataAdapter2();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);
                //   if (json.getString((Image_Auction_JSON)).equalsIgnoreCase("YES"))

                GetDataAdapter2.setImageTitle(json.getString(Image_Name_JSON));
             /*  GetDataAdapter2.setImagePrice("Price:"+json.getString(Image_Price_JSON));
                GetDataAdapter2.setImagePrice1("Price1:"+json.getString(Image_Price_JSON1));
                GetDataAdapter2.setImagePrice2("Price2:"+json.getString(Image_Price_JSON2));
                GetDataAdapter2.setImagePrice3("Price3:"+json.getString(Image_Price_JSON3));
                GetDataAdapter2.setImagePrice4("Price4:"+json.getString(Image_Price_JSON4));*/
                GetDataAdapter2.setBidDate(json.getString(Bid_Date_JSON));
                GetDataAdapter2.setItemtype("Gear:"+json.getString(Bid_Type_JSON));
                GetDataAdapter2.setNoBid("Bid:"+json.getString(Image_NoBid_JSON));
                GetDataAdapter2.setItembidtime(json.getString(Bid_Hour_JSON));
                GetDataAdapter2.setBidDate(json.getString(Bid_Date_JSON));
                GetDataAdapter2.setNoBid("NO.of Bids:"+json.getString(Image_NoBid_JSON));
                final  String datadb =json.getString(Bid_Date_JSON)+" "+json.getString(Bid_Hour_JSON);
                try {
                    Date d2=formatter1.parse(datadb);
                    Calendar calendar1 = Calendar.getInstance();
                    String currentDate = formatter1.format(calendar1.getTime());
                    Date d1 = formatter1.parse(currentDate);
                    elapsed = d2.getTime() - d1.getTime();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
              //  GetDataAdapter2.setItemStatus("Status:"+json.getString(Bid_Status_JSON));
                if ((elapsed/60*1000)<0){
                    GetDataAdapter2.setItemStatus("Bid Closed");

                }
                else
                {
                long secondsInMilli = 1000;
                long minutesInMilli = secondsInMilli * 60;
                long hoursInMilli = minutesInMilli * 60;
                long daysInMilli = hoursInMilli * 24;
                long elapsedDays = elapsed / daysInMilli;
                elapsed = elapsed % daysInMilli;
                long elapsedHours = elapsed / hoursInMilli;
                elapsed = elapsed % hoursInMilli;
                long elapsedMinutes = elapsed / minutesInMilli;
                elapsed = elapsed % minutesInMilli;
                long elapsedSeconds = elapsed / secondsInMilli;
                //     GetDataAdapter2.setItemStatus("Status:"+formatter1.format(String.valueOf(elapsed/(60*1000))));
                GetDataAdapter2.setItemStatus(elapsedDays+"d"+":"+elapsedHours+"h"+":"+elapsedMinutes+"m"+":"+elapsedSeconds+"s");}
                GetDataAdapter2.setItemmark("Make:"+json.getString(Image_Make_JSON));
                GetDataAdapter2.setItemmodel("Model:"+json.getString(Image_Model_JSON));
                GetDataAdapter2.setItemmillage("Millage:"+json.getString(Image_Millage_JSON));
                GetDataAdapter2.setItemyear("Year:"+json.getString(Image_Year_JSON));
                GetDataAdapter2.setItemwarn("Waranty:"+json.getString(Image_Warn_JSON));

                GetDataAdapter2.setItemengine("Engine:"+json.getString(Image_Engine_JSON));
              /* GetDataAdapter2.setBidWin("Win1:"+json.getString(Bid_Winner_JSON));
                GetDataAdapter2.setBidWin1("Win2:"+json.getString(Bid_Winner_JSON1));
                GetDataAdapter2.setBidWin2("Win3:"+json.getString(Bid_Winner_JSON2));
                GetDataAdapter2.setBidWin3("Win4:"+json.getString(Bid_Winner_JSON3));
                GetDataAdapter2.setBidWin4("Win5:"+json.getString(Bid_Winner_JSON4));*/
                GetDataAdapter2.setItembidtime(json.getString(Bid_Hour_JSON));
                GetDataAdapter2.setItemAuction("Auction:"+json.getString(Image_Auction_JSON));
                GetDataAdapter2.setImageID(json.getString(Image_ID_JSON));
                GetDataAdapter2.setItemDescr("Description:"+json.getString(Image_Descr_JSON));
                GetDataAdapter2.setNoBid("NO.of Bids:"+json.getString(Image_NoBid_JSON));
                if (Integer.parseInt(json.getString(Image_NoBid_JSON))==1)
                {
                    GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON));
                    GetDataAdapter2.setBidWin("Win1:"+json.getString(Bid_Winner_JSON));

                    ImageTitleNameArrayListForClick.add(json.getString(Image_Price_JSON));
                    PriceArray.add(json.getString(Image_Price_JSON));
                    Price1Array.add(json.getString(Image_Price_JSON1));
                    Win1Array.add(json.getString(Bid_Winner_JSON1));
                    Price2Array.add(json.getString(Image_Price_JSON2));
                    Win2Array.add(json.getString(Bid_Winner_JSON2));
                    Price3Array.add(json.getString(Image_Price_JSON3));
                    Win3Array.add(json.getString(Bid_Winner_JSON3));
                    Price4Array.add(json.getString(Image_Price_JSON4));
                    Win4Array.add(json.getString(Bid_Winner_JSON4));
                    WinArray.add(json.getString(Bid_Winner_JSON));
                    bidoneArray.add(json.getString(Image_NoBid_JSON));
                    biddateArray.add(json.getString(Bid_Date_JSON));
                    bidtimeArray.add(json.getString(Bid_Hour_JSON));
                }
                else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==2)
                {
                    GetDataAdapter2.setImagePrice( json.getString(Image_Price_JSON1));
                    GetDataAdapter2.setBidWin("Win2:"+json.getString(Bid_Winner_JSON1));
                    ImageTitleNameArrayListForClick.add(json.getString(Image_Price_JSON1));
                    PriceArray.add(json.getString(Image_Price_JSON));
                    Price1Array.add(json.getString(Image_Price_JSON1));
                    Win1Array.add(json.getString(Bid_Winner_JSON1));
                    Price2Array.add(json.getString(Image_Price_JSON2));
                    Win2Array.add(json.getString(Bid_Winner_JSON2));
                    Price3Array.add(json.getString(Image_Price_JSON3));
                    Win3Array.add(json.getString(Bid_Winner_JSON3));
                    Price4Array.add(json.getString(Image_Price_JSON4));
                    Win4Array.add(json.getString(Bid_Winner_JSON4));
                    WinArray.add(json.getString(Bid_Winner_JSON));
                    bidoneArray.add(json.getString(Image_NoBid_JSON));
                    biddateArray.add(json.getString(Bid_Date_JSON));
                    bidtimeArray.add(json.getString(Bid_Hour_JSON));
                }
                else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==3)
                {
                    GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON2));
                    GetDataAdapter2.setBidWin("Win3:"+json.getString(Bid_Winner_JSON2));
                    ImageTitleNameArrayListForClick.add(json.getString(Image_Price_JSON2));
                    PriceArray.add(json.getString(Image_Price_JSON));
                    Price1Array.add(json.getString(Image_Price_JSON1));
                    Win1Array.add(json.getString(Bid_Winner_JSON1));
                    Price2Array.add(json.getString(Image_Price_JSON2));
                    Win2Array.add(json.getString(Bid_Winner_JSON2));
                    Price3Array.add(json.getString(Image_Price_JSON3));
                    Win3Array.add(json.getString(Bid_Winner_JSON3));
                    Price4Array.add(json.getString(Image_Price_JSON4));
                    Win4Array.add(json.getString(Bid_Winner_JSON4));
                    WinArray.add(json.getString(Bid_Winner_JSON));
                    bidoneArray.add(json.getString(Image_NoBid_JSON));
                    biddateArray.add(json.getString(Bid_Date_JSON));
                    bidtimeArray.add(json.getString(Bid_Hour_JSON));
                }
                else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==4)
                {
                    GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON3));
                    GetDataAdapter2.setBidWin("Win4:"+json.getString(Bid_Winner_JSON3));
                    ImageTitleNameArrayListForClick.add(json.getString(Image_Price_JSON3));
                    PriceArray.add(json.getString(Image_Price_JSON));
                    Price1Array.add(json.getString(Image_Price_JSON1));
                    Win1Array.add(json.getString(Bid_Winner_JSON1));
                    Price2Array.add(json.getString(Image_Price_JSON2));
                    Win2Array.add(json.getString(Bid_Winner_JSON2));
                    Price3Array.add(json.getString(Image_Price_JSON3));
                    Win3Array.add(json.getString(Bid_Winner_JSON3));
                    Price4Array.add(json.getString(Image_Price_JSON4));
                    Win4Array.add(json.getString(Bid_Winner_JSON4));
                    WinArray.add(json.getString(Bid_Winner_JSON));
                    bidoneArray.add(json.getString(Image_NoBid_JSON));
                    biddateArray.add(json.getString(Bid_Date_JSON));
                    bidtimeArray.add(json.getString(Bid_Hour_JSON));
                }
                else if (Integer.parseInt(json.getString(Image_NoBid_JSON))==5)
                {
                    GetDataAdapter2.setImagePrice(json.getString(Image_Price_JSON4));
                    GetDataAdapter2.setBidWin("Win5:"+json.getString(Bid_Winner_JSON4));
                    ImageTitleNameArrayListForClick.add(json.getString(Image_Price_JSON4));
                    PriceArray.add(json.getString(Image_Price_JSON));
                    Price1Array.add(json.getString(Image_Price_JSON1));
                    Win1Array.add(json.getString(Bid_Winner_JSON1));
                    Price2Array.add(json.getString(Image_Price_JSON2));
                    Win2Array.add(json.getString(Bid_Winner_JSON2));
                    Price3Array.add(json.getString(Image_Price_JSON3));
                    Win3Array.add(json.getString(Bid_Winner_JSON3));
                    Price4Array.add(json.getString(Image_Price_JSON4));
                    Win4Array.add(json.getString(Bid_Winner_JSON4));
                    WinArray.add(json.getString(Bid_Winner_JSON));
                    bidoneArray.add(json.getString(Image_NoBid_JSON));
                    biddateArray.add(json.getString(Bid_Date_JSON));
                    bidtimeArray.add(json.getString(Bid_Hour_JSON));
                }

                IdList.add(json.getString("id").toString());
                // Adding image title name in array to display on RecyclerView click event.
                //   ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                //ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                GetDataAdapter2.setImageUrl(json.getString(Image_URL_JSON));
                GetDataAdapter2.setImageUrl1(json.getString(Image_URL_JSON1));
                GetDataAdapter2.setImageUrl2(json.getString(Image_URL_JSON2));
                Image1.add(json.getString(Image_URL_JSON));
                Image2.add(json.getString(Image_URL_JSON1));
                Image3.add(json.getString(Image_URL_JSON2));
                ListOfdataAdapter.add(GetDataAdapter2);

            } catch (JSONException e) {

                e.printStackTrace();
            }

            // ListOfdataAdapter.add(GetDataAdapter2);
            swipeRefreshLayout.setRefreshing(false);
        }

        recyclerViewadapter = new RecyclerViewAdapteritemsuserBid2(ListOfdataAdapter, getApplicationContext());

        recyclerView.setAdapter(recyclerViewadapter);

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




    @Override
    public void onRefresh() {
        JSON_HTTP_CALL();
        swipeRefreshLayout.setRefreshing(false);
    }

   /* public void HttpWebCallFast(final String PreviousListViewClickedItem){

        class HttpWebCallFunction extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(getApplicationContext(),"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg ;

                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new CarsFragmentBidActivity.GetHttpResponse(getApplicationContext()).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("ImageID",params[0]);

                ParseResult = httpParse.postRequest(ResultHash, AppConfig.HTTP_JSON_URL_item);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }*/



    // Parsing Complete JSON Object.
    private class GetHttpResponse extends AsyncTask<Void, Void, Void>
    {
        public Context context;

        public GetHttpResponse(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            try
            {
                if(FinalJSonObject != null)
                {
                    JSONArray jsonArray = null;

                    try {
                        jsonArray = new JSONArray(FinalJSonObject);
                        JSONObject jsonObject;
                        // DataAdapter2 GetDataAdapter2 = new DataAdapter2();
                        for(int i=0; i<jsonArray.length(); i++)
                        {
                            jsonObject = jsonArray.getJSONObject(i);
                            //     json = array.getJSONObject(i);
                            // if (json.getString((Image_Name_JSON)).equalsIgnoreCase("ahmed"))
                           /* Double maxi1,maxi2,maxi3,maxi4;
                            maxi1=Math.max(Double.parseDouble(jsonObject.getString(Image_Price_JSON)), Double.parseDouble(jsonObject.getString(Image_Price_JSON1)));
                            maxi2=Math.max(Double.parseDouble(jsonObject.getString(Image_Price_JSON2)), Double.parseDouble(jsonObject.getString(Image_Price_JSON1)));
                            maxi3=Math.max(maxi1,maxi2);
                            maxi4=Math.max(Double.parseDouble(jsonObject.getString(Image_Price_JSON2)),maxi3);*/

                          /*  GetDataAdapter2.setImageTitle("Name:"+jsonObject.getString(Image_Name_JSON));

                            GetDataAdapter2.setItemmark("Make:"+jsonObject.getString(Image_Make_JSON));
                            GetDataAdapter2.setItemmodel("Model:"+jsonObject.getString(Image_Model_JSON));
                            GetDataAdapter2.setItemmillage("Millage:"+jsonObject.getString(Image_Millage_JSON));
                            GetDataAdapter2.setItemyear("Year:"+jsonObject.getString(Image_Year_JSON));
                            GetDataAdapter2.setItemwarn("Waranty:"+jsonObject.getString(Image_Warn_JSON));
                            GetDataAdapter2.setItemtype("Type:"+jsonObject.getString(Image_Cartypr_JSON));
                            GetDataAdapter2.setItemengine("Engine:"+jsonObject.getString(Image_Engine_JSON));
                            GetDataAdapter2.setItembidtime("Bid Time:"+jsonObject.getString(Image_Bidtime_JSON));



                            //   GetDataAdapter2.setImagePrice("Price:"+jsonObject.getString(Image_Price_JSON));
                          /*  GetDataAdapter2.setImagePrice1("Price1:"+jsonObject.getString(Image_Price_JSON1));
                            GetDataAdapter2.setImagePrice2("Price2:"+jsonObject.getString(Image_Price_JSON2));
                            GetDataAdapter2.setImagePrice3("Price3:"+jsonObject.getString(Image_Price_JSON3));
                            GetDataAdapter2.setImagePrice4("Price4:"+jsonObject.getString(Image_Price_JSON4));*/
                         /*   GetDataAdapter2.setBidDate("Bid Date:"+jsonObject.getString(Bid_Date_JSON));
                            GetDataAdapter2.setItemStatus("Status:"+jsonObject.getString(Bid_Status_JSON));
                            GetDataAdapter2.setItemAuction("Auction:"+jsonObject.getString(Image_Auction_JSON));
                            GetDataAdapter2.setImageID("Item Code#:"+jsonObject.getString(Image_ID_JSON));
                            GetDataAdapter2.setItemDescr("Description:"+jsonObject.getString(Image_Descr_JSON));
                            GetDataAdapter2.setNoBid("NO.of Bids:"+jsonObject.getString(Image_NoBid_JSON));
                            if (Integer.parseInt(jsonObject.getString(Image_NoBid_JSON))==1)
                            {
                                GetDataAdapter2.setImagePrice("Price:"+jsonObject.getString(Image_Price_JSON));
                                GetDataAdapter2.setBidWin("Win:"+jsonObject.getString(Bid_Winner_JSON));}
                            else if (Integer.parseInt(jsonObject.getString(Image_NoBid_JSON))==2)
                            {   GetDataAdapter2.setImagePrice("Price1:"+jsonObject.getString(Image_Price_JSON1));
                                GetDataAdapter2.setBidWin1("Win1:"+jsonObject.getString(Bid_Winner_JSON1));}
                            else if (Integer.parseInt(jsonObject.getString(Image_NoBid_JSON))==3)
                            {    GetDataAdapter2.setImagePrice("Price2:"+jsonObject.getString(Image_Price_JSON2));
                                GetDataAdapter2.setBidWin2("Win2:"+jsonObject.getString(Bid_Winner_JSON2));}
                            else if (Integer.parseInt(jsonObject.getString(Image_NoBid_JSON))==4)
                            {  GetDataAdapter2.setImagePrice("Price3:"+jsonObject.getString(Image_Price_JSON3));
                                GetDataAdapter2.setBidWin3("Win3:"+jsonObject.getString(Bid_Winner_JSON3));}
                            else if (Integer.parseInt(jsonObject.getString(Image_NoBid_JSON))==5)
                            {     GetDataAdapter2.setImagePrice("Price4:"+jsonObject.getString(Image_Price_JSON4));
                                GetDataAdapter2.setBidWin4("Win4:"+jsonObject.getString(Bid_Winner_JSON4));}
                            GetDataAdapter2.setImageUrl(jsonObject.getString(Image_URL_JSON));
                            GetDataAdapter2.setImageUrl1(jsonObject.getString(Image_URL_JSON1));
                            GetDataAdapter2.setImageUrl2(jsonObject.getString(Image_URL_JSON2));*/

                            // Adding image title name in array to display on RecyclerView click event.
                            ImageTitleNameArrayListForClick.add(jsonObject.getString(Image_Name_JSON));
                            //ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));

                            // Storing Student Name, Phone Number, Class into Variables.
                            IdHolder= jsonObject.getString("id").toString() ;
                            NameHolder = jsonObject.getString("email").toString() ;
                            Priceholder = jsonObject.getString("price").toString() ;
                            Priceholder1 = jsonObject.getString("price1").toString() ;
                            Priceholder2 = jsonObject.getString("price2").toString() ;
                            Priceholder3 = jsonObject.getString("price3").toString() ;
                            Priceholder4 = jsonObject.getString("price4").toString() ;
                            BidDateHolder = jsonObject.getString("biddate").toString() ;
                            BidHourHolder = jsonObject.getString("bidhour").toString() ;
                            ItemypeHolder = jsonObject.getString("type").toString() ;
                            BidNumberHolder = jsonObject.getString("nobid").toString() ;
                            BidWinHolder = jsonObject.getString("bidwin").toString() ;
                            BidWinHolder1 = jsonObject.getString("bidwin1").toString() ;
                            BidWinHolder2 = jsonObject.getString("bidwin2").toString() ;
                            BidWinHolder3 = jsonObject.getString("bidwin3").toString() ;
                            BidWinHolder4 = jsonObject.getString("bidwin4").toString() ;
                            AuctionHolder = jsonObject.getString("auction").toString() ;
                            BidStatusHolder = jsonObject.getString("status").toString() ;
                            pathholder = jsonObject.getString("image_path1").toString() ;
                            //   ListOfdataAdapter.add(GetDataAdapter2);
                        }



                    }
                    catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
                //    recyclerViewadapter = new RecyclerViewAdapter2itemusers(ListOfdataAdapter, context);
                //   recyclerView.setAdapter(recyclerViewadapter);

            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

      /*  @Override
        protected void onPostExecute(Void result)
        {

            // Setting Student Name, Phone Number, Class into TextView after done all process .
            //  NAME.setText(NameHolder);

            recyclerView.setAdapter(recyclerViewadapter);
        }*/
    }
    public void JSON_HTTP_CALL_User(){

        RequestOfJSonArray = new JsonArrayRequest(AppConfig.HTTP_JSON_URL_Users,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response2) {

                        ParseJSonResponse2(response2);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(getApplicationContext());

        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse2(JSONArray array){

        for(int i = 0; i<array.length(); i++) {


            JSONObject json = null;
            try {

                json = array.getJSONObject(i);
                if (json.getString((Image_Email_JSON)).equals(email))
                {
                    userpayment=(json.getString(User_Downpay_JSON));
                    userpayment2=(json.getString(User_Downpay_JSON2));
                    userid=(json.getString(Image_ID_JSON));
                }
                //  IdList.add(json.getString("id").toString());
                // Adding image title name in array to display on RecyclerView click event.
                //    ImageTitleNameArrayListForClick.add(json.getString(User_Name_JSON));
                //ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                // ListOfdataAdapter.add(GetDataAdapter2);
            } catch (JSONException e) {

                e.printStackTrace();
            }

            //  ListOfdataAdapter.add(GetDataAdapter2);
        }

    }
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public void StudentRecordUpdate( String ID,  String S_price, String S_price1, String S_price2, String S_price3, String S_price4
            ,String S_mobile, String S_mobile1, String S_mobile2, String S_mobile3, String S_mobile4,
                                    String S_nobid, String S_bidhour,  String S_biddate){

        class StudentRecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

         //    progressDialog = ProgressDialog.show(CarsFragmentBidActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);
//                progressDialog.dismiss();
             //   Log.d("Result",httpResponseMsg.toString());
             //   Toast.makeText(CarsFragmentBidActivity.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("id",params[0]);
                hashMap.put("price",params[1]);
                hashMap.put("price1",params[2]);
                hashMap.put("price2",params[3]);
                hashMap.put("price3",params[4]);
                hashMap.put("price4",params[5]);
                hashMap.put("bidwin",params[6]);
                hashMap.put("bidwin1",params[7]);
                hashMap.put("bidwin2",params[8]);
                hashMap.put("bidwin3",params[9]);
                hashMap.put("bidwin4",params[10]);
                hashMap.put("nobid",params[11]);
                hashMap.put("bidhour",params[12]);
                hashMap.put("biddate",params[13]);
                //   hashMap.put("auction",params[2]);
                //  hashMap.put("status",params[3]);
                //    hashMap.put("biddate",params[4]);
                //   hashMap.put("bidwin",params[5]);
                //    hashMap.put("image_path",params[2]);

                finalResult = httpParse.postRequest(hashMap, AppConfig.HttpURLitem);

                return finalResult;
            }
        }

        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass();

        studentRecordUpdateClass.execute(ID,S_price,S_price1,S_price2,S_price3,S_price4,S_mobile,S_mobile1,S_mobile2,S_mobile3,S_mobile4,S_nobid,S_bidhour,S_biddate);
    }

}
