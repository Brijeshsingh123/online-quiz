package com.example.onlinequestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.onlinequestion.databinding.ActivityResultBinding;

import pl.droidsonroids.gif.GifDrawable;

public class Result extends AppCompatActivity {

    SharedPreferences preferences;
    ActivityResultBinding binding;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences=getApplicationContext().getSharedPreferences("loginpref", MODE_PRIVATE);
        editor=preferences.edit();
        binding=ActivityResultBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        ((GifDrawable) binding.gif.getDrawable()).start();
        binding.score.setText(""+preferences.getInt("correct",0));
        binding.tvwrong.setText(""+preferences.getInt("wrong",0));
        binding.tvquestion.setText(""+preferences.getInt("updated",0));
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Result.this,MainActivity.class));
            }
        });

    }
}