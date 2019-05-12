package co.nguliktime.japps.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Muhamad Jalal on 29/06/2018.
 */

public class getAbsen {
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
    @SerializedName("status")
    private String status;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("result_absen_rayon")
    private ArrayList<getAbsen> result_absen_rayon;

    public void setResult_absen_rayon(ArrayList<getAbsen> result_absen_rayon) {
        this.result_absen_rayon = result_absen_rayon;
    }

    public ArrayList<getAbsen> getResult_absen_rayon() {
        return result_absen_rayon;
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

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }

    public String getRayon() {
        return rayon;
    }

    public String getKd_rayon() {
        return kd_rayon;
    }

    public String getKd_mesjid() {
        return kd_mesjid;
    }

    public void setKd_mesjid(String kd_mesjid) {
        this.kd_mesjid = kd_mesjid;
    }

    public String getMesjid() {
        return mesjid;
    }

    public void setMesjid(String mesjid) {
        this.mesjid = mesjid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTanggal() {
        return tanggal;
    }

}
