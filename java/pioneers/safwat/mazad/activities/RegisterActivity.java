package pioneers.safwat.mazad.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hbb20.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pioneers.safwat.mazad.R;

/**
 * Created by safwa on 12/3/2017.
 */

public class RegisterActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnRegister;
    private Button btnLinkToLogin;
    private EditText inputFullName;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputMobile;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandlerUsers db1;
    CountryCodePicker ccp;
    String countryCodeAndroid ;
    String User_Downpay_JSON = "downpayment";
    String User_Downpay_JSON2 = "downpaymentcar";
    String User_Name_JSON = "name";
    String Image_Mobile_JSON = "mobile";
    String Image_Email_JSON = "email";
    String Image_ID_JSON = "id";
    RequestQueue requestQueue ;
    JsonArrayRequest RequestOfJSonArray ;
    List<DataAdapter_users> ListOfdataAdapter;
    ArrayList<String> ImageTitleNameArrayListForClick;
    List<DataAdapter_users> FilteredListOfdataAdapter;
    List<String> IdList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        inputFullName = (EditText) findViewById(R.id.name);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputMobile = (EditText) findViewById(R.id.mobile);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        ccp=(CountryCodePicker)findViewById(R.id.ccp);
        ccp.resetToDefaultCountry();
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                countryCodeAndroid = ccp.getSelectedCountryCodeWithPlus();
                Log.d("Country Code", countryCodeAndroid);
            }
        });
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // SQLite database handler
        db1 = new SQLiteHandlerUsers(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(RegisterActivity.this,
                    MainActivityMaster.class);
            startActivity(intent);
            finish();
        }
      //  JSON_HTTP_CALL();


        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                countryCodeAndroid = ccp.getSelectedCountryCodeWithPlus();
                String name = inputFullName.getText().toString().trim();
                String  email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String mobile =(countryCodeAndroid+inputMobile.getText().toString().trim());

                Getemaile(email);

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()&& !mobile.isEmpty()) {
                    registerUser(name, email, mobile, password);


                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
public String Getemaile(String emailo)
{
    JSON_HTTP_CALL();
    emailo = inputEmail.getText().toString().trim();
    for(int counti=0;counti<IdList.size();counti++)
    {
        if(emailo.equalsIgnoreCase(IdList.get(counti).toString().trim()))
        {
            Toast.makeText(getApplicationContext(),"Existing User!", Toast.LENGTH_LONG).show();
        }
        else
        {
            emailo=inputEmail.getText().toString().trim();
           // Toast.makeText(getApplicationContext(),emailo, Toast.LENGTH_LONG).show();
        }
    }
    return emailo;

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

        requestQueue = Volley.newRequestQueue(RegisterActivity.this);

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
                GetDataAdapter2.setUserDownpay("DownPayment Property:"+json.getString(User_Downpay_JSON));
                GetDataAdapter2.setUserDownpay2("DownPayment Car:"+json.getString(User_Downpay_JSON2));
                GetDataAdapter2.setUserEmail(json.getString(Image_Email_JSON));
                GetDataAdapter2.setUserMobile("Mobile:"+json.getString(Image_Mobile_JSON));
                GetDataAdapter2.setUserID("id:"+json.getString(Image_ID_JSON));
                IdList.add(json.getString("email").toString());
            //    ImageTitleNameArrayListForClick.add(json.getString(User_Name_JSON));
                //ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));
                // ListOfdataAdapter.add(GetDataAdapter2);
            } catch (JSONException e) {

                e.printStackTrace();
            }

          //  ListOfdataAdapter.add(GetDataAdapter2);
            //     FilteredListOfdataAdapter.addAll(ListOfdataAdapter);
        }

       /* recyclerViewadapter = new RecyclerViewAdapter_users(ListOfdataAdapter, this);

        recyclerView.setAdapter(recyclerViewadapter);*/


    }


    private void registerUser(final String name, final String email,final String mobile,
                              final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registering ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
                    //    String uid = jObj.getString("uid");

                        JSONObject usermazad = jObj.getJSONObject("usermazad");
                        String name = usermazad.getString("name");
                        String email = usermazad.getString("email");
                        String mobile = usermazad.getString("mobile");
                        String downpayment = usermazad.getString("downpayment");
                        String uid = usermazad.getString("uid");
                        String created_at = usermazad.getString("created_at");

                        // Inserting row in users table
                        db1.addUser(name, email, uid, mobile, downpayment, created_at);

                        Toast.makeText(getApplicationContext(), "User successfully registered. Try login now!", Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(RegisterActivity.this,MainActivityMaster.class);
                        startActivity(intent);
                       // finish();
                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }

        , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }

        })


        {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", name);
                params.put("email", email);
                params.put("mobile", mobile);
              //  params.put("mobile", downpayment);
                params.put("password", password);

                return params;
            }

        };

      /*  Intent intent = new Intent(
                RegisterActivity.this,
                MainActivityMaster.class);
        startActivity(intent);*/

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }



}