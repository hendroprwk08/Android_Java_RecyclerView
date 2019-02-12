package com.example.cilodong_latihan02_recyclerview;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder alertDialog;
    LayoutInflater inflater;

    EditText edtNama;
    RadioGroup rGroup;
    RadioButton rbIk, rbSI, rbTI;

    ArrayList<Siswa> siswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        siswaArrayList = new ArrayList<>();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog = new AlertDialog.Builder(MainActivity.this);

                inflater = getLayoutInflater();
                view = inflater.inflate(R.layout.input_layout, null);

                final View diagView = view;

                alertDialog.setView(diagView);
                alertDialog.setCancelable(true);
                alertDialog.setIcon(R.mipmap.ic_launcher);
                alertDialog.setTitle("Biodata");

                //definisi objek
                edtNama = (EditText) view.findViewById(R.id.edNama);
                rGroup = (RadioGroup) view.findViewById(R.id.rbGroup);
                rbIk = (RadioButton) view.findViewById(R.id.rb_ik);
                rbSI = (RadioButton) view.findViewById(R.id.rb_si);
                rbTI = (RadioButton) view.findViewById(R.id.rb_ti);

                alertDialog.setPositiveButton("SIMPAN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Simpan kedalam pojo
                        siswaArrayList.add(
                                new Siswa(
                                        edtNama.getText().toString(),
                                        rbIk.isChecked(),
                                        rbTI.isChecked(),
                                        rbSI.isChecked()
                                )
                        );

                        NotificationManager notificationManager = (NotificationManager) getBaseContext().getSystemService(getBaseContext().NOTIFICATION_SERVICE);

                        int notificationId = 1;
                        String channelId = "channel-01";
                        String channelName = "Channel Name";
                        int importance = NotificationManager.IMPORTANCE_HIGH;

                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            NotificationChannel mChannel = new NotificationChannel(
                                    channelId, channelName, importance);
                            notificationManager.createNotificationChannel(mChannel);
                        }

                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getBaseContext(), channelId)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("title")
                                .setContentText("body");

                        notificationManager.notify(notificationId, mBuilder.build());

                        //Toast.makeText(getBaseContext(),"Data tersimpan", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alertDialog.show();

               // Snackbar.make(diagView, "Replace with your own action", Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
