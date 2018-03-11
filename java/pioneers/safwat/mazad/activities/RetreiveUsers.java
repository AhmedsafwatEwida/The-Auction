package pioneers.safwat.mazad.activities;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pioneers.safwat.mazad.R;


/**
 * Created by safwa on 12/7/2017.
 */

public class RetreiveUsers extends AppCompatActivity {

    List<DataAdapter_users> ListOfdataAdapter;
    List<DataAdapter_users> FilteredListOfdataAdapter;
//private RecyclerViewAdapter_users   recyclerserach;
    RecyclerView recyclerView,recycler2;

   String HTTP_JSON_URL = "http://192.168.100.8/mazad/UsersJsonData.php";
   // String HTTP_JSON_URL = "http://192.168.43.215/mazad/UsersJsonData.php";
  //  String HTTP_JSON_URL = "http://192.168.43.215/android_login_upload_images/ImageJsonData2.php";
   String iduser,emailuser,mobileuser,paymentcaruser,paymentpropuser,nameuser;
    String IdHolder, NameHolder, pathholder,emailholder,mobileholder,paymentholder,paymentholdercar;
    ProgressDialog pDialog;
    HttpParse httpParse = new HttpParse();
    String User_Downpay_JSON = "downpayment";
    String User_Downpay_JSON2 = "downpaymentcar";
    String User_Name_JSON = "name";
    String Image_Mobile_JSON = "mobile";
    String Image_Email_JSON = "email";
    String Image_ID_JSON = "id";
    List<String> IdList = new ArrayList<>();
    List<String> downpaympropList = new ArrayList<>();
    List<String> downpaymcarList = new ArrayList<>();
    List<String> mobileList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();
    private SearchView searchView;
    JsonArrayRequest RequestOfJSonArray ;
   // private RecyclerViewAdapter_users dataAdaptersfiltered;
    RequestQueue requestQueue ;
    String FinalJSonObject ;
    String ParseResult ;
    HashMap<String,String> ResultHash = new HashMap<>();

    View view ;

    int RecyclerViewItemPosition ;

    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

   // RecyclerView.Adapter recyclerViewadapter,recyclerserach ;
    RecyclerViewAdapter_users recyclerViewadapter;


