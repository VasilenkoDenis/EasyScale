package ua.com.ics_market.easyscale;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Denis on 20.02.2017.
 */

public class ScaleClass {

    private Context context;

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

}

