package com.example.assignment1ps112

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.assignment1ps112.api.URL_API
import com.example.assignment1ps112.data.User
import com.example.assignment1ps112.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private fun initComponents() {
        val btn = findViewById<Button>(R.id.btnGetData)
        btn.setOnClickListener {
            this.getDetail()
        }
    }


    private fun getDetail() {
        val txtUserId = findViewById<EditText>(R.id.txtUserId)
        val txt1 = findViewById<EditText>(R.id.txtName)
        val txt2 = findViewById<EditText>(R.id.txtUsername)
        val txt3 = findViewById<EditText>(R.id.txtEmail)
        val txt4 = findViewById<EditText>(R.id.txtPhone)
        val txt5 = findViewById<EditText>(R.id.txtAdStreet)
        val txt6 = findViewById<EditText>(R.id.txtAdSuite)
        val txt7 = findViewById<EditText>(R.id.txtAdCity)
        val txt8 = findViewById<EditText>(R.id.txtAdZip)

        val retro = Retrofit.Builder()
            .baseUrl(URL_API)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val service = retro.create(adapter.Adapter::class.java)
        val userRequest = service.getUser(txtUserId.text.toString())

        userRequest.enqueue(object: Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()

                if (user == null) {
                    Toast.makeText(this@MainActivity, "No Such User Found!", Toast.LENGTH_LONG).show()
                }
                else {
                    txt1.setText(user.name)
                    txt2.setText(user.username)
                    txt3.setText(user.email)
                    txt4.setText(user.phone)
                    txt5.setText(user.address.street)
                    txt6.setText(user.address.suite)
                    txt7.setText(user.address.city)
                    txt8.setText(user.address.zipcode)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to Fetch.", Toast.LENGTH_LONG).show()
            }
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initComponents()
    }
}

