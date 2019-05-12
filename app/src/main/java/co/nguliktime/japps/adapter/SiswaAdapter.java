package co.nguliktime.japps.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import co.nguliktime.japps.DetailAbsen;
import co.nguliktime.japps.DetailSiswa;
import co.nguliktime.japps.R;
import co.nguliktime.japps.model.getSiswa;

/**
 * Created by Muhamad Jalal on 05/06/2018.
 */

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.MyViewHolder>{
    ArrayList<getSiswa> listSiswa;

    public SiswaAdapter(ArrayList<getSiswa> listSiswa){
        super();
        this.listSiswa = listSiswa;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_siswa, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.nis.setText(listSiswa.get(position).getNis());
        holder.nama.setText(listSiswa.get(position).getNama());
        holder.rombel.setText(listSiswa.get(position).getRombel());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(), DetailSiswa.class);
                mIntent.putExtra("nis", listSiswa.get(position).getNis());
                mIntent.putExtra("nama", listSiswa.get(position).getNama());
                mIntent.putExtra("rombel", listSiswa.get(position).getRombel());
                mIntent.putExtra("rayon", listSiswa.get(position).getRayon());
                v.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSiswa.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nis, nama, rombel;

        public MyViewHolder(View itemView) {
            super(itemView);
            nis = (TextView) itemView.findViewById(R.id.lsNis);
            nama = (TextView) itemView.findViewById(R.id.lsNama);
            rombel = (TextView) itemView.findViewById(R.id.lsRombel);
        }
    }

}
