package co.nguliktime.japps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnPembimbing, btnPendamping;
    ImageView about;
    TextView tvtitle, tvSub, tvlogas;
    Typeface typeface;
    Context context;
    PrefManager prefManager;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        prefManager = new PrefManager(context);
        btnPembimbing = (Button)findViewById(R.id.btnPembimbing);
        btnPembimbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginPembimbing.class));
            }
        });

        btnPendamping = (Button)findViewById(R.id.btnPendamping);
        btnPendamping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, LoginPendamping.class );
                startActivity(a);
            }
        });

        about = (ImageView)findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, About.class));
            }
        });

        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), "font/roboto_regular.ttf");

        tvtitle = (TextView)findViewById(R.id.japps);
        tvtitle.setTypeface(typeface);

        tvSub = (TextView)findViewById(R.id.subTitle);
        tvSub.setTypeface(typeface);

        tvlogas = findViewById(R.id.logas);
        tvlogas.setTypeface(typeface);
    }

    @Override
    public  void onBackPressed(){
        showDialog();
    }

    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(R.string.app_name)
                .setMessage("Keluar dari aplikasi?")
                .setIcon(R.drawable.mosque)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
