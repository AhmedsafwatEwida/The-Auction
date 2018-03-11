package pioneers.safwat.mazad.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;

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

public class Retreiveoneuser extends AppCompatActivity {

    List<DataAdapter_users> ListOfdataAdapter;
    private FloatingActionButton update;

    RecyclerView recyclerView;
    ProgressDialog pDialog;
   //String HTTP_JSON_URL = "http://192.168.100.8/mazad/oneuser_retreive.php";
  // String HTTP_JSON_URL = "http://192.168.43.215/mazad/oneuser_retreive.php";
   //String HTTP_JSON_URL = "http://192.168.43.215/android_login_upload_images/image_retreive2.php";
    HashMap<String,String> ResultHash = new HashMap<>();
    String User_Downpay_JSON = "downpayment";
    String User_Downpay_JSON2 = "downpaymentcar";
    String User_Name_JSON = "name";
    String Image_Mobile_JSON = "mobile";
    String Image_Email_JSON = "email";
    String Image_ID_JSON = "id";
    String ParseResult ;
    String TempItem;
    String NameHolder,emailholder,mobileholder,paymentHolder,paymentHoldercar,Idholder;
    String FinalJSonObject ;
    JsonArrayRequest RequestOfJSonArray ;
    HttpParse httpParse = new HttpParse();
    RequestQueue requestQueue ;

    View view ;

    int RecyclerViewItemPosition ;

    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    RecyclerView.Adapter recyclerViewadapter;
    DataAdapter_users GetDataAdapter2 = new DataAdapter_users();
    ArrayList<String> ImageTitleNameArrayListForClick;
Button UpdateButtonn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.retreive_oneuser);
        TempItem = getIntent().getStringExtra("ListViewValue");
        HttpWebCall(TempItem);
        ImageTitleNameArrayListForClick = new ArrayList<>();
//            UpdateButtonn=(Button)findViewById(R.id.update);
        update = (FloatingActionButton) findViewById(R.id.update);
        ListOfdataAdapter = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerView.setHasFixedSize(true);

        layoutManagerOfrecyclerView = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);


      //  Toast.makeText(Retreiveoneuser.this, mobileholder, Toast.LENGTH_LONG).show();
       update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Retreiveoneuser.this,UserUpdatepayment.class);

                // Sending Student Id, Name, Number and Class to next UpdateActivity.
               /* intent.putExtra("Id", TempItem);
                intent.putExtra("email", emailholder);*/
                intent.putExtra("Id", Idholder);
                intent.putExtra("email", TempItem);
                intent.putExtra("name", NameHolder);
                intent.putExtra("mobile", mobileholder);
                intent.putExtra("downpayment", paymentHolder);
                intent.putExtra("downpaymentcar", paymentHoldercar);
                startActivity(intent);

                // Finishing current activity after opening next activity.
                finish();

            }
        });
      //  JSON_HTTP_CALL();
        // Implementing Click Listener on RecyclerView.

    }

    public void HttpWebCall(final String PreviousListViewClickedItem){

        class HttpWebCallFunction extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = ProgressDialog.show(Retreiveoneuser.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                pDialog.dismiss();

                //Storing Complete JSon Object into String Variable.
                FinalJSonObject = httpResponseMsg ;

                //Parsing the Stored JSOn String to GetHttpResponse Method.
                new GetHttpResponse(Retreiveoneuser.this).execute();

            }

            @Override
            protected String doInBackground(String... params) {

                ResultHash.put("EmailID",params[0]);

                ParseResult = httpParse.postRequest(ResultHash, AppConfig.HTTP_JSON_URL_oneuser);

                return ParseResult;
            }
        }

        HttpWebCallFunction httpWebCallFunction = new HttpWebCallFunction();

        httpWebCallFunction.execute(PreviousListViewClickedItem);
    }


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

                        for(int i=0; i<jsonArray.length(); i++)
                        {
                            jsonObject = jsonArray.getJSONObject(i);
                       //     json = array.getJSONObject(i);
                            // if (json.getString((Image_Name_JSON)).equalsIgnoreCase("ahmed"))

                         /*   GetDataAdapter2.setUsername("Name:"+jsonObject.getString(User_Name_JSON));
                            GetDataAdapter2.setUserDownpay("DownPayment Properties:"+jsonObject.getString(User_Downpay_JSON));
                            GetDataAdapter2.setUserDownpay2("DownPayment Cars:"+jsonObject.getString(User_Downpay_JSON2));
                            GetDataAdapter2.setUserEmail("Email:"+jsonObject.getString(Image_Email_JSON));
                            GetDataAdapter2.setUserMobile("Mobile:"+jsonObject.getString(Image_Mobile_JSON));
                            GetDataAdapter2.setUserID("id:"+jsonObject.getString(Image_ID_JSON));*/

                            // Adding image title name in array to display on RecyclerView click event.
                            ImageTitleNameArrayListForClick.add(jsonObject.getString(User_Name_JSON));
                            //ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                            // Storing Student Name, Phone Number, Class into Variables.
                            Idholder = jsonObject.getString("id").toString() ;
                            NameHolder = jsonObject.getString("name").toString() ;
                            emailholder = jsonObject.getString("email").toString() ;
                            mobileholder = jsonObject.getString("mobile").toString() ;
                            paymentHolder = jsonObject.getString("downpayment").toString() ;
                            paymentHoldercar = jsonObject.getString("downpaymentcar").toString() ;

                            ListOfdataAdapter.add(GetDataAdapter2);
                            recyclerViewadapter = new RecyclerViewAdapter_users2(ListOfdataAdapter, context);
                            recyclerView.setAdapter(recyclerViewadapter);

                        }



                    }
                    catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
             /*   recyclerViewadapter = new RecyclerViewAdapter_users2(ListOfdataAdapter, context);
                recyclerView.setAdapter(recyclerViewadapter);*/

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

            // Setting Student Name, Phone Number, Class into TextView after done all process .
          //  NAME.setText(NameHolder);
            GetDataAdapter2.setUsername("Name:"+  NameHolder);
            GetDataAdapter2.setUserDownpay("DownPayment Properties:"+paymentHolder);
            GetDataAdapter2.setUserDownpay2("DownPayment Cars:"+paymentHoldercar);
            GetDataAdapter2.setUserEmail("Email:"+emailholder);
            GetDataAdapter2.setUserMobile("Mobile:"+mobileholder);
            GetDataAdapter2.setUserID("id:"+Idholder);
            ListOfdataAdapter.add(GetDataAdapter2);
            recyclerViewadapter = new RecyclerViewAdapter_users2(ListOfdataAdapter, context);
            recyclerView.setAdapter(recyclerViewadapter);

           // Toast.makeText(Retreiveoneuser.this, mobileholder, Toast.LENGTH_LONG).show();
        }
    }


}
