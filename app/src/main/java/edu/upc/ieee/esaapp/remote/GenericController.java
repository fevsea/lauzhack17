package edu.upc.ieee.esaapp.remote;

import edu.upc.ieee.esaapp.models.Mission;
import edu.upc.ieee.esaapp.remote.ApiUtils;
import edu.upc.ieee.esaapp.remote.SOServices;
import retrofit2.Response;

/**
 * Created by alejandro on 11/11/17.
 */

import android.content.res.Resources;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class GenericController {


    private static GenericController instance = null;

    public GenericController(){
        mServices = ApiUtils.getSOService();
    }

    public static GenericController getInstance() {
        if(instance == null) {
            instance = new GenericController();
        }
        return instance;
    }

    private SOServices mServices;

    public List<Mission> getMissions() {
        List<Mission> l = new ArrayList<>();

        try {
            Response<Mission[]> res = mServices.missionList().execute();
            l.addAll(Arrays.asList(res.body()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < l.size(); i++) {
            Log.e("MAIN","i: "+i+" titlle: "+ l.get(i).getName());
            System.err.println("i: "+i+" titlle: "+ l.get(i).getName());
        }

        return l;
    }


    public Mission getMission(int id) throws Resources.NotFoundException {
        Mission us = null;
        try {
            Response<Mission> ret = mServices.getMission(id).execute();
            us = ret.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return us;
    }



}

