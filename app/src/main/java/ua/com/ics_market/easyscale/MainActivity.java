package ua.com.ics_market.easyscale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang.ArrayUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,TaskCompleteListener{

    Button btn1, btn2;
    TextView tv1, tv2;
    ScaleClass sc;
    InetAddress inetAddress;
    Document doc = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sc = new ScaleClass(this);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btn1:

                int ip_addr = sc.getAccessPointInfo();
                byte [] ipAddress = BigInteger.valueOf(ip_addr).toByteArray();
                ArrayUtils.reverse(ipAddress);

                try {
                    inetAddress = InetAddress.getByAddress(ipAddress);
                    tv1.setText(inetAddress.getHostAddress());
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btn2:

                final String url="http://"+inetAddress.getHostAddress()+":80";
                //sc.setURL(url);
                //Toast
                Toast.makeText(this, url, Toast.LENGTH_LONG).show();

                String err = "ERROR";

                try {
                    doc = new GetDataFromUrl(this).execute(url).get();
                } catch (InterruptedException | ExecutionException e ) {
                    tv2.setText(err.concat(e.getMessage()));
                    e.printStackTrace();
                }
//                sc.sendRequest(new ScaleClass.VolleyCallback() {
//
//                    @Override
//                    public void onSuccess(String result) {
//                        Document doc = Jsoup.parse(result);
//                        Element link = doc.select("body > h1").first();
//                        String content  = link.text();
//                        link = doc.select("body > p").first();
//                        content +="\n"+link.text();
//                        tv2.setText(content);
//                        //tv2.setText(Html.fromHtml(result));
//                        //tv2.setText(result);
//                    }
//
//                    @Override
//                    public void onError(String error) {
//
//                        tv2.setText("ERROR: "+error);
//
//                    }
//
//                });
                break;
        }

    }

    @Override
    public void onTaskComplete(String result) {

        Element link = doc.select("body > h1").first();
        String content =link.text();

        link = doc.select("body > p").first();
        content +="\n"+link.text();
        tv2.setText(content);
    }

}
