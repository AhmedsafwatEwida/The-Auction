package pioneers.safwat.mazad.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.util.HashMap;

import pioneers.safwat.mazad.R;

public class BidActivityold extends AppCompatActivity {

    //String HttpURL = "http://192.168.43.215/android_login_upload_images/ImageUpdate.php";
    String HttpURLitem = "http://192.168.100.8/mazad/ItemUpdatePrice.php";
    String HttpURLuser = "http://192.168.100.8/mazad/UserUpdatePayment.php";
    String HTTP_JSON_URL = "http://192.168.100.8/mazad/UsersJsonData.php";
   /* String HttpURLitem = "http://192.168.43.215/mazad/ItemUpdatePrice.php";
    String HttpURLuser = "http://192.168.43.215/mazad/UserUpdatePayment.php";
    String HTTP_JSON_URL = "http://192.168.43.215/mazad/UsersJsonData.php";*/
    String User_Downpay_JSON = "downpayment";
    String User_Downpay_JSON2 = "downpaymentcar";

    String User_Name_JSON = "name";
    String Image_Mobile_JSON = "mobile";
    String Image_Email_JSON = "email";
    String Image_ID_JSON = "id";
    JsonArrayRequest RequestOfJSonArray ;
private Dialog dialog;
    RequestQueue requestQueue ;
    private static final String TAG = "EarnedValue";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText recent,newprice;
    private RadioGroup radioauctionGroup,radiostatusGroup;
    private RadioButton radioauctionButton,radiostatusbutton;
    Button Updatename;
    String IdHolder, NameHolder, pathholder,priceholder,mobileholder,paymentholder;
    String auctione,stause,email,userpayment,userpayment2,newuserpayment,userid,newpayment,oldprice,usermobile;
    String bidwinholde,BidNumberHolder;
    String BidWinHolder1,BidWinHolder2,BidWinHolder3,BidWinHolder4,Priceholder1,Priceholder2,Priceholder3,Priceholder4,BidHourHolder,ItemtypeHolder;
    private SQLiteHandlerUsers db1;
    private SessionManager session;
    double userpaymentnumber, pricenumber,pricenumber2,newpricenumber,oldpricenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_price);
        // SqLite database handler
        db1 = new SQLiteHandlerUsers(getApplicationContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(BidActivityold.this);
        builder.setTitle("ALERT");
        builder.setMessage("Make Sure you Pay your DownPayment Before Bidding \n\n");
        builder.setCancelable(true);
        dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        AlertDialog dialog = builder.show();
        TextView textView = ((TextView) dialog.findViewById(android.R.id.message));
        // textView.setTextColor(Color.LTGRAY);
        textView.setTextSize(16);
        textView.setGravity(Gravity.CENTER);
        // session manager
       // session = new SessionManager(getApplicationContext());
        HashMap<String, String> usermazad = db1.getUserDetails();

       // userpayment = usermazad.get("downpayment");
         email = usermazad.get("email");
         usermobile=usermazad.get("mobile");
        // userid=usermazad.get("id");
        JSON_HTTP_CALL();
        // int selectedId = radiotypeGroup.getCheckedRadioButtonId();

       recent = (EditText)findViewById(R.id.recentprice);
      newprice = (EditText)findViewById(R.id.newprice);
        newpayment=newprice.getText().toString();

        Updatename = (Button)findViewById(R.id.updateitem);

        // Receive Student ID, Name , Phone Number , Class Send by previous ShowSingleRecordActivity.
        IdHolder = getIntent().getStringExtra("Id");
        NameHolder = getIntent().getStringExtra("email");
        priceholder = getIntent().getStringExtra("price");
        BidNumberHolder=getIntent().getStringExtra("nobid");
        Priceholder1 = getIntent().getStringExtra("price1");
        Priceholder2 = getIntent().getStringExtra("price2");
        Priceholder3 = getIntent().getStringExtra("price3");
        Priceholder4 = getIntent().getStringExtra("price4");
        BidWinHolder1=getIntent().getStringExtra("bidwin1");
        BidWinHolder2=getIntent().getStringExtra("bidwin2");
        BidWinHolder3=getIntent().getStringExtra("bidwin3");
        BidWinHolder4=getIntent().getStringExtra("bidwin4");
        bidwinholde=getIntent().getStringExtra("bidwin");
        BidHourHolder=getIntent().getStringExtra("bidhour");
        ItemtypeHolder=getIntent().getStringExtra("type");
      //  mobileholder = getIntent().getStringExtra("mobile");


        // Setting Received Student Name, Phone Number, Class into EditText.
       // recent.setText(priceholder);
        if (Integer.parseInt(BidNumberHolder)==1)
        {recent.setText(priceholder);
        oldpricenumber=Double.parseDouble(priceholder);}
        else if (Integer.parseInt(BidNumberHolder)==2)
        {   recent.setText(Priceholder1);
        oldpricenumber=Double.parseDouble(Priceholder1);}
        else if (Integer.parseInt(BidNumberHolder)==3)
        {   recent.setText(Priceholder2);
        oldpricenumber=Double.parseDouble(Priceholder2);}
        else if (Integer.parseInt(BidNumberHolder)==4)
        { recent.setText(Priceholder3);
        oldpricenumber=Double.parseDouble(Priceholder3);}
        else if (Integer.parseInt(BidNumberHolder)==5)
        { recent.setText(Priceholder4);
        oldpricenumber=Double.parseDouble(Priceholder4);}

      //  oldprice=recent.getText().toString();

     //   oldpricenumber=Double.parseDouble(priceholder);
  //   mobile.setText(mobileholder);
     //   payment.setText(paymentholder);


        // Adding click listener to update button .
        Updatename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {// Getting data from EditText after button click.
                    newpayment=newprice.getText().toString();
                   if(ItemtypeHolder=="Property")
                    pricenumber = Double.parseDouble(userpayment);//user downpayment
                    else
                       pricenumber2 = Double.parseDouble(userpayment2);

                    if (Double.parseDouble(newpayment) <= 0 ||(Double.parseDouble(newpayment)>pricenumber ||oldpricenumber>=Double.parseDouble(newpayment)) )
                        Toast.makeText(BidActivityold.this,"WRONG", Toast.LENGTH_LONG).show();
                    else
                    {
                        GetDataFromEditText();
                        // Sending Student Name, Phone Number, Class to method to update on server.
                        newpricenumber=(pricenumber)-(userpaymentnumber);
                    newuserpayment=String.valueOf(newpricenumber);
                        Toast.makeText(BidActivityold.this,userid+" "+email+" "+newuserpayment, Toast.LENGTH_LONG).show();
                     //   StudentRecordUpdate(IdHolder, priceholder,email);
                        StudentRecordUpdate(IdHolder, priceholder,Priceholder1,Priceholder2,Priceholder3,
                                Priceholder4,bidwinholde,BidWinHolder1,BidWinHolder2,
                                BidWinHolder3,BidWinHolder4,BidNumberHolder);

                        PaymentRecordUpdate(userid,newuserpayment);

                    }


                }
                catch(Exception e){
                    Log.e(TAG, "Error", e);
                    Toast toast= Toast.makeText(BidActivityold.this, "Wrong Price Entry or DownPayment not Paid", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();

                    return;
                }
                startActivity(new Intent(BidActivityold.this, MainActivityMaster.class));
            }

        });


    }

    // Method to get existing data from EditText.
    public void GetDataFromEditText(){
        if (Integer.parseInt(BidNumberHolder)==1)
        {
            Priceholder1 = newprice.getText().toString();
        userpaymentnumber = Double.parseDouble( Priceholder1);
        BidWinHolder1=email;
            BidNumberHolder=String.valueOf(2);

        }
        else if (Integer.parseInt(BidNumberHolder)==2)
        {  Priceholder2 = newprice.getText().toString();
            userpaymentnumber = Double.parseDouble( Priceholder2);
            BidWinHolder2=email;
            BidNumberHolder=String.valueOf(3);
        }
        else if (Integer.parseInt(BidNumberHolder)==3)
        {  Priceholder3 = newprice.getText().toString();
            userpaymentnumber = Double.parseDouble( Priceholder3);
            BidWinHolder3=email;
            BidNumberHolder=String.valueOf(4);
        }

        else if (Integer.parseInt(BidNumberHolder)==4)
        {  Priceholder4 = newprice.getText().toString();
            userpaymentnumber = Double.parseDouble( Priceholder4);
            BidWinHolder4=email;
            BidNumberHolder=String.valueOf(5);
        }
        else if (Integer.parseInt(BidNumberHolder)==5)
        {  priceholder = newprice.getText().toString();
            userpaymentnumber = Double.parseDouble( priceholder);
            bidwinholde=email;
            BidNumberHolder=String.valueOf(1);
        }


    }

    // Method to Update Student Record.
    public void StudentRecordUpdate(final String ID, final String S_price,final String S_price1,final String S_price2,final String S_price3,final String S_price4
            ,final String S_mobile,final String S_mobile1,final String S_mobile2,final String S_mobile3,final String S_mobile4,
    final String S_nobid){

        class StudentRecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(BidActivityold.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(BidActivityold.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

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

        studentRecordUpdateClass.execute(ID,S_price,S_price1,S_price2,S_price3,S_price4,S_mobile,S_mobile1,S_mobile2,S_mobile3,S_mobile4,S_nobid);
    }
    public void PaymentRecordUpdate(final String id, final String S_payment){

        class StudentRecordUpdateClass1 extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

               // progressDialog = ProgressDialog.show(BidActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(BidActivityold.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("id",params[0]);
                hashMap.put("downpayment",params[1]);

                //   hashMap.put("auction",params[2]);
                //  hashMap.put("status",params[3]);
                //    hashMap.put("biddate",params[4]);
                //   hashMap.put("bidwin",params[5]);
                //    hashMap.put("image_path",params[2]);

                finalResult = httpParse.postRequest(hashMap, AppConfig.HttpURLuser);

                return finalResult;
            }
        }

        StudentRecordUpdateClass1 studentRecordUpdateClass = new StudentRecordUpdateClass1();

        studentRecordUpdateClass.execute(id,S_payment);
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

        requestQueue = Volley.newRequestQueue(BidActivityold.this);

        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse(JSONArray array){

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
}
