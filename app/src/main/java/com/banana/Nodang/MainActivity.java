package com.banana.Nodang;

import static com.banana.Nodang.Utils.LogDisplay.setLog;
import static com.banana.Nodang.Utils.LogDisplay.setToast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null)
            navController = navHostFragment.getNavController();
    }

    long waitTime = 0L;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (removeFragment().equals("fMain")) {
            if (System.currentTimeMillis() - waitTime >= 2000) {
                waitTime = System.currentTimeMillis();
                setToast(MainActivity.this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다.");
            } else
                finish();
        } else
            navController.popBackStack();
    }

    public String removeFragment() {
        String strFragmentName = "";
        try {
            NavDestination navDestination = navController.getCurrentDestination();
            setLog("navDestination.getLabel() : " + navDestination.getLabel());
            strFragmentName = navDestination.getLabel().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strFragmentName;
    }
}