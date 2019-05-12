package co.nguliktime.japps;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import co.nguliktime.japps.adapter.SiswaAdapter;
import co.nguliktime.japps.api.ApiClient;
import co.nguliktime.japps.api.ApiInterface;
import co.nguliktime.japps.model.getSiswa;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Absensi extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private ArrayList<getSiswa> siswas = new ArrayList<getSiswa>();
    private SiswaAdapter siswaAdapter;
    Context context;
    ProgressDialog dialog;
    PrefManager prefManager;
    ApiInterface apiInterface;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);

        context = this;
        layoutManager = new LinearLayoutManager(context);
        dialog = new ProgressDialog(context);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        toolbar = findViewById(R.id.toolbar);
        recyclerView.setLayoutManager(layoutManager);
        dialog.setMessage("Loading...");

        apiInterface = new ApiClient(context).getClient().create(ApiInterface.class);
        prefManager = new PrefManager(context);
        swipeRefreshLayout.setOnRefreshListener(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Absen Siswa");

        showData();
    }

    private void showData() {
        swipeRefreshLayout.setRefreshing(true);
        dialog.show();
        apiInterface.getSiswa(prefManager.getKd_rayon())
                .enqueue(new Callback<getSiswa>() {
                    @Override
                    public void onResponse(Call<getSiswa> call, Response<getSiswa> response) {
                        dialog.dismiss();
                        if (response.isSuccessful()){
                            siswas = new ArrayList<>(response.body().getResult_siswa_rayon());
                            siswaAdapter = new SiswaAdapter(siswas);
                            recyclerView.setAdapter(siswaAdapter);
                        }else{
                            Toast.makeText(context, "Error saat mencari", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<getSiswa> call, Throwable t) {
                        Toast.makeText(context, "Tidak ada siswa", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRefresh() {
        showData();
    }
}
