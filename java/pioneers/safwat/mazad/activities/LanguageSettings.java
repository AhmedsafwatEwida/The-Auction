package pioneers.safwat.mazad.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

import pioneers.safwat.mazad.R;

/**
 * Created by safwa on 12/11/2017.
 */

public class LanguageSettings extends Activity {
    private Button btn_en, btn_ar;
    private Locale myLocale;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lang_set);


        btn_en = (Button)findViewById(R.id.english);
        btn_ar = (Button)findViewById(R.id.arabic);
        loadLocale();
        btn_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lang = "en";
                lang = "en";
                changeLang(lang);
                Intent i = new Intent(getApplication(),MainActivityMaster.class);

                startActivity(i);
                finish();
            }
        });
        btn_ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String lang = "en";
                lang = "ar";
                changeLang(lang);
                Intent i = new Intent(getApplication(),MainActivityMaster.class);

                startActivity(i);
                finish();
            }
        });




    }

    public void loadLocale()
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "");
        changeLang(language);
    }
    public void changeLang(String lang)
    {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
    public void saveLocale(String lang)
    {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }


}
