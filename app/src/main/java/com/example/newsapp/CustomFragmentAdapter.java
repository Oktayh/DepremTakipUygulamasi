package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CustomFragmentAdapter extends FragmentPagerAdapter {

    public CustomFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new FirstFragment();
        }
        return new SecondFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return "Deprem Listesi";
        }
        else
        return "Son Deprem";
    }

    @Override
    public int getCount() {
        return 2;
    }

}
