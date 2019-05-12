package co.nguliktime.japps;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import co.nguliktime.japps.api.ApiClient;
import co.nguliktime.japps.api.ApiInterface;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSiswa extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    TextView tvNis, tvNama, tvRombel, tvRayon;
    String nis, nama, rombel, rayon, status, tanggal;
    Button btnSimpan;
    ApiInterface apiInterface;
    ProgressDialog progressDialog;
    Context context;
    RadioGroup rgAbsen, rgMesjid;
    RadioButton rbStatus, rbMesjid, nurulamin, nuruliman, mujahidin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_siswa);

        tvNis = findViewById(R.id.tvNis);
        tvNama = findViewById(R.id.tvNama);
        tvRombel = findViewById(R.id.tvRombel);
        tvRayon = findViewById(R.id.tvRayon);
        btnSimpan = findViewById(R.id.btnSimpan);
        rgAbsen = (RadioGroup) findViewById(R.id.rgAbsen);
        rgMesjid = (RadioGroup) findViewById(R.id.rgMesjid);
        nuruliman = (RadioButton) findViewById(R.id.rbMesjid1);
        nurulamin = (RadioButton) findViewById(R.id.rbMesjid2);
        mujahidin = (RadioButton) findViewById(R.id.rbMesjid3);
        context = this;

        apiInterface = new ApiClient(context).getClient().create(ApiInterface.class);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");

        nis = getIntent().getStringExtra("nis");
        nama = getIntent().getStringExtra("nama");
        rombel = getIntent().getStringExtra("rombel");
        rayon = getIntent().getStringExtra("rayon");

        tvNis.setText(": " + nis);
        tvNama.setText(": " + nama);
        tvRombel.setText(": " + rombel);
        tvRayon.setText(": " + rayon);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Absen Siswa");

        btnSimpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSimpan){
            String mesjid = new String();
            progressDialog.show();
            progressDialog.setCancelable(false);
            int selectedStatus = rgAbsen.getCheckedRadioButtonId();
            rbStatus = (RadioButton) findViewById(selectedStatus);
            int selectedMosque = rgMesjid.getCheckedRadioButtonId();
            rbMesjid = (RadioButton) findViewById(selectedMosque);

            apiInterface.simpanAbsen(nis,
                    (String) rbMesjid.getHint(),
                    (String) rbStatus.getText())
            .enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    progressDialog.dismiss();
                    if (response.isSuccessful()){
                        try {
                            JSONObject result = new JSONObject(response.body().string());
                            if (result.getString("code").equals("2")){
                                Toast.makeText(context, result.getString("msg"), Toast.LENGTH_SHORT).show();
                            }else if(result.getString("code").equals("1")){
                                Toast.makeText(context, result.getString("msg"), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(DetailSiswa.this, getApplication().getClass()));
                            }else{
                                Toast.makeText(context, result.getString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(context, "Error JSONException : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        } catch (IOException e) {
                            Toast.makeText(context, "Error IOException : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(context, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
