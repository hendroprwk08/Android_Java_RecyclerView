package com.example.cilodong_latihan02_recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.CardViewHolder> {
    private List<Siswa> siswas;
    private Context context;

    //menginformasikan context pada adapter
    public RvAdapter(Context context) {
        this.context = context;
    }

    //ambil data siswa
    public List<Siswa> getSiswas() {
        return siswas;
    }

    //set data siswa kedalam list yang ada pada adapter
    public void setSiswa(List siswas){
        this.siswas = siswas;
    }

    @NonNull
    @Override
    public RvAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //baku: hanya ubah nama layout saja
        //1. menentukan layout
        View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.recyclerview_layout,
                            viewGroup,
                            false);
        CardViewHolder viewHolder = new CardViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapter.CardViewHolder cardViewHolder, int i) {
        //4. meletakkan array list pada recycler view
        final String nama, kelas;

        nama = getSiswas().get(i).getNama();
        kelas = getSiswas().get(i).getKelas();

        cardViewHolder.tvNama.setText(nama);
        cardViewHolder.tvKelas.setText(kelas);

        cardViewHolder.itemView.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,
                        nama + " - " + kelas + " di klik",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        //3. menghitung jumlah data pada array list
        return getSiswas().size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        //2. mendefinisikan objek yang ada pada layout recyclerview
        TextView tvNama, tvKelas;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            //definisikan
            tvNama = (TextView) itemView.findViewById(R.id.rv_tv_nama);
            tvKelas = (TextView) itemView.findViewById(R.id.rv_tv_kelas);
        }
    }
}
