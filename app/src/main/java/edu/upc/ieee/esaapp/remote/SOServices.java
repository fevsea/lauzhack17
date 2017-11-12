package edu.upc.ieee.esaapp.remote;

import edu.upc.ieee.esaapp.models.Mission;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by alejandro on 11/11/17.
 */

public interface SOServices {
        // Request method and URL specified in the annotation

        // Callback for the parsed response is the last parameter


        @GET("missions.json")
        Call<Mission[]> missionList();

        @GET("missions/{pk}")
        Call<Mission> getMission(@Path("pk") int pk);


}
