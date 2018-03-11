package pioneers.safwat.mazad.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import pioneers.safwat.mazad.R;

public class UploadItem extends AppCompatActivity {

    Bitmap bitmap,bitmap2,bitmap3;

    boolean check = true;
    private SQLiteHandlerUsers db1;
    Button SelectImageGallery, UploadImageServer,SelectImageGallery2,SelectImageGallery3;
private Dialog dialog;
    ImageView imageView;
    private RadioGroup radiotypeGroup;
    private RadioButton radiotypeButton;
    EditText itemdescript;
    ProgressDialog progressDialog ;
    private SessionManager session;
    String GetImageNameEditText;
    String GetImagedescrip;
    String GetImagetype,gettype;
String type,descrip;
    String ImageName = "email" ;
    String ImageType = "type" ;
    String ImageDsc = "descr" ;
    String Proptype = "proptype" ;
    String ConvertImage;
    String ConvertImage2;
    String ConvertImage3;
    String ImagePath = "image_path1" ;
    String ImagePath2 = "image_path2" ;
    String ImagePath3 = "image_path3" ;
    ByteArrayOutputStream byteArrayOutputStreamObject,byteArrayOutputStreamObject2,byteArrayOutputStreamObject3 ;
   // String ServerUploadPath ="http://192.168.43.215/android_login_upload_images/img_upload_to_server2.php" ;
   // String ServerUploadPath ="http://192.168.100.8/android_login_upload_images/img_upload_to_server2.php" ;
  // String ServerUploadPath ="http://192.168.43.215/mazad/item_uploadprop.php" ;
    String ServerUploadPath ="http://192.168.100.8/mazad/item_uploadprop.php" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        db1 = new SQLiteHandlerUsers(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

      /*  if (!session.isLoggedIn()) {
            logoutUser();
        }*/

        // Fetching user details from sqlite
        HashMap<String, String> usermazad = db1.getUserDetails();

        final String name = usermazad.get("name");
        final String email = usermazad.get("email");

        radiotypeGroup = (RadioGroup) findViewById(R.id.radiotype);
       // int selectedId = radiotypeGroup.getCheckedRadioButtonId();
        radiotypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId > -1) {
                    type= rb.getText().toString();
                    Toast.makeText(UploadItem.this, type, Toast.LENGTH_SHORT).show();

                }

            }
        });
        // find the radiobutton by returned id
       // radiotypeButton = (RadioButton) findViewById(selectedId);



        imageView = (ImageView)findViewById(R.id.imageView);
      //  imageView2 = (ImageView)findViewById(R.id.imageView2);
       // imageView3 = (ImageView)findViewById(R.id.imageView3);
       itemdescript = (EditText)findViewById(R.id.itemdescr);
