package com.example.zaky.kasircantik;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by zaky on 04/11/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.numOfTabs=NumOfTabs;
    }



    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new MenuFragment();
            case 1:
                return new OrderListFragment();
            case 2:
                return new ReportFragment();
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
