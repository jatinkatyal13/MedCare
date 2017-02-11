package com.care.med.medcare;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BloodDonation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SectionPagerAdapter sectionPagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(), this);
        ViewPager viewPager = (ViewPager)findViewById(R.id.container);

        //add the fragments
        BloodInformationFragment bloodInformationFragment = new BloodInformationFragment();
        bloodInformationFragment.addContext(this);
        sectionPagerAdapter.addTab(bloodInformationFragment, "Information");

        BroadcastRequestFragment broadcastRequestFragment = new BroadcastRequestFragment();
        broadcastRequestFragment.addContext(this);
        sectionPagerAdapter.addTab(broadcastRequestFragment, "Requests");

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        //iterating over tabs and setting custom tabs
        for (int i=0; i<tabLayout.getTabCount(); i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(sectionPagerAdapter.getTabView(i));
        }

        viewPager.setAdapter(sectionPagerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blood_donation, menu);
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

    public class SectionPagerAdapter extends FragmentPagerAdapter{

        List<Fragment> fragmentList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();

        Context context;

        public SectionPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        public void addTab(Fragment fragment, String title){
            fragmentList.add(fragment);
            titleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public View getTabView(int position){
            View tab = LayoutInflater.from(context).inflate(R.layout.custom_tab_black, null);
            ((TextView)tab.findViewById(R.id.custom_text)).setText(titleList.get(position));
            return tab;
        }

    }

}
