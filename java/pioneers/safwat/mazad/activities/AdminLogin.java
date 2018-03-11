package pioneers.safwat.mazad.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.HashMap;
import java.util.List;

import pioneers.safwat.mazad.R;

public class AdminLogin extends AppCompatActivity {

    //String HttpURL = "http://192.168.43.215/android_login_upload_images/ImageUpdate.php";

//   String HTTP_JSON_URL = "http://192.168.100.8/mazad/AdminsJsonData.php";
   //String HTTP_JSON_URL = "http://192.168.43.215/mazad/AdminsJsonData.php";
  //  String User_Downpay_JSON = "downpayment";
    String Admin_Pass_JSON = "password";
   // String Image_Mobile_JSON = "mobile";
    String Admin_Email_JSON = "email";
    String Admin_ID_JSON = "id";
    JsonArrayRequest RequestOfJSonArray ;
   // final String[] emailholder=null;
   // final String[] pssholder=null;
    List<String> emailholder = new ArrayList<>();
    List<String> pssholder = new ArrayList<>();

    RequestQueue requestQueue ;
    private static final String TAG = "EarnedValue";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText emailtext,passtext;
    private RadioGroup radioauctionGroup,radiostatusGroup;
    private RadioButton radioauctionButton,radiostatusbutton;
    Button Updatename;
   // String emailholder,pssholder;
    String auctione,pass,email;
    private SQLiteHandlerUsers db1;
    private SessionManager session;
    double userpaymentnumber, pricenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        // SqLite database handler
      //  db1 = new SQLiteHandlerUsers(getApplicationContext());

        emailtext = (EditText)findViewById(R.id.email);
        passtext = (EditText)findViewById(R.id.password);
        email=emailtext.getText().toString().trim();
        pass=passtext.getText().toString();
        JSON_HTTP_CALL();
        // session manager
       // session = new SessionManager(getApplicationContext());
       // HashMap<String, String> usermazad = db1.getUserDetails();

       // final String userpayment = usermazad.get("downpayment");
       //  email = usermazad.get("email");
        // int selectedId = radiotypeGroup.getCheckedRadioButtonId();

        Updatename = (Button)findViewById(R.id.btnLogin);

        // Receive Student ID, Name , Phone Number , Class Send by previous ShowSingleRecordActivity.
       // IdHolder = getIntent().getStringExtra("Id");
        //NameHolder = getIntent().getStringExtra("email");
     //   emailholder = getIntent().getStringExtra("price");
     //   mobileholder = getIntent().getStringExtra("mobile");


        // Setting Received Student Name, Phone Number, Class into EditText.
     // recent.setText(emailholder);
  //   mobile.setText(mobileholder);
     //   payment.setText(paymentholder);


        // Adding click listener to update button .
        Updatename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailtext.getText().toString().trim();
                pass = passtext.getText().toString();

                try {// Getting data from EditText after button click.

                    for (int i = 0; i < emailholder.size(); i++)
                        if (!email.matches("") && !pass.matches("") && email.equalsIgnoreCase(emailholder.get(i)) && pass.equalsIgnoreCase(pssholder.get(i)))
                        {
                            Intent intent = new Intent(getApplicationContext(), RetreiveData2.class);
                            startActivity(intent);
                            finish();
                            break;
                        }
                    //Toast.makeText(AdminLogin.this, "WRONG", Toast.LENGTH_LONG).show();

                    // Sending Student Name, Phone Number, Class to method to update on server.}

                }

                catch(Exception e){
                    Log.e(TAG, "Error", e);
                    Toast toast= Toast.makeText(AdminLogin.this, "Wrong Credentials", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER| Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    return;
                }


            }

        });


    }

    // Method to get existing data from EditText.
/*    public void GetDataFromEditText(){
        emailholder = newprice.getText().toString();



    }*/

    public void JSON_HTTP_CALL(){

        RequestOfJSonArray = new JsonArrayRequest(AppConfig.HTTP_JSON_URLAdmins,

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

        requestQueue = Volley.newRequestQueue(AdminLogin.this);

        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse(JSONArray array){

        for(int i = 0; i<array.length(); i++) {


            JSONObject json = null;
            try {

                json = array.getJSONObject(i);


              /*    emailholder=(json.getString(Admin_Email_JSON));
                pssholder=(json.getString(Admin_Pass_JSON));*/
              emailholder.add(json.getString(Admin_Email_JSON).toString());
             pssholder.add(json.getString(Admin_Pass_JSON).toString());

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
