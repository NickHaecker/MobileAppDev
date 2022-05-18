package com.raysono.hfu.fridgepay.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WebService {

    @POST("v1/user")
    suspend fun signUp(@Body request: SignUpRequestDto): SignUpResponseDto

    @GET("v1/product")
    suspend fun getProducts(): List<ProductDto>

    @PUT("v1/cart/{id}")
    suspend fun addItem(@Path("id") id: String, @Body body: AddItemRequestDto)

    @GET("v1/cart/{id}")
    suspend fun getCartById(@Path("id") id: String): List<ShoppingCartItemDto>

    @GET("v1/user")
    suspend fun login(): LoginResponseDto

    @DELETE("v1/cart/{id}")
    suspend fun clearCart(@Path("id") id: String)

    companion object {
        const val BASE_URL = "http://10.0.2.2:8080/"
    }
}

@Serializable
data class LoginResponseDto(
    val cartId: String,
)

@Serializable
data class ShoppingCartItemDto(
    val id: String,
    val productId: String,
    val cartId: String,
)

@Serializable
data class AddItemRequestDto(
    val productId: String,
)

@Serializable
data class ProductDto(
    val id: String,
    val name: String,
    val description: String,
    @SerialName("icon")
    val imageUrl: String,
    val price: Double,
)

@Serializable
data class SignUpRequestDto(
    val userName: String,
    val password: String,
)

@Serializable
data class SignUpResponseDto(
    val cartId: String,
)
