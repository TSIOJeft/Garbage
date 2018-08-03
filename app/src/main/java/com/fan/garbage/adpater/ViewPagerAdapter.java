package com.fan.garbage.adpater;

import com.fan.garbage.page.MainPageFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] pages = new Fragment[4];

    public ViewPagerAdapter(FragmentManager f) {
        super(f);
    }

    @Override
    public int getCount() {
        return pages.length;
    }

    @Override
    public Fragment getItem(int i) {
        if (pages[i] == null) {
            switch (i) {
//                case 0:
//                    pages[i] = new MainPageFragment();
//                    break;
//                case 1:
//                    pages[i] = new MainPageFragment();
//                    break;
//                case 2:
//                    pages[i] = new MainPageFragment();
//                    break;
//                case 3:
//                    pages[i] = new MainPageFragment();
//                    break;
                default:
                    pages[i] = new MainPageFragment();
            }
        }
        return pages[i];
    }
}
