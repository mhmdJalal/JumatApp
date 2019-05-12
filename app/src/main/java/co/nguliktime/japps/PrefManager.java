package co.nguliktime.japps;

import android.content.Context;
import android.content.SharedPreferences;
/**
 * Created by Muhamad Jalal on 05/04/2018.
 */

public class PrefManager {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    private static final int PREF_MODE = 0;
    private static final String PREF_NAME = "Session_Manager";
    private static final String IS_FIRST_TIME = "isFirstTime";

    private static final String IS_LOGGED_IN = "isLoggedIn";

    private static final String ID_USER = "idUser";
    private static final String NAME_USER = "nameUser";
    private static final String RAYON = "rayon";
    private static final String KD_RAYON = "null";
    private static final String IP_ADDRESS = "ipAdress";

    public PrefManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PREF_MODE);
        editor = preferences.edit();
    }

    public  void setIsLoggedIn(Boolean isLoggedIn) {
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn);
        editor.commit();
    }

    public void setIdUser(String idUser) {
        editor.putString(ID_USER, idUser);
        editor.commit();
    }

    public  void setNameUser(String nameUser) {
        editor.putString(NAME_USER, nameUser);
        editor.commit();
    }

    public void setRayon(String rayon){
        editor.putString(RAYON, rayon);
        editor.commit();
    }

    public void setKd_rayon(String kd_rayon){
        editor.putString(KD_RAYON, kd_rayon);
        editor.commit();
    }

    public  void setIpAddress(String ipAddress) {
        editor.putString(IP_ADDRESS, ipAddress);
        editor.commit();
    }

    public Boolean getIsLoggedIn() {
        return preferences.getBoolean(IS_LOGGED_IN, false);
    }

    public String getRayon(){
        return preferences.getString(RAYON, RAYON);
    }

    public String getKd_rayon(){
        return preferences.getString(KD_RAYON, KD_RAYON);
    }

    public  Integer getIdUser() {
        return preferences.getInt(IS_LOGGED_IN, 0);
    }

    public String getNameUser() {
        return preferences.getString(NAME_USER, NAME_USER);
    }

    public String getIpAddress() {
        //IP Address diganti sesuai IP Address HPnya
        return preferences.getString(IP_ADDRESS, "192.168.43.44:81");
    }
}
