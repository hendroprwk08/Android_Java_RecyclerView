package com.example.cilodong_latihan02_recyclerview;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder alertDialog;
    LayoutInflater inflater;

    EditText edtNama, edtKelas;

    List<Siswa> siswas;

    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //panggil database
        db = Room.databaseBuilder(
                getApplicationContext(),
                MyDatabase.class,
                "db-siswa")
                .allowMainThreadQueries()
                .build();

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
                edtKelas = (EditText) view.findViewById(R.id.edKelas); //<-----

                alertDialog.setPositiveButton("SIMPAN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //insert data
                        db.siswaDao().insertAll(
                            new Siswa(
                                edtNama.getText().toString(),
                                edtKelas.getText().toString()
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

        siswas = db.siswaDao().getAll(); //ambil data

        RvAdapter rvAdapter = new RvAdapter(this);
        rvAdapter.setSiswa(siswas);
        rv.setAdapter(rvAdapter);
    }
}
