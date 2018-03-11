package pioneers.safwat.mazad.activities;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import pioneers.safwat.mazad.R;

public class Showwinprice extends AppCompatActivity {

   // String HttpURL = "http://192.168.43.215/android_login_upload_images/ImageUpdate.php";
   String HttpURL = "http://192.168.100.8/mazad/ItemUpdateAll.php";
   //String HttpURL = "http://192.168.43.215/mazad/ItemUpdateAll.php";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText price,biddate,bidwin;
    TextView priceview1,priceview2,priceview3,priceview4,win1,win2,win3,win4,win5,priceview5;
    private RadioGroup radioauctionGroup,radiostatusGroup;
    private RadioButton radioauctionButton,radiostatusbutton;
    Button Updatename;
    String IdHolder, NameHolder, pathholder,priceholder,biddateholder,bidwinholder,statusholder,auctionholder,nobidholder;
    String BidWinHolder1,BidWinHolder2,BidWinHolder3,BidWinHolder4,Priceholder1,Priceholder2,Priceholder3,Priceholder4;
    String auctione,stause;

    private Dialog dialog;
    private Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_activity);


       priceview1 = (TextView)findViewById(R.id.priceview1);
        priceview2 = (TextView)findViewById(R.id.priceview2);
        priceview3 = (TextView)findViewById(R.id.priceview3);
        priceview4 = (TextView)findViewById(R.id.priceview4);
        priceview5 = (TextView)findViewById(R.id.priceview5);
        win1 = (TextView)findViewById(R.id.winview1);
        win2 = (TextView)findViewById(R.id.winview2);
        win3 = (TextView)findViewById(R.id.winview3);
        win4 = (TextView)findViewById(R.id.winview4);
        win5 = (TextView)findViewById(R.id.winview5);


        // Receive Student ID, Name , Phone Number , Class Send by previous ShowSingleRecordActivity.
        IdHolder = getIntent().getStringExtra("Id");
        //NameHolder = getIntent().getStringExtra("email");
        pathholder = getIntent().getStringExtra("image_path1");
        priceholder = getIntent().getStringExtra("price");
        Priceholder1 = getIntent().getStringExtra("price1");
        Priceholder2 = getIntent().getStringExtra("price2");
        Priceholder3 = getIntent().getStringExtra("price3");
        Priceholder4 = getIntent().getStringExtra("price4");

        biddateholder = getIntent().getStringExtra("biddate");
        bidwinholder = getIntent().getStringExtra("bidwin");
        BidWinHolder1 = getIntent().getStringExtra("bidwin1");
        BidWinHolder2 = getIntent().getStringExtra("bidwin2");
        BidWinHolder3 = getIntent().getStringExtra("bidwin3");
        BidWinHolder4 = getIntent().getStringExtra("bidwin4");
        nobidholder = getIntent().getStringExtra("nobid");

        statusholder = getIntent().getStringExtra("status");
       auctionholder = getIntent().getStringExtra("auction");
        priceview1.setText("First Price: "+"\n"+"\n"+priceholder);
        priceview2.setText("Second Price: "+"\n"+"\n"+Priceholder1);
        priceview3.setText("Third Price: "+"\n"+"\n"+Priceholder2);
        priceview4.setText("Fourth Price: "+"\n"+"\n"+Priceholder3);
        priceview5.setText("Fifth Price: "+"\n"+"\n"+Priceholder4);
        win1.setText("First Winner: "+"\n"+"\n"+bidwinholder);
        win2.setText("Second Winner: "+"\n"+"\n"+BidWinHolder1);
        win3.setText("Third Winner: "+"\n"+"\n"+BidWinHolder2);
        win4.setText("Fourth Winner: "+"\n"+"\n"+BidWinHolder3);
        win5.setText("Fifth Winner: "+"\n"+"\n"+BidWinHolder4);




        // Adding click listener to update button .


    }

    // Method to get existing data from EditText.
    public void GetDataFromEditText(){
        Calendar calendar = Calendar.getInstance();
      /*  try {
            calendar.setTime((new SimpleDateFormat("dd/MM/yy")).parse(
                    biddate.getText().toString()));
            double date1 = calendar.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

      /*  SimpleDateFormat mdformat = new SimpleDateFormat("MM/dd/yy");
        String strDate =  mdformat.format(calendar.getTime());
        long diff = Long.parseLong(strDate) - Long.parseLong(biddate.getText().toString());
        long days = diff / (24 * 60 * 60 * 1000);*/
        priceholder = price.getText().toString();
       // biddateholder=String.valueOf(days);
        biddateholder=biddate.getText().toString();
        bidwinholder=bidwin.getText().toString();
        nobidholder= String.valueOf(1);


    }

    // Method to Update Student Record.
    public void StudentRecordUpdate(final String ID, final String S_price,final String ImageAuction,final String ImageBidstatus,final String ImageBiddate, final String ImageBidwin, final  String ImageBidnumber){

        class StudentRecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(Showwinprice.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                Toast.makeText(Showwinprice.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("id",params[0]);
                hashMap.put("price",params[1]);
                hashMap.put("auction",params[2]);
                hashMap.put("status",params[3]);
                hashMap.put("biddate",params[4]);
                hashMap.put("bidwin",params[5]);
                hashMap.put("nobid",params[6]);

            //    hashMap.put("image_path",params[2]);

                finalResult = httpParse.postRequest(hashMap, AppConfig.HttpURL_itemupdate);

                return finalResult;
            }
        }

        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass();

        studentRecordUpdateClass.execute(ID,S_price,ImageAuction,ImageBidstatus,ImageBiddate,ImageBidwin,ImageBidnumber);
    }

    DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormat = "MM/dd/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
            biddate.setText(sdf.format(myCalendar.getTime()));
        }

    };

}
