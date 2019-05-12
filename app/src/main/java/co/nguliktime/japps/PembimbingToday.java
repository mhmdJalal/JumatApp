package co.nguliktime.japps;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

import co.nguliktime.japps.adapter.AbsenAdapter;
import co.nguliktime.japps.api.ApiClient;
import co.nguliktime.japps.api.ApiInterface;
import co.nguliktime.japps.model.getAbsen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembimbingToday extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<getAbsen> absens = new ArrayList<getAbsen>();
    private AbsenAdapter absenAdapter;
    Context context;
    ProgressDialog dialog;
    PrefManager prefManager;
    ApiInterface apiInterface;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembimbing_today);

        context = this;
        prefManager = new PrefManager(context);
        apiInterface = new ApiClient(context).getClient().create(ApiInterface.class);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Daftar Absensi hari ini");

        recyclerView = findViewById(R.id.recycleView);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView.setLayoutManager(layoutManager);
        dialog = new ProgressDialog(context);
        dialog.setMessage("Loading...");

        swipeRefreshLayout.setOnRefreshListener(this);
        showData();
    }

    private void showData() {
        swipeRefreshLayout.setRefreshing(true);
        dialog.show();
        apiInterface.getAbsenToday(prefManager.getKd_rayon())
                .enqueue(new Callback<getAbsen>() {
                    @Override
                    public void onResponse(Call<getAbsen> call, Response<getAbsen> response) {
                        dialog.dismiss();
                        if (response.isSuccessful()){
                            absens = new ArrayList<>(response.body().getResult_absen_rayon());
                            absenAdapter = new AbsenAdapter(absens);
                            recyclerView.setAdapter(absenAdapter);
                        }else{
                            Toast.makeText(context, "Error saat mencari", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<getAbsen> call, Throwable t) {
                        Toast.makeText(context, "Belum ada Absensi hari ini", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRefresh(){
        showData();
    }
}
