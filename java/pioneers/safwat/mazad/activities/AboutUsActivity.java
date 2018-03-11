package pioneers.safwat.mazad.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import pioneers.safwat.mazad.R;

/**
 * Created by safwa on 12/11/2017.
 */

public class AboutUsActivity extends AppCompatActivity {

TextView texttitle,textbody;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.policy);
       // setContentView(R.layout.earned_data);
       textbody = (TextView)findViewById(R.id.article);

       texttitle=(TextView)findViewById(R.id.article_heading);

       /*  String rawHTML = "<HTML>"+
                "<body style='color: #1e656d; background-color: #f1f3cf'> </body>"+" %s "+
                "</HTML>";
        String myData = "<h2>Terms and Conditions:</h2>\n" +
                "By using this application, you are agreeing to be bound by these Terms and Conditions of Use, all applicable laws and regulations, and agree that you are responsible for compliance with any applicable local laws. If you do not agree with any of these terms, you are prohibited from using this application. The materials contained in this application are protected by applicable copyright and trade mark law.\n" +
                "<P> \n" +
                "The buyer of a vehicle will be responsible for contacting and clearing any paperwork related to the vehicle.\n" +
                "<P> \n" +
                "MazadJo is not responsible or liable for any delays or issues related to the delivery of the vehicle.\n" +
                "<P> \n" +
                "MazadJo is not responsible for any damages or loss to vehicle that may happen before or during the process of delivery.\n" +
                "<P> \n" +
                "You must complete the payment and collect the car within 24 hours once the auction ends.\n" +
                "<P> \n" +
                "do not fix or modify the car unless car documents has been completed and you have a registration under your name.\n" +
                "<P> \n" +
                "Closing the auction at a buyer's highest price doesn't mean that deal is confirmed , the sale of the vehicle for the closing price is subject to providers approval.\n" +
                "<P> \n" +
                "Displayed prices for sellers are not subject to any charges or deductions and guaranteed to be fully paid to sellers, MazadJo reserves the right to charge buyers from for participation in it's auction either on agreed contracts or pet vehicle in no more than 1 to 5 percent of buying prices.\n" +
                "<P> \n" +
                "Unless officially requested by seller, MazadJo reserves the right to use any of uploaded pictures on as is condition for any further advertising or social media adverts for the purpose of marketing.\n" +
                "<P> \n" +
                "MazadJo is just an intermediate for advertising vehicles and does not hold any responsibility towards the advertised vehicles nor their condition as they were never inspected or technically checked by Mazadoha.\n" +
                "<P> \n" +
                "All auctions opening prices are not considered as buying prices.\n" +
                "<P> \n" +
                "The seller is obliged to clearly show through photos the condition of his car, photos shall include and damages, scratches, dents and/or any other damages that may effect the buyers decision or the vehicle value. Attempt to hide any damage or information that shall effect the vehicle's market value shall result in the cancellation of the deal at any level or time. Legal actions shall be taken against seller if required.\n" +
                "<P> \n" +
                "After auction is closed, the closing price is not counted as guaranteed deal as the buyer reserves the right to optically inspect the vehicle in not more than 24 working hours from auction closing date. Unless any variance in condition , deal is confirmed.\n" +
                "<P> \n" +
                "In case vehicle has failed in the traffic Dept passing , the deal shall be counted as cancelled and seller has to return any down/full payments done by the buyer.\n" +
                "<P> \n" +
                "MazadJo reserves the right to block , hold or delete any user of it's application without prior notice incase auction roles and regulations are not followed by user..\n" +

                "<h3>الشروط و الأحكام :</h3>\n" +

                "باستخدام هذا التطبيق، فإنك توافق على الالتزام بهذه الشروط والأحكام، وجميع القوانين واللوائح المعمول بها، وتوافق على أن تكون مسؤولا عن الامتثال لأية قوانين المحلية المعمول بها. إذا كنت لا أتفق مع أي من هذه الشروط، لا يحق لك استخدام هذا التطبيق. المواد الواردة في هذا الطلب محمية بموجب حقوق النشر وقانون العلامات التجارية.\n" +
                "<P> \n" +
                "يكون مشتر السيارة مسؤولا عن الاتصال و تخليص كل الأوراق المتعلقة بالسيارة كتسجيل و خلافه. مزادوحة غير مسئول عن أي تأخير أو القضايا المتعلقة بإيصال السيارة.\n" +
                "<P> \n" +
                "مزادوحة غير مسئول عن أي أضرار أو خسائر للمركبة التي قد تحدث قبل أو أثناء عملية التسليم.\n" +
                "<P> \n" +
                "MazadJo is not responsible for any damages or loss to vehicle that may happen before or during the process of delivery.\n" +
                "<P> \n" +
                "You must complete the payment and collect the car within 24 hours once the auction ends.\n" +
                "<P> \n" +
                "do not fix or modify the car unless car documents has been completed and you have a registration under your name.\n" +
                "<P> \n" +
                "Closing the auction at a buyer's highest price doesn't mean that deal is confirmed , the sale of the vehicle for the closing price is subject to providers approval.\n" +
                "<P> \n" +
                "Displayed prices for sellers are not subject to any charges or deductions and guaranteed to be fully paid to sellers, MazadJo reserves the right to charge buyers from for participation in it's auction either on agreed contracts or pet vehicle in no more than 1 to 5 percent of buying prices.\n" +
                "<P> \n" +
                "Unless officially requested by seller, MazadJo reserves the right to use any of uploaded pictures on as is condition for any further advertising or social media adverts for the purpose of marketing.\n" +
                "<P> \n" +
                "MazadJo is just an intermediate for advertising vehicles and does not hold any responsibility towards the advertised vehicles nor their condition as they were never inspected or technically checked by Mazadoha.\n" +
                "<P> \n" +
                "All auctions opening prices are not considered as buying prices.\n" +
                "<P> \n" +
                "The seller is obliged to clearly show through photos the condition of his car, photos shall include and damages, scratches, dents and/or any other damages that may effect the buyers decision or the vehicle value. Attempt to hide any damage or information that shall effect the vehicle's market value shall result in the cancellation of the deal at any level or time. Legal actions shall be taken against seller if required.\n" +
                "<P> \n" +
                "After auction is closed, the closing price is not counted as guaranteed deal as the buyer reserves the right to optically inspect the vehicle in not more than 24 working hours from auction closing date. Unless any variance in condition , deal is confirmed.\n" +
                "<P> \n" +
                "In case vehicle has failed in the traffic Dept passing , the deal shall be counted as cancelled and seller has to return any down/full payments done by the buyer.\n" +
                "<P> \n" +
                "MazadJo reserves the right to block , hold or delete any user of it's application without prior notice incase auction roles and regulations are not followed by user..\n" +



                "<li>Schedule Performance Index:SPI = EV / PV  Ahead (> 1) or behind (< 1) schedule</li>";
        WebView webView = (WebView) findViewById(R.id.webView1);
        webView.loadData(String.format(rawHTML, myData, Color.parseColor("#1e656d")), "text/html", "utf-8");*/

    }

}
