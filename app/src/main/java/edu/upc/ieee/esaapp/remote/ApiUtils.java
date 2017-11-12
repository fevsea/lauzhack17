package edu.upc.ieee.esaapp.remote;

/**
 * Created by alejandro on 11/11/17.
 */


public class ApiUtils {

    public static final String BASE_URL = "http://ec2-18-194-144-183.eu-central-1.compute.amazonaws.com/";


    public static SOServices getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOServices.class);
    }
}