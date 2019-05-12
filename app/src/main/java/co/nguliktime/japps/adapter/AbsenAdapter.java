package co.nguliktime.japps.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import co.nguliktime.japps.DetailAbsen;
import co.nguliktime.japps.R;
import co.nguliktime.japps.model.getAbsen;

/**
 * Created by Muhamad Jalal on 29/06/2018.
 */

public class AbsenAdapter extends RecyclerView.Adapter<AbsenAdapter.MyViewHolder> {
    ArrayList<getAbsen> getAbsens;

    public AbsenAdapter(ArrayList<getAbsen> getAbsens){
        super();
        this.getAbsens = getAbsens;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_absen, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(AbsenAdapter.MyViewHolder holder, final int position) {
        holder.nis.setText(getAbsens.get(position).getNis());
        holder.nama.setText(getAbsens.get(position).getNama());
        holder.rombel.setText(getAbsens.get(position).getRombel());
        holder.status.setText(getAbsens.get(position).getStatus());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(v.getContext(), DetailAbsen.class);
                mIntent.putExtra("nis", getAbsens.get(position).getNis());
                mIntent.putExtra("nama", getAbsens.get(position).getNama());
                mIntent.putExtra("rombel", getAbsens.get(position).getRombel());
                mIntent.putExtra("rayon", getAbsens.get(position).getRayon());
                mIntent.putExtra("mesjid", getAbsens.get(position).getMesjid());
                mIntent.putExtra("status", getAbsens.get(position).getStatus());
                mIntent.putExtra("tanggal", getAbsens.get(position).getTanggal());
                v.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getAbsens.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nis, nama, rombel, status;

        public MyViewHolder(View itemView) {
            super(itemView);
            nis = (TextView) itemView.findViewById(R.id.lsNis);
            nama = (TextView) itemView.findViewById(R.id.lsNama);
            rombel = (TextView) itemView.findViewById(R.id.lsRombel);
            status = (TextView) itemView.findViewById(R.id.lsStatus);
        }
    }
}
