package com.example.adriana.piggybank_moviles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ActivityGastos extends AppCompatActivity {

    private static final int TOTAL_PAGES = 3;
    public FragmentGastosToday fragmentGastosToday;
    public FragmentGastosWeek fragmentGastosWeek;
    public FragmentGastosMonth fragmentGastosMonth;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
      //  setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityGastos.this, ActivityNuevoGasto.class);
                startActivity(intent);
            }
        });

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    if( fragmentGastosToday== null){
                        fragmentGastosToday = new FragmentGastosToday();
                    }
                    return fragmentGastosToday;
                case 1:
                    if( fragmentGastosWeek== null){
                        fragmentGastosWeek = new FragmentGastosWeek();
                    }
                    return fragmentGastosWeek;
                case 2:
                    if( fragmentGastosMonth== null){
                        fragmentGastosMonth = new FragmentGastosMonth();
                    }
                    return fragmentGastosMonth;
                default:
                    return new FragmentGastosToday();

            }
        }

            @Override
            public int getCount () {
                return TOTAL_PAGES;
            }

        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase();
                case 1:
                    return getString(R.string.title_section2).toUpperCase();
                case 2:
                    return getString(R.string.title_section3).toUpperCase();
            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logOut:
                Intent intent2= new Intent(ActivityGastos.this,ActivityMain.class); startActivity(intent2);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



