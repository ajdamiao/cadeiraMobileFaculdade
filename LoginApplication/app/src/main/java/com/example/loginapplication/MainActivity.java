package com.example.loginapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.loginapplication.data.DataBase;
import com.example.loginapplication.databinding.ActivityMainBinding;
import com.example.loginapplication.model.User;
import com.example.loginapplication.view.HomeActivity;
import com.example.loginapplication.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.btnSignInApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.login(binding.txtEmail.getText().toString(), binding.txtPass.getText().toString());
                observerLoginStatus();
            }
        });
    }

    private void observerLoginStatus() {
        viewModel.userLogin.observe(this, status -> {
            if(status) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Erro ao fazer login", Toast.LENGTH_LONG).show();
            }
        });
    }
}