    ArrayList<String> ImageTitleNameArrayListForClick;
    private EditText searchBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.retreive_datausers);
        searchBox = (EditText)findViewById(R.id.search_box);
        ImageTitleNameArrayListForClick = new ArrayList<>();

        FilteredListOfdataAdapter=new ArrayList<DataAdapter_users>();

        ListOfdataAdapter = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerView.setHasFixedSize(true);

        layoutManagerOfrecyclerView = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);
      /*  recyclerserach=new RecyclerViewAdapter_users(FilteredListOfdataAdapter,this);

        recyclerView.setAdapter(recyclerserach);*/
        JSON_HTTP_CALL();

        //assert recyclerView != null;
   //     recyclerserach=new RecyclerViewAdapter_users(FilteredListOfdataAdapter,getApplicationContext());


        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recyclerViewadapter.getFilter().filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        // Implementing Click Listener on RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(RetreiveUsers.this, new GestureDetector.SimpleOnGestureListener() {

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
                   TextView emailEditText = (TextView) vview.findViewById(R.id.ImageemailTextView);
                    TextView mobileEditText = (TextView) vview.findViewById(R.id.ImageMobileTextView);
                    TextView downpaymentEditText = (TextView) vview.findViewById(R.id.ImagepaymenyTextView);
                    TextView downpaymentcarEditText = (TextView) vview.findViewById(R.id.Imagepaymeny2TextView);
                    TextView nameEditText = (TextView) vview.findViewById(R.id.ImageNameTextView);
                    TextView idtextview = (TextView) vview.findViewById(R.id.ImageIDTextView);



                    emailuser=emailEditText.getText().toString();
                    iduser=idtextview.getText().toString();
                   // nameuser=nameEditText.getText().toString();
                    mobileuser=mobileEditText.getText().toString();
                   paymentcaruser=downpaymentcarEditText.getText().toString();
                    paymentpropuser=downpaymentEditText.getText().toString();
                    //Getting RecyclerView Clicked Item value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(vview);
                    Intent i = new Intent(getApplicationContext(),
                            UserUpdatepayment.class);
                  //  HttpWebCall(emailuser);

                  // i.putExtra("ListViewValue", IdList.get(RecyclerViewItemPosition).toString());
                    i.putExtra("ListViewValue", emailuser);
                    i.putExtra("nameViewValue", nameuser);
                    i.putExtra("mobileViewValue", mobileuser);
                    i.putExtra("paymentcarValue", paymentcaruser);
                    i.putExtra("paymentpropValue", paymentpropuser);
                    i.putExtra("idValue", iduser);


                   /* i.putExtra("ListViewValue", emailuser);
                    i.putExtra("nameViewValue", NameHolder);
                    i.putExtra("mobileViewValue", mobileholder);
                    i.putExtra("paymentcarValue", paymentholdercar);
                    i.putExtra("paymentpropValue", paymentholder);*/

                    startActivity(i);
                    // Showing RecyclerView Clicked Item value using Toast.
                  //  Toast.makeText(RetreiveUsers.this, ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
                 //   Toast.makeText(RetreiveUsers.this,  emailuser, Toast.LENGTH_LONG).show();

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

        RequestOfJSonArray = new JsonArrayRequest(AppConfig.HTTP_JSON_URL_Users,

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

        requestQueue = Volley.newRequestQueue(RetreiveUsers.this);

        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            DataAdapter_users GetDataAdapter2 = new DataAdapter_users();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);
               // if (json.getString((Image_Name_JSON)).equalsIgnoreCase("ahmed"))
                  GetDataAdapter2.setUsername("Name:"+json.getString(User_Name_JSON));
                GetDataAdapter2.setUserDownpay(json.getString(User_Downpay_JSON));
                GetDataAdapter2.setUserDownpay2(json.getString(User_Downpay_JSON2));
                GetDataAdapter2.setUserEmail(json.getString(Image_Email_JSON));
                GetDataAdapter2.setUserMobile(json.getString(Image_Mobile_JSON));
                GetDataAdapter2.setUserID(json.getString(Image_ID_JSON));
                IdList.add(json.getString("email").toString());
                nameList.add(json.getString("name").toString());
                mobileList.add(json.getString("mobile").toString());
                downpaymcarList.add(json.getString("downpaymentcar").toString());
               downpaympropList.add(json.getString("downpayment").toString());
             /*   GetDataAdapter2.setUsername(json.getString(User_Name_JSON));
                GetDataAdapter2.setUserDownpay(json.getString(User_Downpay_JSON));
                GetDataAdapter2.setUserDownpay2(json.getString(User_Downpay_JSON2));
                GetDataAdapter2.setUserEmail(json.getString(Image_Email_JSON));
                GetDataAdapter2.setUserMobile(json.getString(Image_Mobile_JSON));
                GetDataAdapter2.setUserID(json.getString(Image_ID_JSON));
                IdList.add(json.getString("id").toString());*/
                // Adding image title name in array to display on RecyclerView click event.
                ImageTitleNameArrayListForClick.add(json.getString(User_Name_JSON));
                //ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
               // ListOfdataAdapter.add(GetDataAdapter2);
            } catch (JSONException e) {

                e.printStackTrace();
            }

            ListOfdataAdapter.add(GetDataAdapter2);
       //     FilteredListOfdataAdapter.addAll(ListOfdataAdapter);
        }

        recyclerViewadapter = new RecyclerViewAdapter_users(ListOfdataAdapter, this);

        recyclerView.setAdapter(recyclerViewadapter);


    }


    public void HttpWebCall(final String PreviousListViewClickedItem){

        class HttpWebCallFunction extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(RetreiveUsers.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg ;

                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new GetHttpResponse(RetreiveUsers.this).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("email",params[0]);

                ParseResult = httpParse.postRequest(ResultHash, AppConfig.HTTP_JSON_URL_oneuser);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }

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

                        for(int i=0; i<jsonArray.length(); i++)
                        {
                            jsonObject = jsonArray.getJSONObject(i);
                            IdHolder = jsonObject.getString("id").toString() ;
                            NameHolder = jsonObject.getString("name").toString() ;
                            emailholder = jsonObject.getString("email").toString() ;
                            mobileholder = jsonObject.getString("mobile").toString() ;
                            paymentholder = jsonObject.getString("downpayment").toString() ;
                            paymentholdercar = jsonObject.getString("downpaymentcar").toString() ;

                        }
                    }
                    catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {

         /*   email.setText(emailholder);
            mobile.setText(mobileholder);
            payment.setText(paymentholder);
            paymentcar.setText(paymentholdercar);
            useremai.setText("User Email: "+emailholder);
            usermobile.setText("User Mobile: "+mobileholder);*/



        }
    }



}
