package pioneers.safwat.mazad.activities;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import pioneers.safwat.mazad.R;

public class UserUpdatepayment extends AppCompatActivity {

    //String HttpURL = "http://192.168.43.215/android_login_upload_images/ImageUpdate.php";
   //String HttpURL = "http://192.168.100.8/mazad/UserUpdatePayment.php";
  //  String HttpURL = "http://192.168.43.215/mazad/UserUpdatePayment.php";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText email,mobile,payment,paymentcar;
    TextView idview,useremai,usermobile;
    Button Updatename;
    String IdHolder, NameHolder, pathholder,emailholder,mobileholder,paymentholder,paymentholdercar;
    ProgressDialog pDialog;
    HashMap<String,String> ResultHash = new HashMap<>();
    String TempItem,nameitem,paymentcaro,paymentprop,mobileuser,iduserre;
    String FinalJSonObject ;
    String ParseResult ;
    String  updateid,updatepaymentprop,updatepaymentcar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_payment);

        // int selectedId = radiotypeGroup.getCheckedRadioButtonId();

       email = (EditText)findViewById(R.id.useremail);
       mobile = (EditText)findViewById(R.id.usermobile);
       payment = (EditText)findViewById(R.id.userpayment);
        paymentcar = (EditText)findViewById(R.id.userpaymentcar);
       idview = (TextView)findViewById(R.id.idteextview);
        useremai = (TextView) findViewById(R.id.emailview);
       usermobile = (TextView) findViewById(R.id.mobileview);

        Updatename = (Button)findViewById(R.id.updateitem);

        // Receive Student ID, Name , Phone Number , Class Send by previous ShowSingleRecordActivity.
     //   IdHolder = getIntent().getStringExtra("Id");
        TempItem = getIntent().getStringExtra("ListViewValue");
        nameitem = getIntent().getStringExtra("nameViewValue");
        paymentcaro = getIntent().getStringExtra("paymentcarValue");
        paymentprop = getIntent().getStringExtra("paymentpropValue");
        mobileuser = getIntent().getStringExtra("mobileViewValue");
        iduserre = getIntent().getStringExtra("idValue");
       // HttpWebCall(TempItem);
     //   Toast.makeText(UserUpdatepayment.this,iduserre, Toast.LENGTH_LONG).show();
        //NameHolder = getIntent().getStringExtra("email");
       /* emailholder = getIntent().getStringExtra("email");
        mobileholder = getIntent().getStringExtra("mobile");
        paymentholder = getIntent().getStringExtra("downpayment");
        paymentholdercar = getIntent().getStringExtra("downpaymentcar");*/
        email.setText(TempItem);
        mobile.setText(mobileuser);
        payment.setText(paymentprop);
        paymentcar.setText(paymentcaro);
        idview.setText(iduserre);
        useremai.setText("User Email: "+TempItem);
        usermobile.setText("User Mobile: "+mobileuser);

        // Setting Received Student Name, Phone Number, Class into EditText.
      /*email.setText(emailholder);
     mobile.setText(mobileholder);
        payment.setText(paymentholder);
        paymentcar.setText(paymentholdercar);
useremai.setText("User Email: "+emailholder);
usermobile.setText("User Mobile: "+mobileholder);*/

        // Adding click listener to update button .
        Updatename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Getting data from EditText after button click.
               GetDataFromEditText();

                // Sending Student Name, Phone Number, Class to method to update on server.
               // StudentRecordUpdate(IdHolder,paymentholder,paymentholdercar);
               // Toast.makeText(UserUpdatepayment.this,updatepaymentcar, Toast.LENGTH_LONG).show();

               StudentRecordUpdate(updateid,updatepaymentprop,updatepaymentcar);
                Toast.makeText(UserUpdatepayment.this,"User Payment Updated", Toast.LENGTH_LONG).show();
               // HttpWebCall(TempItem);

            }
        });


    }

    // Method to get existing data from EditText.
    public void GetDataFromEditText(){

    //   updateemail = email.getText().toString();
    //     updatemobile=mobile.getText().toString();
        updateid=idview.getText().toString();
         updatepaymentprop=payment.getText().toString();
         updatepaymentcar=paymentcar.getText().toString();


    }



    public void StudentRecordUpdate(String ID, String S_payment,String S_paymentcar){

        class StudentRecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

          progressDialog = ProgressDialog.show(UserUpdatepayment.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

               progressDialog.dismiss();

           //     Toast.makeText(getBaseContext(),httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("id",params[0]);
                hashMap.put("downpayment",params[1]);
                hashMap.put("downpaymentcar",params[2]);
                //   hashMap.put("auction",params[2]);
                //  hashMap.put("status",params[3]);
                //    hashMap.put("biddate",params[4]);
                //   hashMap.put("bidwin",params[5]);
                //    hashMap.put("image_path",params[2]);

                finalResult = httpParse.postRequest(hashMap, AppConfig.HttpURLupdatepayment);

                return finalResult;
            }
        }

        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass();

        studentRecordUpdateClass.execute(ID,S_payment,S_paymentcar);
    }



}
