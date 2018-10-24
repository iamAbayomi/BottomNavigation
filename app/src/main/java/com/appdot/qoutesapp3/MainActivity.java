package com.appdot.qoutesapp3;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

public class MainActivity extends AppCompatActivity {

    //Credits AHBottomNavigation library for Android
    /*Copyright (c) 2017 Aurelien Hubert (http://github.com/aurelhubert).

            Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    Credits https://github.com/Suleiman19/Bottom-Navigation-Demo/blob/master/app/src/main/java/com/grafixartist/bottomnav/MainActivity.java
*/
    //Instance variables definition
    private final int[] colors = {R.color.bottomtab_0, R.color.bottomtab_1, R.color.bottomtab_2, R.color.bottomtab_3};

    private AHBottomNavigation bottomNavigation;
    private Toolbar toolbar;
    private NoSwipePager viewPager;
    private BottomBarAdapter pagerAdapter;

    //private DrawerLayout mDrawerLayout;

    private boolean notificationVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bottomtab_0)));
        //noinspection ConstantConditions
        getSupportActionBar().setTitle("QuoteApp");
        toolbar.setTitleTextColor(Color.WHITE);
        setupViewPager();

//        ActionBar actionbar = getSupportActionBar();
//        actionbar.setDisplayHomeAsUpEnabled(true);
//        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        setupBottomNavBehaviors();
        setupBottomNavStyle();

        //createFakeNotification();
        addBottomNavItem();
        bottomNavigation.setCurrentItem(0);

       /* mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        switch(menuItem.getItemId()){
                            case 1:
                                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bottomtab_0)));
                                break;
                        }
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });*/





        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {

            //final DummyFragment fragment = new DummyFragment();

            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
              //  fragment.updateColor(ContextCompat.getColor(MainActivity.this, colors[position]));
                if(!wasSelected)
                    viewPager.setCurrentItem(position);

                //remove notification badge
                int lastItemPos = bottomNavigation.getItemsCount() -1;
                if(notificationVisible && position == lastItemPos)
                    bottomNavigation.setNotification(new AHNotification(), lastItemPos);

                return true;
            }
        });

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if(position==0){
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bottomtab_0)));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(getResources().getColor(R.color.bottomtab_0));
                    }
                }
                if(position ==1){
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bottomtab_1)));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(getResources().getColor(R.color.bottomtab_1));
                    }
                }
                if(position ==2){
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bottomtab_2)));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(getResources().getColor(R.color.bottomtab_2));
                    }
                }

                if(position ==3) {
                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bottomtab_3)));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Window window = getWindow();
                        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                        window.setStatusBarColor(getResources().getColor(R.color.bottomtab_3));
                    }


                }
                    return true;
            }
        });
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                mDrawerLayout.openDrawer(GravityCompat.START);
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }



    public void setupBottomNavBehaviors() {
//        bottomNavigation.setBehaviorTranslationEnabled(false);

        /*
        Before enabling this. Change MainActivity theme to MyTheme.TranslucentNavigation in
        AndroidManifest.

        Warning: Toolbar Clipping might occur. Solve this by wrapping it in a LinearLayout with a top
        View of 24dp (status bar size) height.
         */
        bottomNavigation.setTranslucentNavigationEnabled(false);
    }

private void setupBottomNavStyle(){

    //Setting the very 1st item as home Screen.
    // bottomNavigation.setCurrentItem(0);
    bottomNavigation.setDefaultBackgroundColor(Color.WHITE);
    bottomNavigation.setAccentColor(fetchColor(R.color.bottomtab_0));
    bottomNavigation.setInactiveColor(fetchColor(R.color.bottomtab_item_resting));

    // Colors for selected (active) and non-selected items (in color reveal mode).
    bottomNavigation.setColoredModeColors(Color.WHITE,
            fetchColor(R.color.bottomtab_item_resting));

    //  Enables color Reveal effect
    bottomNavigation.setColored(true);

    //Displays item Title always( for selected and non-sselected items)
    bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
     bottomNavigation.setTranslucentNavigationEnabled(true);
    // bottomNavigation.setBehaviorTranslationEnabled(true);      
}



/*
    public void createFakeNotification(){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                AHNotification notification = new AHNotification.Builder()
                        .setText("1")
                        .setBackgroundColor(Color.YELLOW)
                        .setTextColor(Color.BLACK)
                        .build();
                bottomNavigation.setNotification(notification, bottomNavigation.getItemsCount()-1);
                notificationVisible =true;
            }
        }, 1000);
    }*/

    private void setupViewPager() {
        viewPager = (NoSwipePager) findViewById(R.id.viewpager);
        viewPager.setPagingEnabled(false);
        pagerAdapter = new BottomBarAdapter(getSupportFragmentManager());

        pagerAdapter.addFragments(createFragment(R.color.bottomtab_0));
        pagerAdapter.addFragments(createFragment(R.color.bottomtab_1));
        pagerAdapter.addFragments(createFragment(R.color.bottomtab_2));
        pagerAdapter.addFragments(createFragment(R.color.bottomtab_3));
        viewPager.setAdapter(pagerAdapter);
    }

    @NonNull
    private DummyFragment createFragment(int color) {
        DummyFragment fragment = new DummyFragment();
        fragment.setArguments(passFragmentArguments(fetchColor(color)));
        return fragment;
    }

    @NonNull
    private Bundle passFragmentArguments(int color) {
        Bundle bundle = new Bundle();
        bundle.putInt("color", color);
        return bundle;
    }


    private void addBottomNavItem() {

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_brightness_1_black_24dp, colors[0]);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_brightness_1_black_24dp, colors[1]);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem( R.string.tab_3, R.drawable.ic_brightness_1_black_24dp, colors[2]);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.drawable.ic_brightness_1_black_24dp, colors[3]);

        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
    }
    private int fetchColor(@ColorRes int color){
        return ContextCompat.getColor(this, color);

    }
}








 /*
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/


 /* private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };*/