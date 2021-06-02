package com.example.spacex.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.spacex.dao.crew_dao;
import com.example.spacex.database.crew_database;
import com.example.spacex.model.crew_model;

import java.util.List;

public class crew_repository {

    private crew_database database;

    public LiveData<List<crew_model>> getGetcrewdetails() {
        return getcrewdetails;
    }

    public LiveData<List<crew_model>> getcrewdetails;
    private crew_dao dao;

    public crew_repository(Application application){
    database =crew_database.getInstance(application);
    dao = database.crew_dao();
    getcrewdetails = database.crew_dao().getcrewdetails();
    }

    public void insertcrewdetails(List<crew_model> crew_modelList){
    new InsertAsynctask(database).execute(crew_modelList);
    }

    static class InsertAsynctask extends AsyncTask<List<crew_model>,Void,Void>{
        private crew_dao crew_dao;
        InsertAsynctask(crew_database crew_database){
            crew_dao = crew_database.crew_dao();
        }
        @Override
        protected Void doInBackground(List<crew_model>... lists) {
            crew_dao.insert(lists[0]);
            return null;
        }
    }
    public  void deleteall(){
        new deleteallasynctask(dao).execute();
    }

    private static  class deleteallasynctask extends AsyncTask<Void,Void,Void> {
        private crew_dao crewDao;
        public deleteallasynctask(crew_dao dao) {
            crewDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            crewDao.deleteallcrew();
            return null;
        }
    }
}
