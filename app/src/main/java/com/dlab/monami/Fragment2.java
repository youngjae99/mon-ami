package com.dlab.monami;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Fragment2 extends Fragment {

    private ViewPager viewPager2;
    private TabLayout tabLayout2;
    private ImageButton filterBtn;

    private ImageView profile;
    private TextView txt_username;
    String user_name;
    String user_email;

    private GalleryActivity galleryActivity;
    private BookmarkActivity bookmarkActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Fragment2","onCreateView");
        final View v = inflater.inflate(R.layout.fragment2, container, false);


        viewPager2 = v.findViewById(R.id.view_pager2); //탭별 화면 보이는 view pager
        tabLayout2 = v.findViewById(R.id.tab_layout2); //탭바


        galleryActivity = new GalleryActivity();
        bookmarkActivity = new BookmarkActivity();

        tabLayout2.setupWithViewPager(viewPager2);


        Fragment2.ViewPagerAdapter2 viewPagerAdapter2 = new Fragment2.ViewPagerAdapter2(getFragmentManager(), 0);
        viewPagerAdapter2.addFragment(galleryActivity, "전체보기");
        viewPagerAdapter2.addFragment(bookmarkActivity,"북마크");
        viewPager2.setAdapter(viewPagerAdapter2);

        return v;

    }

    private class ViewPagerAdapter2 extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        public ViewPagerAdapter2(@NonNull FragmentManager fm, int behavior) {
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


//    public Fragment2() {
//        // Required empty public constructor
//
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//



}
