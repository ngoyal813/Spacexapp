package com.example.spacex.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.spacex.model.crew_model;

import java.util.List;

@Dao
public interface crew_dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<crew_model> crew_list);

    @Query("SELECT * FROM crew_details")
    LiveData<List<crew_model>> getcrewdetails();

    @Query("DELETE FROM crew_details")
    void deleteallcrew();
}
