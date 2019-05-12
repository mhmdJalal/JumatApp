package co.nguliktime.japps;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.widget.EditText;

import co.nguliktime.japps.api.ApiClient;
import co.nguliktime.japps.api.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Muhamad Jalal on 11/04/2018.
 */

public class LoginPembimbing extends AppCompatActivity {
    Button btnMasuk, btnKembali;
    Typeface typeface;
    TextView tvtitle, tvback, tvsubtitle;
    EditText etUsername, etPassword;
    ProgressDialog dialog;
    ApiInterface mApiInterface;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pembimbing);

        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);

        btnMasuk = (Button)findViewById(R.id.bPembimbing);
        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

        btnKembali = (Button)findViewById(R.id.btnBack);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPembimbing.this, MainActivity.class));
                finish();
            }
        });

        dialog = new ProgressDialog(LoginPembimbing.this);
        dialog.setMessage("Loading...");

        mApiInterface = new ApiClient(LoginPembimbing.this).getClient().create(ApiInterface.class);
        prefManager = new PrefManager(LoginPembimbing.this);

        tvtitle = (TextView)findViewById(R.id.Japps);
        Typeface title = Typeface.createFromAsset(getApplicationContext().getAssets(), "font/lovestory.ttf");
        tvtitle.setTypeface(title);

        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "font/roboto_regular.ttf");
        tvback = findViewById(R.id.back);
        tvsubtitle = findViewById(R.id.subTitle);

        tvback.setTypeface(typeface);
        tvsubtitle.setTypeface(typeface);
    }

    private void validate() {
        if (etUsername.getText().toString().trim().equals(""))
            etUsername.requestFocus();
        else if (etPassword.getText().toString().trim().equals(""))
            etPassword.requestFocus();
        else {
            dialog.show();
            mApiInterface.loginPembimbing(etUsername.getText().toString(), etPassword.getText().toString())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                dialog.dismiss();
                                try  {
                                    JSONObject result = new JSONObject(response.body().string());
                                    if (result.getString("code").equals("1")) {
                                        prefManager.setNameUser(result.getString("nama_pembimbing"));
                                        prefManager.setKd_rayon(result.getString("kd_rayon"));
                                        prefManager.setRayon(result.getString("rayon"));
                                        Toast.makeText(LoginPembimbing.this, result.getString("msg"), Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(LoginPembimbing.this, Pembimbing.class));
                                        finish();
                                    } else {
                                        dialog.dismiss();
                                        Toast.makeText(LoginPembimbing.this, result.getString("msg"), Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    Toast.makeText(LoginPembimbing.this, "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            dialog.dismiss();
                            Toast.makeText(LoginPembimbing.this, "Error : "+t.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
}
