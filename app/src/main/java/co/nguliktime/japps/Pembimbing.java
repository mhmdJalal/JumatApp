package co.nguliktime.japps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Muhamad Jalal on 11/04/2018.
 */

public class Pembimbing extends AppCompatActivity{
    Toolbar toolbar;
    Context context;
    PrefManager prefManager;
    AlertDialog alertDialog;
    Button btnToday, btnAll;
    TextView tvUser;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembimbing);
        context = this;
        prefManager = new PrefManager(context);
        btnToday = findViewById(R.id.btnToday);
        btnAll = findViewById(R.id.btnAll);
        tvUser = findViewById(R.id.user);

        btnToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, PembimbingToday.class));
            }
        });
        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, PembimbingAbsens.class));
            }
        });

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Rayon " + prefManager.getRayon());

        typeface = Typeface.createFromAsset(getApplication().getAssets(), "font/roboto_regular.ttf");
        tvUser.setTypeface(typeface);
        tvUser.setText(prefManager.getNameUser());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout){
            showDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public  void onBackPressed(){
        showDialog();
    }

    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(R.string.app_name)
                .setMessage("Yakin ingin logout?")
                .setIcon(R.drawable.mosque)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(context, LoginPembimbing.class));
                        finish();
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
