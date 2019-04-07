package com.iata.reifly;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

public class HomeScreen extends AppCompatActivity {
    private final int REQ_CODE = 11;
    private final String TAG_PROFILE = "profile";
    private final String  TAG_OFFERS = "offers";
    private final String TAG_ALERTS = "alerts";
    private String CURRENT_TAG = TAG_OFFERS;

    String[] titles = {"Offers", "Alerts", "Profile"};
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;

    //fragments
    private OffersFragment mOfferFragment;
    private ProfileFragment mProfileFragment;

    //ui elements
    TextView textView;
    BottomNavigationView mNavigationView;

    private Handler mHandler;
    int navItemIndex = 0;
    boolean shouldLoadHomeFragOnBackPress = false;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        mHandler = new Handler();
        mAuth = FirebaseAuth.getInstance();
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser()==null)
                {
                    startActivity(new Intent(HomeScreen.this, SignIn.class));
                }
            }
        };

        setupToolbar();
        setUpNavigationView();
        loadHomeFragment();
        getDataFromCalendarTable();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(titles[0]);
    }

    private void setUpNavigationView() {
        mNavigationView = findViewById(R.id.navigationView);
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_item_offers:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_OFFERS;
                        break;
                    case R.id.nav_item_alerts:
                            navItemIndex = 1;
                            CURRENT_TAG = TAG_ALERTS;
                        break;
                    case R.id.nav_item_profile:
                            navItemIndex = 2;
                            CURRENT_TAG = TAG_PROFILE;
                        break;
                    default:
                        navItemIndex = 0;
                }
                menuItem.setChecked(!menuItem.isChecked());
                uncheckAllMenuItems(mNavigationView);
                menuItem.setChecked(true);
                loadHomeFragment();
                return true;
            }
        });
    }

    private void setToolbarTitle() {
        if (navItemIndex >= 0) {
            getSupportActionBar().setTitle(titles[navItemIndex]);
        }
    }

    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        if (navItemIndex >= 0) {
            setToolbarTitle();
        }

        try {
            Runnable mPendingRunnable = new Runnable() {
                @Override
                public void run() {
                    // update the main content by replacing fragments
                    try {
                        Fragment fragment = getHomeFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.containerView, fragment, "");
                        fragmentTransaction.commit();
                    } catch (IllegalStateException e) {
                        loadHomeFragment();
                    }
                }
            };

            if (mPendingRunnable != null) {
                mHandler.post(mPendingRunnable);
            }
        } catch (IllegalStateException e) {}
        // refresh toolbar menu
        //  invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                shouldLoadHomeFragOnBackPress = false;
                if (mOfferFragment == null) {
                    return mOfferFragment =  new OffersFragment();
                }
                return mOfferFragment;
            case 1:
                shouldLoadHomeFragOnBackPress = true;
                if (mOfferFragment == null) {
                    return mOfferFragment =  new OffersFragment();
                }
                return mOfferFragment;
            case 2:
                shouldLoadHomeFragOnBackPress = true;
                if (mProfileFragment == null) {
                    return mProfileFragment =  new ProfileFragment();
                }
                return mProfileFragment;
            default:
                shouldLoadHomeFragOnBackPress = false;
                if (mOfferFragment == null) {
                    return mOfferFragment =  new OffersFragment();
                }
                return mOfferFragment;
        }
    }

    // to uncheck all menu Items including Sub Menus
    private void uncheckAllMenuItems(BottomNavigationView navigationView) {
        final Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.hasSubMenu()) {
                SubMenu subMenu = item.getSubMenu();
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    subMenuItem.setChecked(false);
                }
            } else {
                item.setChecked(false);
            }
        }
    }

    public void getDataFromCalendarTable() {
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(Uri.parse("content://com.android.calendar/events"), new String[]{ "calendar_id", "title", "description", "dtstart", "dtend", "eventLocation" }, null, null, null);
        //Cursor cursor = cr.query(Uri.parse("content://calendar/calendars"), new String[]{ "_id", "name" }, null, null, null);
        String add = null;
        cursor.moveToFirst();
        String[] CalNames = new String[cursor.getCount()];
        int[] CalIds = new int[cursor.getCount()];
        for (int i = 0; i < 50; i++) {
            CalIds[i] = cursor.getInt(0);
            if (cursor.getString(1).startsWith("Flight")) {
                String name = "Event" + cursor.getInt(0) + ": \nTitle: " + cursor.getString(1) + "\nStart Date: " + new Date(cursor.getLong(3)) + "\nEnd Date : " + new Date(cursor.getLong(4)) + "\nLocation : " + cursor.getString(5);
             //   textView.append(name+"\n\n");
                System.out.println("this: " + name);
            }
            cursor.moveToNext();
        }
        cursor.close();

    }
}