package co.nguliktime.japps.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import co.nguliktime.japps.adapter.SiswaAdapter;

/**
 * Created by Muhamad Jalal on 28/06/2018.
 */

public class getSiswa {
    @SerializedName("nis")
    private String nis;
    @SerializedName("nama")
    private String nama;
    @SerializedName("kd_rombel")
    private String kd_rombel;
    @SerializedName("rombel")
    private String rombel;
    @SerializedName("kd_rayon")
    private String kd_rayon;
    @SerializedName("rayon")
    private String rayon;
    @SerializedName("kd_mesjid")
    private String kd_mesjid;
    @SerializedName("mesjid")
    private String mesjid;
    @SerializedName("result_mesjid")
    private ArrayList<getSiswa> result_mesjid;
    @SerializedName("result_siswa_rayon")
    private ArrayList<getSiswa> result_siswa_rayon;

    public ArrayList<getSiswa> getResult_mesjid() {
        return result_mesjid;
    }

    public String getKd_mesjid() {
        return kd_mesjid;
    }

    public String getMesjid() {
        return mesjid;
    }

    public void setResult_siswa_rayon(ArrayList<getSiswa> result_siswa_rayon) {
        this.result_siswa_rayon = result_siswa_rayon;
    }

    public ArrayList<getSiswa> getResult_siswa_rayon() {
        return result_siswa_rayon;
    }

    public getSiswa(){}

    public getSiswa(String nis, String nama, String kd_rombel, String rombel, String kd_rayon, String rayon){
        this.nis = nis;
        this.nama = nama;
        this.kd_rombel = kd_rombel;
        this.rombel = rombel;
        this.kd_rayon = kd_rayon;
        this.rayon = rayon;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getNis() {
        return nis;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setKd_rombel(String kd_rombel) {
        this.kd_rombel = kd_rombel;
    }

    public String getKd_rombel() {
        return kd_rombel;
    }

    public void setRombel(String rombel) {
        this.rombel = rombel;
    }

    public String getRombel() {
        return rombel;
    }

    public void setKd_rayon(String kd_rayon) {
        this.kd_rayon = kd_rayon;
    }

    public String getKd_rayon() {
        return kd_rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getRayon() {
        return rayon;
    }
}
