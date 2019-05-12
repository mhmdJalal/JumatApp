package co.nguliktime.japps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import co.nguliktime.japps.api.ApiClient;
import co.nguliktime.japps.api.ApiInterface;

public class DetailAbsen extends AppCompatActivity {
    Toolbar toolbar;
    TextView tvNis, tvNama, tvRombel, tvRayon, tvMesjid, tvStatus, tvTanggal;
    String nis, nama, rombel, rayon, mesjid, status, tanggal;
    ApiInterface apiInterface;
    Context context;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_absen);

        tvNis = findViewById(R.id.tvNis);
        tvNama = findViewById(R.id.tvNama);
        tvRombel = findViewById(R.id.tvRombel);
        tvRayon = findViewById(R.id.tvRayon);
        tvStatus = findViewById(R.id.tvStatus);
        tvMesjid = findViewById(R.id.tvMesjid);
        tvTanggal = findViewById(R.id.tvTanggal);

        nis = getIntent().getStringExtra("nis");
        nama = getIntent().getStringExtra("nama");
        rombel = getIntent().getStringExtra("rombel");
        rayon = getIntent().getStringExtra("rayon");
        mesjid = getIntent().getStringExtra("mesjid");
        status = getIntent().getStringExtra("status");
        tanggal = getIntent().getStringExtra("tanggal");

        tvNis.setText(": " + nis);
        tvNama.setText(": " + nama);
        tvRombel.setText(": " + rombel);
        tvRayon.setText(": " + rayon);
        tvMesjid.setText(": " + mesjid);
        tvStatus.setText(": " + status);
        tvTanggal.setText(tanggal);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);

        context = this;

        apiInterface = new ApiClient(this).getClient().create(ApiInterface.class);
    }
}
