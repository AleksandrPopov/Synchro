package com.aleksandrpopov.syncro.wrappers;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Objects;

public class ScanDevices extends AsyncTask<Void, Void, String> {

    Context context;
    ArrayList<String> arrIp = new ArrayList<>();
    private ProgressBar progressBar;

    public ScanDevices(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressBar = new ProgressBar(context);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Log.d("qwerty", "Let's sniff the network");

        try {
            if (context != null) {

                WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                int ipAddress = wm.getConnectionInfo().getIpAddress();

                String ipString = Formatter.formatIpAddress(ipAddress);
                String prefix = ipString.substring(0, ipString.lastIndexOf(".") + 1);

                for (int i = 0; i < 255; i++) {
                    String testIp = prefix + String.valueOf(i);
                    if (Objects.equals(testIp, ipString)) {
                        break;
                    }
                    else {
                        InetAddress address = InetAddress.getByName(testIp);
                        boolean reachable = address.isReachable(100);
                        String hostName = address.getCanonicalHostName();
                            if (reachable) {
                                Log.i("qwerty", "Host: " + String.valueOf(hostName) + "(" + prefix + ")(( is reachable!");
                                arrIp.add(hostName);
                             }
                    }
                }
            }
        } catch (Throwable t) {
            Log.e("qwerty", "Well that's not good.", t);
        }
        return String.valueOf(arrIp);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressBar.setVisibility(View.GONE);

    }
}
