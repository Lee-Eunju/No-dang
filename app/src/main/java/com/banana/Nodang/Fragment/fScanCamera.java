package com.banana.Nodang.Fragment;

import static com.banana.Nodang.Utils.LogDisplay.setLog;
import static com.banana.Nodang.Utils.LogDisplay.setToast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.banana.Nodang.R;
import com.google.zxing.client.android.Intents;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class fScanCamera extends Fragment {
    private NavController navController = null;

    private Context context;
    private Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof Activity)
            activity = (Activity) context;
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                setLog("fScanCamera handleOnBackPressed");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if (result.getContents() == null) {
                    Intent originalIntent = result.getOriginalIntent();
                    if (originalIntent == null) {
                        setToast(activity, "Cancelled scan");
                        setLog("Cancelled scan");

                    } else if (originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                        setLog("Cancelled scan due to missing camera permission");
                    }
                } else {
                    setLog("Scanned : " + result.getContents());
                    setToast(activity, "Scanned : " + result.getContents());
                    if (navController != null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("code", result.getContents());
                        navController.navigate(R.id.action_fScanCamera_to_fResult, bundle);
                    }
                }
            });

    public fScanCamera() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_camera, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        ScanOptions options = new ScanOptions();
        options.setOrientationLocked(false);
        options.setBeepEnabled(false);

        barcodeLauncher.launch(options);
    }
}