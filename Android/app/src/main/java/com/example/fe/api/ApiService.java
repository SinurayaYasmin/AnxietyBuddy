package com.example.fe.api;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Query;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import com.example.fe.model.Account;
import com.example.fe.model.Medicine;
import com.example.fe.response.Response;

public interface ApiService {

    //Account
    @POST("anxietyBuddy/account/register")
    Call<Account> registerAccount(@Body Account user);

    @POST("anxietyBuddy/account/login")
    Call<Response> loginAccount(@Body Account user);

    @GET("anxietyBuddy/account/:userID")
    Call<Response> getAccount(@Query ("userID") String userID);

    @PUT("anxietyBuddy/account/topUp/:userID")
    Call<Response> topUpAccount(@Query ("userID") String userID, @Body Account user);

    @PUT("anxietyBuddy/account/setPicture/:userID")
    Call<Response> setPictureAccount(@Query ("userID") String userID, @Body Account user);

    @PUT("anxietyBuddy/account/forgotPassword")
    Call<Response> forgotPasswordAccount(@Body Account user);


    //Consule


    //Medicine
    @GET("anxietyBuddy/getMedicine")
    Call<Medicine> getMedicine (@Body Medicine medicine);

    //Journal

    //Order
}


