package ua.com.ics_market.easyscale;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Denis on 20.02.2017.
 */

public class ScaleClass {

    private Context context;
    private String url;
    private String result;

    // Constructor
    public ScaleClass(Context context){

        this.context = context;
    }

    public int getAccessPointInfo(){

        WifiManager wifimanager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcpInfo = wifimanager.getDhcpInfo();
        //WiFiInfo wifiInfo = wifimanager.getConnectionInfo();
        return dhcpInfo.gateway;
    }

    //Setter
    public void setURL(String url){

        this.url = url;
    }


    public void sendRequest (final VolleyCallback callback){

        RequestQueue queue = Volley.newRequestQueue(context);
        //Request a string response from the provided URL.

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                result = response;
                callback.onSuccess(result);
            }

        }, new Response.ErrorListener(){


            @Override
            public void onErrorResponse(VolleyError error) {
                result = error.getMessage();
                callback.onError(result);
            }
        });

        queue.add(stringRequest);

    }

    public interface VolleyCallback{

        void onSuccess(String result);
        void onError(String error);

    }


}

