package com.masterkpkit.anhtam.kpkit.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.masterkpkit.anhtam.kpkit.network.Connectivity;
import com.masterkpkit.anhtam.kpkit.network.INetworkChange;
import com.masterkpkit.anhtam.kpkit.network.NetworkReceiver;

public class BaseActivity extends AppCompatActivity implements INetworkChange {

    private boolean isRegisteredBroadcast = false;
    private NetworkReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receiver = new NetworkReceiver(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final IntentFilter filters = new IntentFilter();
        filters.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filters.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filters.addAction("android.net.wifi.STATE_CHANGE");
        if (!isRegisteredBroadcast) {
            registerReceiver(receiver, filters);
            isRegisteredBroadcast = true;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isRegisteredBroadcast) {
            unregisterReceiver(receiver);
            isRegisteredBroadcast = false;
        }
    }

    @Override
    public void onNetworkchange(final boolean isConnectted, final int typeNetwork) {
        if (!Connectivity.isConnected(this)) {

        }

    }
}
