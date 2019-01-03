package com.vyankatesh.expensetracker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.vyankatesh.expensetracker.database.DatabaseHelper;
import com.vyankatesh.expensetracker.database.Transactions;
import com.vyankatesh.expensetracker.fragment.MainTab1;
import com.vyankatesh.expensetracker.fragment.MainTab2;
import com.vyankatesh.expensetracker.fragment.MainTab3;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragment for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static int mTab;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private MainTab3 mMainTab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mTab = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @OnClick(R.id.fab)
    void onFABClick() {
        switch (mTab) {
            case 0:
                break;
            case 1:
                insertTransaction();
                break;
            case 2:
                insertCategory();
                break;
        }
    }

    private void insertTransaction() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View rootView = inflater.inflate(R.layout.add_transaction_dialog, null);
        builder.setView(rootView);
        builder.setTitle("Add New Transaction");
        EditText category = rootView.findViewById(R.id.category_ed);
        EditText date = rootView.findViewById(R.id.date_ed);
        EditText amount = rootView.findViewById(R.id.amount_ed);
        EditText note = rootView.findViewById(R.id.note_ed);
        builder.setPositiveButton("OKAY", (dialog, which) -> {
            Transactions transaction = new Transactions(category.getText().toString(),
                    date.getText().toString(), Integer.parseInt(amount.getText().toString()), note.getText().toString());
            new DatabaseHelper(getApplicationContext()).insertTransaction(transaction);
            Toast.makeText(getApplicationContext(), "Added Transaction Successfully", Toast.LENGTH_SHORT).show();
        });
        builder.create().show();
    }

    public void insertCategory() {
        new MaterialDialog.Builder(this)
                .title("Category Name")
                .content("Enter Name")
                .input("", null, (dialog, input) -> {
                    new DatabaseHelper(getApplicationContext()).insertRecord(String.valueOf(input));
                    mMainTab3.setData();
                })
                .show();
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

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return new MainTab1();
                case 1:
                    return new MainTab2();
                case 2:
                    mMainTab3 = new MainTab3();
                    return mMainTab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
