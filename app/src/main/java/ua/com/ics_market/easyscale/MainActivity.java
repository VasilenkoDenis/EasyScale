package ua.com.ics_market.easyscale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.viewpagerindicator.CirclePageIndicator;
import org.apache.commons.lang.ArrayUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,TaskCompleteListener{


    Button btn1, btn2;
    TextView tv1, tv2, display;
    ScaleClass sc;
    InetAddress inetAddress;
    public static Document doc = null;
    ViewPager pager;
    FragmentPagerAdapter adapter;
    CirclePageIndicator circleIndicator;
    Timer refreshTimer;
    RefreshTimerTask refreshTimerTask;

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return FirstFragment.newInstance(0);
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return FirstFragment.newInstance(1);
                case 2: // Fragment # 1 - This will show SecondFragment
                    return FirstFragment.newInstance(2);
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {

            return "Page " + position;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sc = new ScaleClass(this);
        // Определение таймера для периодмческого опроса весов
        refreshTimer = new Timer();
        refreshTimerTask = new RefreshTimerTask(this);
        refreshTimerTask.setURL("http://192.168.4.22:80");

        refreshTimer.schedule(refreshTimerTask, 3000,1000);

        pager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        circleIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        circleIndicator.setViewPager(pager);

    //    btn1 = (Button) findViewById(R.id.btn1);
    //    btn2 = (Button) findViewById(R.id.btn2);
        display = (TextView) findViewById(R.id.display);
    //    tv1 = (TextView) findViewById(R.id.tv1);
    //    tv2 = (TextView) findViewById(R.id.tv2);

    //    btn1.setOnClickListener(this);
    //    btn2.setOnClickListener(this);



        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
             //   Toast.makeText(MainActivity.this,"Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void onClick(View v) {

        //switch(v.getId()){

            //case R.id.btn1:

                //int ip_addr = sc.getAccessPointInfo();
                //byte [] ipAddress = BigInteger.valueOf(ip_addr).toByteArray();
                //ArrayUtils.reverse(ipAddress);

                //try {
                //  inetAddress = InetAddress.getByAddress(ipAddress);
                //  tv1.setText(inetAddress.getHostAddress());
                //} catch (UnknownHostException e) {
                //    e.printStackTrace();
                //}
                //break;

            //case R.id.btn2:

                //final String url="http://"+inetAddress.getHostAddress()+":80";
                //final String url="http://192.168.4.22:80";



                //sc.setURL(url);
                //Toast
                //Toast.makeText(this, url, Toast.LENGTH_LONG).show();

                //String err = "ERROR";

                //try {


                //    doc = new GetDataFromUrl(this).execute(url).get();
                //} catch (InterruptedException | ExecutionException e ) {
                //    tv2.setText(err.concat(e.getMessage()));
                //    e.printStackTrace();
                //}
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
            //break;
        //}

    }

    @Override
    public void onTaskComplete(String result) {

           runOnUiThread(new Runnable() {
               @Override
               public void run() {
                 display.setText(doc.text());
               }
           });

//        Element link = doc.select("body > h1").first();
//        String content =link.text();
//
//        link = doc.select("body > p").first();
//        content +="\n"+link.text();
//        tv2.setText(content);
    }
 }
