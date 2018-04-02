package com.example.drew.socketservertest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView ipText;
    TextView sendText;
    static TextView recText;
    TextView statusText;

    SocketServer socket;

    public static final Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            String aResponse = msg.getData().getString("client");
            recText.setText(aResponse);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ipText = (TextView) findViewById(R.id.ipText);
        sendText = (TextView) findViewById(R.id.sendText);
        recText = (TextView) findViewById(R.id.recText);
        statusText = (TextView) findViewById(R.id.statusText);
        Button sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(this);

        ipText.setText(getIpAddress());
        socket = new SocketServer();
        socket.setParent(this);
    }

    @Override
    public void onClick(View v) {
        if(statusText.getText().equals("Connected")) {
            String input = sendText.getText().toString();

            Message sendMsg = socket.
        }
    }

    public static String getIpAddress() {
        String ipAddress = "Unable to Fetch IP..";
        try {
            Enumeration en;
            en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface intf = (NetworkInterface)en.nextElement();
                for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = (InetAddress)enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()&&inetAddress instanceof Inet4Address) {
                        ipAddress=inetAddress.getHostAddress();
                        return ipAddress;
                    }
                }
            }
        } catch (SocketException ex) {
            //ex.printStackTrace();
        }
        return ipAddress;
    }
}
