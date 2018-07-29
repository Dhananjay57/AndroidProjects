package com.binarywalllabs.socialintegration;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.binarywalllabs.socialintegration.managers.SharedPreferenceManager;
import com.binarywalllabs.socialintegration.model.UserModel;
import com.binarywalllabs.ui.activity.LoginActivity;
import com.binarywalllabs.ui.activity.YouTubePlayerActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.user_imageview)
    SimpleDraweeView simpleDraweeView;
    @BindView(R.id.name_textview)
    TextView nameTextView;
    @BindView(R.id.email_textview)
    TextView emailTextView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.btn_player)
    Button player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        UserModel userModel = getUserModelFromIntent();
        if(userModel!=null)
            setDataOnNavigationView(userModel);

    }

    private void setDataOnNavigationView(UserModel userModel) {
        if (navigationView != null) {
            setupDrawerContent(userModel);
        }

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            case R.id.nav_sign_out:
                                drawerLayout.closeDrawers();
                                SharedPreferenceManager.getSharedInstance().clearAllPreferences();
                                startLoginActivity();
                                return true;
                            default:
                                return true;
                        }
                    }
                });
    }

    private void setupDrawerContent(UserModel userModel) {
        View headerView = navigationView.getHeaderView(0);

        simpleDraweeView.setImageURI(Uri.parse(userModel.profilePic));
        nameTextView.setText(userModel.userName);
        emailTextView.setText(userModel.userEmail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_another, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSignOut(View view) {
        finish();
    }

    private UserModel getUserModelFromIntent()
    {
        Intent intent = getIntent();
        return intent.getParcelableExtra(UserModel.class.getSimpleName());
    }

    private void startLoginActivity()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    @OnClick(R.id.btn_player)
    public void setPlayer(){
        Intent intent = new Intent(this, YouTubePlayerActivity.class);
        startActivity(intent);
    }
}
