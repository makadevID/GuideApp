package com.guideapp.ui.views.map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import com.guideapp.R;
import com.guideapp.ui.views.BaseActivity;

public class MapActivity extends BaseActivity {
    private static final String EXTRA_CITY = "id-city";
    private static final String EXTRA_CATEGORY = "id-category";
    private static final String EXTRA_SUB_CATEGORY = "id-sub-category";
    private static final String EXTRA_ID_TITLE = "id-title";

    private long mIdCity;
    private long mIdCategory;
    private int mIdTitle;
    private long[] mIdSubCategories;

    public static void navigate(Context context, Long idCity) {
        Intent intent = new Intent(context, MapActivity.class);
        intent.putExtra(EXTRA_CITY, idCity);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initExtra();
        setupToolbar();

        setupFragment(MapFragment.newInstance(mIdCity, mIdCategory, mIdSubCategories));
    }

    private void initExtra() {
        mIdCity = getIntent().getLongExtra(EXTRA_CITY, 0);
        mIdCategory = getIntent().getLongExtra(EXTRA_CATEGORY, 0);
        mIdTitle = getIntent().getIntExtra(EXTRA_ID_TITLE, 0);
        mIdSubCategories = getIntent().getLongArrayExtra(EXTRA_SUB_CATEGORY);
    }

    private void setupFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentFrame, fragment);
        transaction.commit();
    }

    private void setupToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.app_name_city);
        }

        if (mIdTitle > 0) {
            setTitle(mIdTitle);
        }
    }
}