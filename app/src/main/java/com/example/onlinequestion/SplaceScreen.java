package com.example.onlinequestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.onlinequestion.databinding.ActivitySplaceScreenBinding;

public class SplaceScreen extends AppCompatActivity {

    ActivitySplaceScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplaceScreenBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);


        Handler handler=new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(this,MainActivity.class));
        },3000);
    }
}