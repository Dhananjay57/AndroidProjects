package com.binarywalllabs.adapter;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.binarywalllabs.ui.fragment.ChatAndNotificationFragment;
import com.binarywalllabs.ui.fragment.PlayListFragment;
import com.google.api.services.youtube.YouTube;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    @NonNull
    private final String[] pageTitle;
    private YouTube mYoutubeDataApi;
    @NonNull
    private final String[] playlistIds;


    public ViewPagerAdapter(FragmentManager fm, @NonNull String[] pageTitle , YouTube youTubeDataApi, String[] playlistIds) {
        super(fm);
        this.pageTitle = pageTitle;
        this.mYoutubeDataApi = youTubeDataApi;
        this.playlistIds = playlistIds;

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = PlayListFragment.newInstance(mYoutubeDataApi , playlistIds);
                break;
            case 1:
                fragment = ChatAndNotificationFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return pageTitle.length;
    }


}

