package com.example.spacex.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.spacex.dao.crew_dao;
import com.example.spacex.model.crew_model;

import org.jetbrains.annotations.NotNull;

@Database(entities = {crew_model.class},version = 1)
public abstract class crew_database extends RoomDatabase {

    private static final String DATABASE_NAME = "SPACEXDATABASE";
    private static volatile crew_database INSTANCE;

    public abstract crew_dao crew_dao();

    public static crew_database getInstance(Context context){
        if (INSTANCE == null){
            synchronized (crew_database.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context,crew_database.class,DATABASE_NAME)
                            .addCallback(callback).fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsynTask(INSTANCE);
        }
    };

    static class PopulateAsynTask extends AsyncTask<Void,Void,Void> {

        private crew_dao crew_dao;

        PopulateAsynTask(crew_database crew_database)
        {
            crew_dao = crew_database.crew_dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            crew_dao.deleteallcrew();
            return null;
        }
    }


}
