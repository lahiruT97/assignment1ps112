package adapter


import com.example.assignment1ps112.data.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Adapter {
    @GET("/users/{id}")
    fun getUser(@Path("id") id: String): Call<User>
}