package com.care.med.medcare;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter mSectionsPagerAdapter;
        ViewPager mViewPager;
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), this);


        //adding the pages

        //first is the profile page
        ProfileFragmentMain profileFragmentMain = new ProfileFragmentMain();
        profileFragmentMain.addContext(Home.this);
        mSectionsPagerAdapter.addTab(profileFragmentMain, "Profile");

        //second is the home page
        HomeFragmentMain homeFragmentMain = new HomeFragmentMain();
        homeFragmentMain.addContext(Home.this);
        mSectionsPagerAdapter.addTab(homeFragmentMain, "Home");

        //third is the notification page
        NotificationFragmentMain notificationFragmentMain = new NotificationFragmentMain();
        notificationFragmentMain.addContext(Home.this);
        mSectionsPagerAdapter.addTab(notificationFragmentMain, "Notifications");

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager((ViewPager)findViewById(R.id.container));

        // Iterate over all tabs and set the custom view
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(mSectionsPagerAdapter.getTabView(i));
        }

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        List<String> tabTitles = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        Context context;

        public SectionsPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        public void addTab(Fragment fragment, String title){
            tabTitles.add(title);
            fragmentList.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return tabTitles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles.get(position);
        }

        public View getTabView(int position){
            View tab = LayoutInflater.from(Home.this).inflate(R.layout.custom_tab, null);
            ((TextView)tab.findViewById(R.id.custom_text)).setText(tabTitles.get(position));
            return tab;
        }
    }
}
