package edu.iupui.azeberly.farbucks.model;

import android.app.Application;

import java.util.List;

import edu.iupui.azeberly.farbucks.FarbucksApp;

/**
 * Created by Alex on 3/21/2018.
 */

public class FarbucksDataStore {

    // CREATE A STATIC VARIABLE THAT HOLDS A REFERENCE TO THE ONE AND ONLY OBJECT OF THIS CLASS
    private static FarbucksDataStore sFarbucksDataStore;

    private LocationDao mLocationDao;

    private DaoSession mDaoSession;


    // CONSTRUCTOR
    private FarbucksDataStore(Application context) {
        mDaoSession = ((FarbucksApp) context).getDaoSession();
        mLocationDao = mDaoSession.getLocationDao();
    }


    // RETRIEVE THE SINGLE INSTANCE OF FarbucksDataStore
    public static FarbucksDataStore getInstance(Application context) {
        if (sFarbucksDataStore == null) {
            sFarbucksDataStore = new FarbucksDataStore(context);
        }
        return sFarbucksDataStore;
    }

    // RETURN A LIST OF ALL LOCATIONS IN THE DATABASE
    public List<Location> getLocations() {
        List<Location> allLocations = mLocationDao.loadAll();
        return allLocations;
    }

}