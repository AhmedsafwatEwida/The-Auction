package pioneers.safwat.mazad.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import pioneers.safwat.mazad.R;

/**
 * Created by safwa on 12/11/2017.
 */

public class Contact extends Activity{
    Button call,mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
       call=(Button)findViewById(R.id.call);
        mail=(Button)findViewById(R.id.maile);

        mail=(Button)findViewById(R.id.maile);
       call.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {

                Intent callIntent =new Intent(Intent.ACTION_CALL    );
                callIntent.setData(Uri.parse("tel:0097455101776"));
                startActivity(callIntent);
            }
        });
       mail.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View view) {

                String[] TO = {"mazda.jo1@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");


                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                    finish();
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(Contact.this,
                            "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
