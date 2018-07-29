package com.binarywalllabs.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.binarywalllabs.socialintegration.FontCache;
import com.binarywalllabs.socialintegration.R;
import com.binarywalllabs.adapter.ViewPagerAdapter;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;

import april.yun.JPagerSlidingTabStrip;
import april.yun.tabstyle.JTabStyle;

import static android.graphics.Typeface.BOLD;

public class YouTubePlayerActivity extends AppCompatActivity {
    private static final String[] YOUTUBE_PLAYLISTS = {
            "PL1D6nWQpbEZepmhpqFAIl78H0TMsmr_x3",
            "PLdIv5OxHpFo7kjRi6Ws5faP1-9BSlIo2T",
            "PL1D6nWQpbEZd5AOfWOwfn6KGxzdXv4n14",

    };
    private YouTube mYoutubeDataApi;
    private final GsonFactory mJsonFactory = new GsonFactory();
    private final HttpTransport mTransport = AndroidHttp.newCompatibleTransport();


    private ViewPager vpApproval;
    private JPagerSlidingTabStrip psts_approval;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typlayer);
        vpApproval = findViewById(R.id.vp_approval);
        psts_approval = findViewById(R.id.psts_approval);
        mYoutubeDataApi = new YouTube.Builder(mTransport, mJsonFactory, null)
                .setApplicationName(getResources().getString(R.string.app_name))
                .build();

        PagerAdapter adapter = new ViewPagerAdapter(
                getSupportFragmentManager(), new String[]{"PlayList", "Chat & Notifiaction"}, mYoutubeDataApi ,YOUTUBE_PLAYLISTS);

        vpApproval.setAdapter(adapter);
        psts_approval.getTabStyleDelegate()
                .setJTabStyle(JTabStyle.MOVESTYLE_DEFAULT)
                .setShouldExpand(true)
                .setFrameColor(R.color.colorPrimary)
                .setTextColor(R.color.colorPrimary)
                .setTabTextColor(R.color.text_grey)
                .setTabTypeface(FontCache.getTypeface(getString(R.string.sans_pro_semi_bold), this))
                .setTabTypefaceStyle(BOLD)
                .setDividerWidth(0)
                .setDividerColor(R.color.transparent)
                .setDividerPadding(0)
                .setUnderlineColor(R.color.colorPrimary)
                .setUnderlineHeight(getResources().getDimensionPixelSize(R.dimen.twodp))
                .setIndicatorColor(R.color.colorPrimary)
                .setIndicatorHeight(getResources().getDimensionPixelSize(R.dimen.twodp));
        psts_approval.bindViewPager(vpApproval);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