descrip=itemdescript.getText().toString();
        SelectImageGallery = (Button)findViewById(R.id.buttonSelect);
        SelectImageGallery2 = (Button)findViewById(R.id.buttonSelect2);
        SelectImageGallery3 = (Button)findViewById(R.id.buttonSelect3);

        UploadImageServer = (Button)findViewById(R.id.buttonUpload);

        SelectImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);

            }
        });
        SelectImageGallery2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent();

                intent2.setType("image/*");

                intent2.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent2, "Select Image From Gallery2"), 2);
            //    Toast.makeText(MainActivity2.this,ConvertImage2,Toast.LENGTH_LONG).show();
            }
        });
        SelectImageGallery3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent3 = new Intent();

                intent3.setType("image/*");

                intent3.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent3, "Select Image From Gallery3"), 3);
             //   Toast.makeText(MainActivity2.this,ConvertImage3,Toast.LENGTH_LONG).show();
            }
        });


        UploadImageServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetImageNameEditText = email;
                GetImagedescrip = itemdescript.getText().toString();
                GetImagetype = type;
                gettype="Property";
                if(!GetImagedescrip.matches("")&&!GetImagetype.matches("") )
                {
                ImageUploadToServerFunction();
                //    UploadItem.this.finish();
                    startActivity(new Intent(getApplicationContext(), MainActivityMaster.class));

                }
                else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UploadItem.this);
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
    @Override
    protected void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {

                bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                imageView.setImageBitmap(bitmap);
                byteArrayOutputStreamObject = new ByteArrayOutputStream();
                bitmap.createScaledBitmap(bitmap,50,50,true);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50 , byteArrayOutputStreamObject);
                byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();
                 ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);
            //   Toast.makeText(UploadItem.this,ConvertImage,Toast.LENGTH_LONG).show();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
       else if (RC == 2 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri2 = I.getData();

            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), uri2);
                imageView.setImageBitmap(bitmap2);
                byteArrayOutputStreamObject2 = new ByteArrayOutputStream();
                bitmap.createScaledBitmap(bitmap,50,50,true);
                bitmap2.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStreamObject2);
                byte[] byteArrayVar2 = byteArrayOutputStreamObject2.toByteArray();
                 ConvertImage2 = Base64.encodeToString(byteArrayVar2, Base64.DEFAULT);
              //  Toast.makeText(MainActivity2.this,ConvertImage2,Toast.LENGTH_LONG).show();
            } catch (IOException e) {

                e.printStackTrace();
            }
        }
       else if (RC == 3 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri3 = I.getData();

            try {

                bitmap3 = MediaStore.Images.Media.getBitmap(getContentResolver(), uri3);
                imageView.setImageBitmap(bitmap3);
                byteArrayOutputStreamObject3 = new ByteArrayOutputStream();
                bitmap.createScaledBitmap(bitmap,50,50,true);
                bitmap3.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStreamObject3);
                byte[] byteArrayVar3 = byteArrayOutputStreamObject3.toByteArray();
                 ConvertImage3 = Base64.encodeToString(byteArrayVar3, Base64.DEFAULT);
              //  Toast.makeText(MainActivity2.this,ConvertImage3,Toast.LENGTH_LONG).show();


            } catch (IOException e) {

                e.printStackTrace();
            }
        }





    }

    public void ImageUploadToServerFunction(){

        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();

               progressDialog = ProgressDialog.show(UploadItem.this,"Image is Uploading","Please Wait",false,false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();

                // Printing uploading success message coming from server on android app.
                Toast.makeText(UploadItem.this,string1.toString(),Toast.LENGTH_LONG).show();

                // Setting image as transparent after done uploading.
                imageView.setImageResource(android.R.color.transparent);
//                imageView2.setImageResource(android.R.color.transparent);
             //   imageView3.setImageResource(android.R.color.transparent);

            }

            @Override
            protected String doInBackground(Void... params) {

                ImageProcessClass imageProcessClass = new ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();

                HashMapParams.put(ImageName, GetImageNameEditText);
                HashMapParams.put(Proptype, GetImagetype);
                HashMapParams.put(ImageType, gettype);
                HashMapParams.put(ImageDsc, GetImagedescrip);
                HashMapParams.put(ImagePath, ConvertImage);
                HashMapParams.put(ImagePath2, ConvertImage2);
                HashMapParams.put(ImagePath3, ConvertImage3);

                String FinalData = imageProcessClass.ImageHttpRequest(AppConfig.ServerUploadPathprop_upload, HashMapParams);
//                Toast.makeText(UploadItem.this,"Uploaded Successfully",Toast.LENGTH_LONG).show();
                return FinalData;

            }

        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

        AsyncTaskUploadClassOBJ.execute();
    }

    public class ImageProcessClass{

        public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {

                URL url;
                HttpURLConnection httpURLConnectionObject ;
                OutputStream OutPutStream;
                BufferedWriter bufferedWriterObject ;
                BufferedReader bufferedReaderObject ;
                int RC ;

                url = new URL(requestURL);

                httpURLConnectionObject = (HttpURLConnection) url.openConnection();

                httpURLConnectionObject.setReadTimeout(19000);

                httpURLConnectionObject.setConnectTimeout(19000);

                httpURLConnectionObject.setRequestMethod("POST");

                httpURLConnectionObject.setDoInput(true);

                httpURLConnectionObject.setDoOutput(true);

                OutPutStream = httpURLConnectionObject.getOutputStream();

                bufferedWriterObject = new BufferedWriter(

                        new OutputStreamWriter(OutPutStream, "UTF-8"));

                bufferedWriterObject.write(bufferedWriterDataFN(PData));

                bufferedWriterObject.flush();

                bufferedWriterObject.close();

                OutPutStream.close();

                RC = httpURLConnectionObject.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {

                    bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReaderObject.readLine()) != null){

                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            StringBuilder stringBuilderObject;

            stringBuilderObject = new StringBuilder();

            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {

                if (check)

                    check = false;
                else
                    stringBuilderObject.append("&");

                stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilderObject.append("=");

                stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilderObject.toString();
        }

    }
}