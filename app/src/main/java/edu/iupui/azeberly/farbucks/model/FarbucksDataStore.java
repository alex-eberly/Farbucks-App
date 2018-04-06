package edu.iupui.azeberly.farbucks.model;

import android.app.Application;

import java.util.List;

import edu.iupui.azeberly.farbucks.FarbucksApp;

/**
 * Created by Alex on 3/21/2018.
 */

public class FarbucksDataStore {

    // CREATE A STATIC VARIABLE THAT HOLDS A REFERENCE TO THE OBJECTS OF THIS CLASS
    private static FarbucksDataStore sFarbucksDataStore;

    private LocationDao mLocationDao;

    private MenuItemDao mMenuItemDao;

    private DaoSession mDaoSession;


    // CONSTRUCTOR
    private FarbucksDataStore(Application context) {
        mDaoSession = ((FarbucksApp) context).getDaoSession();
        mLocationDao = mDaoSession.getLocationDao();
        mMenuItemDao = mDaoSession.getMenuItemDao();
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

    public List<MenuItem> getMenuItems() {
        List<MenuItem> allMenuItems = mMenuItemDao.loadAll();
        return allMenuItems;
    }

}