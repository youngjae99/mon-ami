package com.dlab.monami;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView btn_logout;
    private ImageView profile;
    private TextView txt_username;
    String user_name;
    String user_email;

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(intent.getExtras()!=null) {
            user_email = intent.getExtras().getString("email", "");
        }
        //이메일로 user_name 가져오는거 해야함





        viewPager = findViewById(R.id.view_pager); //탭별 화면 보이는 view pager
        tabLayout = findViewById(R.id.tab_layout); //탭바

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(fragment1, "");
        viewPagerAdapter.addFragment(fragment2, "");
        viewPagerAdapter.addFragment(fragment3, "");
        viewPagerAdapter.addFragment(fragment4, "");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_tab1);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tab2);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_tab3);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_tab4);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();
/*
        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Fragment1.newinstance();
                case 1:
                    return Fragment2.newinstance(user_email);
                case 2:
                    return Fragment3.newinstance(user_email);
                default:
                    return null;
            }
        }*/
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        /*
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }*/

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }
/*
    public void SetStatusBarColor(System.Drawing.Color color, bool darkStatusBarTint)
    {
        if (Build.VERSION.SdkInt < Android.OS.BuildVersionCodes.Lollipop)
            return;

        var activity = Plugin.CurrentActivity.CrossCurrentActivity.Current.Activity;
        var window = activity.Window;
        window.AddFlags(Android.Views.WindowManagerFlags.DrawsSystemBarBackgrounds);
        window.ClearFlags(Android.Views.WindowManagerFlags.TranslucentStatus);
        window.SetStatusBarColor(color.ToPlatformColor());
    }*/
}