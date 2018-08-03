package com.fan.garbage.adpater;

import com.fan.garbage.page.Main_page;
import com.fan.garbage.page.Page2;
import com.fan.garbage.page.Page3;

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
        return 4;
    }

    @Override
    public Fragment getItem(int i) {
        if (pages[i] == null) {
            if (i == 0) pages[i] = new Main_page();
            if (i == 1) pages[i] = new Page2();
            if (i == 2) pages[i] = new Page3();
            if (i == 3) pages[i] = new Main_page();
        }
        return pages[i];
    }
}
