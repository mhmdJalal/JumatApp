package co.nguliktime.japps.api;

import co.nguliktime.japps.model.getAbsen;
import co.nguliktime.japps.model.getSiswa;
import  okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Muhamad Jalal on 11/04/2018.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api.php?action=loginPembimbing")
    Call<ResponseBody> loginPembimbing(@Field("username") String username,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("api.php?action=loginPendamping")
    Call<ResponseBody> loginPendamping(@Field("username") String username,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("api.php?action=getSiswa")
    Call<getSiswa> getSiswa(@Field("kd_rayon") String kd_rayon);

    @FormUrlEncoded
    @POST("api.php?action=getAbsen")
    Call<getAbsen> getAbsen(@Field("kd_rayon") String kd_rayon);

    @FormUrlEncoded
    @POST("api.php?action=getAbsenToday")
    Call<getAbsen> getAbsenToday(@Field("kd_rayon") String kd_rayon);

    @FormUrlEncoded
    @POST("api.php?action=saveAbsen")
    Call<ResponseBody> simpanAbsen(@Field("nis") String nis,
                                   @Field("mesjid") String mesjid,
                                   @Field("status") String status);

}
