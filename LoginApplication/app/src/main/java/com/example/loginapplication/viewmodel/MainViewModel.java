package com.example.loginapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginapplication.data.DataBase;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Boolean> _userLogin = new MutableLiveData<Boolean>();
    public LiveData<Boolean> userLogin = _userLogin;

    public void login(String email, String password) {
        DataBase banco = new DataBase();

        _userLogin.setValue(banco.procurarUsuario(email, password));
    }
}
