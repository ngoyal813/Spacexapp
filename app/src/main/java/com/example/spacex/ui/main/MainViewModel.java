package com.example.spacex.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.spacex.model.crew_model;
import com.example.spacex.repository.crew_repository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public MainViewModel(@NonNull @NotNull Application application) {
        super(application);
        crew_repository = new crew_repository(application);
        getcrewdetails = crew_repository.getGetcrewdetails();
    }

    private crew_repository crew_repository;

    public LiveData<List<crew_model>> getGetcrewdetails() {
        return getcrewdetails;
    }

    private LiveData<List<crew_model>> getcrewdetails;

    public void insertcrew(List<crew_model> list){
        crew_repository.insertcrewdetails(list);
    }

    public void deletecrew(List<crew_model> list){
    }

    public void deleteall(){
        crew_repository.deleteall();
    }


}