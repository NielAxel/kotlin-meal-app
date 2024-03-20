package com.example.uasppb.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.uasppb.MainActivity
import com.example.uasppb.R

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_DELAY = 3000L // Delay waktu tampilan splash screen (dalam milidetik)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Menutup SplashScreenActivity agar tidak bisa kembali ke splash screen dengan tombol back
        }, SPLASH_DELAY)
    }
}