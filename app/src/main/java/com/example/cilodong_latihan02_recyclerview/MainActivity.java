package com.example.cilodong_latihan02_recyclerview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder alertDialog;
    LayoutInflater inflater;

    EditText edtNama;
    RadioGroup rGroup;
    RadioButton rbIk, rbSI, rbTI;
    TextView tvNama, tvKelas;

    ArrayList<Siswa> siswaArrayList  = new ArrayList<>();

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

                        showRecyclerView();
                        /*
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
                                .setContentTitle("Data berhasil ditambahkan")
                                .setContentText(edtNama.getText().toString() +" ditambah");

                        notificationManager.notify(notificationId, mBuilder.build());







                        //--------------------
                        tvNama.setText(edtNama.getText().toString());

                        //untuk kelas, kita hanya ambil yang dipilih saja
                        int selectedIndex = rGroup.getCheckedRadioButtonId();
                        final RadioButton radioButton = (RadioButton) diagView.findViewById(selectedIndex);
                        tvKelas.setText(radioButton.getText().toString());
                        //--------------------

                        //sementara aja

                        for (int i = 0; i < siswaArrayList.size() ; i++) {
                            Log.d("Tes nama ", siswaArrayList.get(i).getNama());
                            Log.d("Tes IK", siswaArrayList.get(i).getIK().toString());
                            Log.d("Tes SI", siswaArrayList.get(i).getSI().toString());
                            Log.d("Tes TI", siswaArrayList.get(i).getTI().toString());
                        }

                        Toast.makeText(getBaseContext(),"Data tersimpan", Toast.LENGTH_SHORT).show();
                        */
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

        showRecyclerView();
    }


    private void showRecyclerView(){
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_container);
        LinearLayoutManager llm =
                new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        RvAdapter rvAdapter = new RvAdapter(this);
        rvAdapter.setSiswaArrayList(siswaArrayList);
        rv.setAdapter(rvAdapter);
    }
}
