package com.example.ass_restapi.services;

import com.example.ass_restapi.models.Fruit;
import com.example.ass_restapi.models.Response;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

// Nếu có CartItem model thì import thêm
// import com.example.ass_restapi.models.CartItem;

public interface ApiService {

    // ----------- FRUIT APIs (giữ nguyên của bạn) -----------
    @GET("fruits/list")
    Call<Response<ArrayList<Fruit>>> getListFruits();

    @DELETE("fruits/delete/{id}")
    Call<Response<Void>> deleteFruits(@Path("id") String id);

    @POST("fruits/add")
    Call<Response<Fruit>> addFruit(@Body Fruit fruit);

    @PUT("fruits/update/{id}")
    Call<Response<Fruit>> updateFruit(@Path("id") String id, @Body Fruit fruit);

    // ----------- CART APIs (ví dụ, nếu dùng API) -----------
    // Lưu ý: Nếu bạn thao tác giỏ hàng local thì KHÔNG cần các API bên dưới.
    // Nếu backend có route cho cart, thêm như sau:

    /*
    @GET("cart")
    Call<Response<ArrayList<CartItem>>> getCart();

    @POST("cart/add")
    Call<Response<CartItem>> addToCart(@Body CartItem cartItem);

    @PUT("cart/update/{id}")
    Call<Response<CartItem>> updateCartItem(@Path("id") String id, @Body CartItem cartItem);

    @DELETE("cart/delete/{id}")
    Call<Response<Void>> deleteCartItem(@Path("id") String id);

    @POST("cart/clear")
    Call<Response<Void>> clearCart();
    */

    // Nếu chỉ dùng giỏ hàng trên local, bạn KHÔNG cần các API trên.

}