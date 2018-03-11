package pioneers.safwat.mazad.activities;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
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

public class UpdateActivity2 extends AppCompatActivity {

   // String HttpURL = "http://192.168.43.215/android_login_upload_images/ImageUpdate.php";
   String HttpURL = "http://192.168.100.8/mazad/ItemUpdateAll.php";
   // String HttpURL = "http://192.168.43.215/mazad/ItemUpdateAll.php";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();
    EditText price,biddate,bidwin,bidtime,descriptio;
    private RadioGroup radioauctionGroup,radiostatusGroup;
    private RadioButton radioauctionButton,radiostatusbutton;
    Button Updatename;
    String IdHolder, NameHolder, pathholder,priceholder,biddateholder,bidwinholder,statusholder,auctionholder,descrholder,nobidholder,bidhourholder;
    String BidWinHolder1,BidWinHolder2,BidWinHolder3,BidWinHolder4,Priceholder1,Priceholder2,Priceholder3,Priceholder4;
    String auctione,stause;

    private Dialog dialog;
    private Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        radioauctionGroup = (RadioGroup) findViewById(R.id.radioauction);
        // int selectedId = radiotypeGroup.getCheckedRadioButtonId();
        radioauctionGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                   auctione= rb.getText().toString();
                    Toast.makeText(UpdateActivity2.this,auctione, Toast.LENGTH_SHORT).show();
                }
            }
        });
       radiostatusGroup= (RadioGroup) findViewById(R.id.radiostatus);
        // int selectedId = radiotypeGroup.getCheckedRadioButtonId();
        radiostatusGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                   stause= rb.getText().toString();
                    Toast.makeText(UpdateActivity2.this,stause, Toast.LENGTH_SHORT).show();
                }
            }
        });


       price = (EditText)findViewById(R.id.itemprice);
       biddate = (EditText)findViewById(R.id.itembidate);
       bidwin = (EditText)findViewById(R.id.itembidwin);
        bidtime = (EditText)findViewById(R.id.itembidtime);
       descriptio = (EditText)findViewById(R.id.itemdescr);
        Updatename = (Button)findViewById(R.id.updateitem);

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
       descrholder = getIntent().getStringExtra("descr");
        bidhourholder = getIntent().getStringExtra("bidhour");
        statusholder = getIntent().getStringExtra("status");
       auctionholder = getIntent().getStringExtra("auction");
            descriptio.setText(descrholder);

       // Toast.makeText(UpdateActivity2.this,priceholder, Toast.LENGTH_LONG).show();
        biddate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(UpdateActivity2.this, datePickerListener, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });
        try {
            if (nobidholder == null) {
                price.setText(priceholder);
                biddate.setText(biddateholder);
                bidwin.setText(bidwinholder);
                bidtime.setText(bidhourholder);
            } else if (Integer.parseInt(nobidholder) == 1) {
                price.setText(priceholder);
                biddate.setText(biddateholder);
                bidtime.setText(bidhourholder);
                bidwin.setText(bidwinholder);
            } else if (Integer.parseInt(nobidholder) == 2) {
                price.setText(Priceholder1);
                biddate.setText(biddateholder);
                bidtime.setText(bidhourholder);
                bidwin.setText(BidWinHolder1);
            } else if (Integer.parseInt(nobidholder) == 3) {
                price.setText(Priceholder2);
                biddate.setText(biddateholder);
                bidtime.setText(bidhourholder);
                bidwin.setText(BidWinHolder2);
            } else if (Integer.parseInt(nobidholder) == 4) {
                price.setText(Priceholder3);
                biddate.setText(biddateholder);
                bidtime.setText(bidhourholder);
                bidwin.setText(BidWinHolder3);
            } else if (Integer.parseInt(nobidholder) == 5) {
                price.setText(Priceholder4);
                biddate.setText(biddateholder);
                bidtime.setText(bidhourholder);
                bidwin.setText(BidWinHolder4);
            }
        }
        catch(NumberFormatException nfe) {
            // Log exception.

        }
        // Setting Received Student Name, Phone Number, Class into EditText.
     /*  price.setText(priceholder);
     biddate.setText(biddateholder);
        bidwin.setText(bidwinholder);*/


        // Adding click listener to update button .
        Updatename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Getting data from EditText after button click.
                GetDataFromEditText();
               // Toast.makeText(UpdateActivity2.this,priceholder, Toast.LENGTH_LONG).show();
statusholder=stause;
auctionholder=auctione;
if(statusholder!=null && auctionholder!=null && priceholder!=null && biddateholder!=null && bidwinholder!=null && bidhourholder!=null)
                // Sending Student Name, Phone Number, Class to method to update on server.
{ StudentRecordUpdate(IdHolder,priceholder,auctionholder,statusholder,biddateholder,bidhourholder,bidwinholder,nobidholder,descrholder);
    startActivity(new Intent(getApplicationContext(), RetreiveData2.class));
    finish();


}
else
{
  /*  new AlertDialog.Builder(UpdateActivity2.this )
            .setMessage( "Fill All Information" ).show();*/
    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity2.this);
    builder.setTitle("ALERT");
    builder.setMessage("Fill All Information \n\n");
    builder.setCancelable(true);
    dialog = builder.create();
    dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
    AlertDialog dialog = builder.show();
    TextView textView = ((TextView) dialog.findViewById(android.R.id.message));
  // textView.setTextColor(Color.LTGRAY);
    textView.setTextSize(16);
    textView.setGravity(Gravity.CENTER);
}

            }
        });

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
        bidhourholder=bidtime.getText().toString();
       // biddateholder=String.valueOf(days);
        biddateholder=biddate.getText().toString();
        bidwinholder=bidwin.getText().toString();
        descrholder=descriptio.getText().toString();
        nobidholder= String.valueOf(1);


    }

    // Method to Update Student Record.
    public void StudentRecordUpdate(final String ID, final String S_price,final String ImageAuction,final String ImageBidstatus,final String ImageBiddate,final String ImageBidhour, final String ImageBidwin, final  String ImageBidnumber,final  String Imagedescrip){

        class StudentRecordUpdateClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

         //       progressDialog = ProgressDialog.show(UpdateActivity2.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

          //      progressDialog.dismiss();

             //   Toast.makeText(UpdateActivity2.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();
             //   Toast.makeText(UpdateActivity2.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("id",params[0]);
                hashMap.put("price",params[1]);
                hashMap.put("auction",params[2]);
                hashMap.put("status",params[3]);
                hashMap.put("biddate",params[4]);
                hashMap.put("bidhour",params[5]);
                hashMap.put("bidwin",params[6]);
                hashMap.put("nobid",params[7]);
                hashMap.put("descr",params[8]);

            //    hashMap.put("image_path",params[2]);

                finalResult = httpParse.postRequest(hashMap, AppConfig.HttpURL_itemupdate);

                return finalResult;
            }
        }

        StudentRecordUpdateClass studentRecordUpdateClass = new StudentRecordUpdateClass();

        studentRecordUpdateClass.execute(ID,S_price,ImageAuction,ImageBidstatus,ImageBiddate,ImageBidhour,ImageBidwin,ImageBidnumber,Imagedescrip);
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
