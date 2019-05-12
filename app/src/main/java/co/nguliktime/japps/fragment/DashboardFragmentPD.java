package co.nguliktime.japps.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.nguliktime.japps.Absensi;
import co.nguliktime.japps.PrefManager;
import co.nguliktime.japps.R;
import co.nguliktime.japps.adapter.SiswaAdapter;
import co.nguliktime.japps.api.ApiClient;
import co.nguliktime.japps.api.ApiInterface;
import co.nguliktime.japps.model.getSiswa;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragmentPD extends Fragment{
    Typeface typeface;
    TextView textView, tvuser;
    PrefManager prefManager;
    Button btnMove;
    Context context;

    public DashboardFragmentPD(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.f_dashboard_pendamping, container, false);

        context = getContext();
        prefManager = new PrefManager(context);
        textView = v.findViewById(R.id.welcome);
        tvuser = (TextView) v.findViewById(R.id.user);
        btnMove = (Button) v.findViewById(R.id.btnMove);
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/roboto_regular.ttf");
        textView.setTypeface(typeface);
        tvuser.setTypeface(typeface);
        tvuser.setText(prefManager.getNameUser());
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Absensi.class));
            }
        });
        return v;
    }
}
