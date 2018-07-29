package com.binarywalllabs.ui.activity;


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.binarywalllabs.socialintegration.HomeActivity;
import com.binarywalllabs.socialintegration.LoginFragment;
import com.binarywalllabs.socialintegration.R;
import com.binarywalllabs.socialintegration.managers.SharedPreferenceManager;
import com.binarywalllabs.socialintegration.model.UserModel;

public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserModel userModel = SharedPreferenceManager.getSharedInstance().getUserModelFromPreferences();
        if(userModel!=null) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(UserModel.class.getSimpleName(), userModel);
            startActivity(intent);
            finishAffinity();
        }
        else
        {
            if (findViewById(R.id.content_frame) != null) {

                if (savedInstanceState != null) {
                    return;
                }

                LoginFragment loginFragment = new LoginFragment();

                loginFragment.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction().add(R.id.content_frame, loginFragment).commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if(loginFragment!=null)
        {
            loginFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}
