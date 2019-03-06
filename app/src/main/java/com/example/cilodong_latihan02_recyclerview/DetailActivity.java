package com.example.cilodong_latihan02_recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    EditText d_ed_nama, d_ed_kelas;
    Button bt_ubah, bt_hapus;

    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //ambil bundle
        Bundle b = null;
        b = this.getIntent().getExtras();

        d_ed_nama = (EditText) findViewById(R.id.d_edNama);
        d_ed_kelas = (EditText) findViewById(R.id.d_edKelas);

        //taruh bundle disini
        final int id = b.getInt("b_id", 0);
        d_ed_nama.setText(b.getString("b_nama"));
        d_ed_kelas.setText(b.getString("b_kelas"));

        bt_ubah = (Button) findViewById(R.id.btUbah);
        bt_hapus = (Button) findViewById(R.id.btHapus);

        bt_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.db.siswaDao().update(
                        new Siswa(
                                id,
                                d_ed_nama.getText().toString(),
                                d_ed_kelas.getText().toString()
                        )
                );

                Toast.makeText(getApplicationContext(),
                                "Data tersimpan",
                                Toast.LENGTH_SHORT).show();

                finish(); //tutup activity

            }
        });

        bt_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.db.siswaDao().delete(
                        new Siswa(
                                id,
                                d_ed_nama.getText().toString(),
                                d_ed_kelas.getText().toString()
                        )
                );

                Toast.makeText(getApplicationContext(),
                        "Data dihapus",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
