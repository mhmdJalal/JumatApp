package co.nguliktime.japps.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.nguliktime.japps.PembimbingAbsens;
import co.nguliktime.japps.PembimbingToday;
import co.nguliktime.japps.PrefManager;
import co.nguliktime.japps.R;
import co.nguliktime.japps.adapter.AbsenAdapter;
import co.nguliktime.japps.api.ApiClient;
import co.nguliktime.japps.api.ApiInterface;
import co.nguliktime.japps.model.getAbsen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KehadiranFragmentPD extends Fragment{
    Context context;
    PrefManager prefManager;
    Button btnToday, btnAll;
    TextView tvUser;
    Typeface typeface;

    public KehadiranFragmentPD() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.f_kehadiran_pendamping, container, false);
        context = getActivity();
        prefManager = new PrefManager(context);

        btnToday = v.findViewById(R.id.btnToday);
        btnAll = v.findViewById(R.id.btnAll);
        tvUser = v.findViewById(R.id.tvLihat);

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

        typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/roboto_regular.ttf");
        tvUser.setTypeface(typeface);

        return v;
    }
}
