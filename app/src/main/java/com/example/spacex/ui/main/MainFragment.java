package com.example.spacex.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spacex.API.retrofit_instance;
import com.example.spacex.API.spacex_api;
import com.example.spacex.R;
import com.example.spacex.adapter.crew_adapter;
import com.example.spacex.dao.crew_dao;
import com.example.spacex.database.crew_database;
import com.example.spacex.database.crew_database_Impl;
import com.example.spacex.model.crew_model;
import com.example.spacex.repository.crew_repository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private RecyclerView recyclerView;
    private crew_adapter adapter;
    private List<crew_model> crew_List;
    private crew_repository crew_repository;
    private crew_database database ;
    private spacex_api api;
    private FloatingActionButton delete;
    private FloatingActionButton refresh;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        delete = view.findViewById(R.id.delete);
        refresh = view.findViewById(R.id.refresh);
        crew_repository = new crew_repository(getActivity().getApplication());
        crew_List = new ArrayList<>();
        adapter = new crew_adapter(crew_List,getContext());
        recyclerView = view.findViewById(R.id.recyclerView_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(adapter);
        networkrequest();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.deleteall();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                networkrequest();
            }
        });
        return view;
    }

    private void networkrequest() {
        api = retrofit_instance.getRetroClient().create(spacex_api.class);
        Call<List<crew_model>> call = api.getcrewdetails();
        call.enqueue(new Callback<List<crew_model>>(){
            @Override
            public void onResponse(Call<List<crew_model>> call, Response<List<crew_model>> response) {
                crew_repository.insertcrewdetails(response.body());
            }

            @Override
            public void onFailure(Call<List<crew_model>> call, Throwable t) {
                Log.d("main",t.toString());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getGetcrewdetails().observe(getViewLifecycleOwner(), new Observer<List<crew_model>>() {
            @Override
            public void onChanged(List<crew_model> crew_modelList) {
                crew_List = crew_modelList;
                adapter.setCrew_list(crew_modelList);
                Log.d("listc","onchanged:"+crew_modelList);
            }
        });
    }

}