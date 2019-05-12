package co.nguliktime.japps.api;

import android.content.Context;

import co.nguliktime.japps.PrefManager;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Muhamad Jalal on 11/04/2018.
 */

public class ApiClient {
    PrefManager prefManager;

    public ApiClient(Context context) {
        prefManager = new PrefManager(context);
    }

    public Retrofit retrofit = null ;
    public  Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://"+prefManager.getIpAddress()+"/apijapps/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
