package com.banana.Nodang.Fragment;

import static com.banana.Nodang.Utils.LogDisplay.setLog;
import static com.banana.Nodang.Utils.LogDisplay.setToast;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.banana.Nodang.Pojo.ProductIngredientByProductName.MainI2790Response;
import com.banana.Nodang.Pojo.ProductNameByBarCode.MainC005Response;
import com.banana.Nodang.R;
import com.banana.Nodang.Retrofit.RetrofitAPI;
import com.banana.Nodang.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;

public class fResult extends Fragment {
    private NavController navController = null;
    private RetrofitAPI retrofitAPI;
    private ProgressBar progressBar;
    private TextView cont2, cont3, cont4, cont5, cont6, cont7, cont8, cont9;
    private SeekBar seekBar;
    private int nKCal = 0;

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
                setLog("fResult handleOnBackPressed");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    public fResult() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            nKCal = 0;
            navController = Navigation.findNavController(view);
            if (getArguments() != null) {
                setLog("Received Code : " + getArguments().getString("code"));
                getProductNameByBarCode(getArguments().getString("code"));
            }
            Button btnReScan = view.findViewById(R.id.btnReScan);
            btnReScan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (navController != null)
                        navController.popBackStack();
                }
            });
            cont2 = view.findViewById(R.id.cont2);
            cont3 = view.findViewById(R.id.cont3);
            cont4 = view.findViewById(R.id.cont4);
            cont5 = view.findViewById(R.id.cont5);
            cont6 = view.findViewById(R.id.cont6);
            cont7 = view.findViewById(R.id.cont7);
            cont8 = view.findViewById(R.id.cont8);
            cont9 = view.findViewById(R.id.cont9);
            seekBar = view.findViewById(R.id.seekBar);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    setLog(String.valueOf(progress));
                    if (progress == 0)
                        setProgressBarData((int) (nKCal / 2));
                    else if (progress == 1)
                        setProgressBarData(nKCal);
                    else if (progress == 2)
                        setProgressBarData((int) (nKCal * 1.5));
                    else if (progress == 3)
                        setProgressBarData((int) (nKCal * 2));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });


            progressBar = view.findViewById(R.id.progressBar);
            progressBar.setMin(1);
            progressBar.setMax(1000);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void setProgressBarData(int nProgress) {
        try {
            Handler mHandler = new Handler(Looper.getMainLooper());
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setProgress(nProgress);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getProductNameByBarCode(String strBarCode) {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        try {
            if (retrofitClient != null) {
                retrofitAPI = RetrofitClient.getRetrofitAPI();
                retrofitAPI.getProductNameByBarCode(strBarCode).enqueue(new Callback<MainC005Response>() {
                    @Override
                    public void onResponse(Call<MainC005Response> call, retrofit2.Response<MainC005Response> response) {
                        MainC005Response body = response.body();
                        if (body.getC005().getResult().getCode().equals("INFO-000")) {
                            setLog("제품명 : " + body.getC005().getRow().get(0).getPrdlstNm());
                            getProductIngredientByProductName(body.getC005().getRow().get(0).getPrdlstNm());
                        } else {
                            setToast(activity, "제품명이 검색되지 않았습니다.");
                            setLog("제품명이 검색되지 않았습니다.");
                        }

                    }

                    @Override
                    public void onFailure(Call<MainC005Response> call, Throwable t) {
                        setLog("Reponse Error : " + t.getMessage());
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getProductIngredientByProductName(String prdlstNm) {
        RetrofitClient retrofitClient = RetrofitClient.getInstance();
        try {
            if (retrofitClient != null) {
                retrofitAPI = RetrofitClient.getRetrofitAPI();
                retrofitAPI.getProductIngredientByProductName(prdlstNm).enqueue(new Callback<MainI2790Response>() {
                    @Override
                    public void onResponse(Call<MainI2790Response> call, retrofit2.Response<MainI2790Response> response) {
                        MainI2790Response body = response.body();
                        if (body.getI2790().getResult().getCode().equals("INFO-000")) {
                            Handler mHandler = new Handler(Looper.getMainLooper());
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    setLog("총 내용량 : " + body.getI2790().getRow().get(0).getServingSize());
                                    setLog("열량(kcal)(1회제공량당) : " + body.getI2790().getRow().get(0).getNutrCont1());
                                    if (!body.getI2790().getRow().get(0).getNutrCont1().isEmpty()) {
                                        setProgressBarData(Integer.parseInt(body.getI2790().getRow().get(0).getNutrCont1()));
                                        nKCal = Integer.parseInt(body.getI2790().getRow().get(0).getNutrCont1());
                                    }
                                    if (!body.getI2790().getRow().get(0).getNutrCont2().isEmpty()) {
                                        setLog("탄수화물(g)(1회제공량당) : " + body.getI2790().getRow().get(0).getNutrCont2());
                                        cont2.setText(body.getI2790().getRow().get(0).getNutrCont2());
                                    } else
                                        cont2.setText("자료 없음");
                                    if (!body.getI2790().getRow().get(0).getNutrCont3().isEmpty()) {
                                        setLog("단백질(g)(1회제공량당) : " + body.getI2790().getRow().get(0).getNutrCont3());
                                        cont3.setText(body.getI2790().getRow().get(0).getNutrCont3());
                                    } else
                                        cont3.setText("자료 없음");
                                    if (!body.getI2790().getRow().get(0).getNutrCont4().isEmpty()) {
                                        setLog("지방(g)(1회제공량당) : " + body.getI2790().getRow().get(0).getNutrCont4());
                                        cont4.setText(body.getI2790().getRow().get(0).getNutrCont4());
                                    } else
                                        cont4.setText("자료 없음");
                                    if (!body.getI2790().getRow().get(0).getNutrCont5().isEmpty()) {
                                        setLog("당류(g)(1회제공량당) : " + body.getI2790().getRow().get(0).getNutrCont5());
                                        cont5.setText(body.getI2790().getRow().get(0).getNutrCont5());
                                    } else
                                        cont5.setText("자료 없음");
                                    if (!body.getI2790().getRow().get(0).getNutrCont6().isEmpty()) {
                                        setLog("나트륨(mg)(1회제공량당) : " + body.getI2790().getRow().get(0).getNutrCont6());
                                        cont6.setText(body.getI2790().getRow().get(0).getNutrCont6());
                                    } else
                                        cont6.setText("자료 없음");
                                    if (!body.getI2790().getRow().get(0).getNutrCont7().isEmpty()) {
                                        setLog("콜레스테롤(mg)(1회제공량당) : " + body.getI2790().getRow().get(0).getNutrCont7());
                                        cont7.setText(body.getI2790().getRow().get(0).getNutrCont7());
                                    } else
                                        cont7.setText("자료 없음");
                                    if (!body.getI2790().getRow().get(0).getNutrCont8().isEmpty()) {
                                        setLog("포화지방산(g)(1회제공량당) : " + body.getI2790().getRow().get(0).getNutrCont8());
                                        cont8.setText(body.getI2790().getRow().get(0).getNutrCont8());
                                    } else
                                        cont8.setText("자료 없음");
                                    if (!body.getI2790().getRow().get(0).getNutrCont9().isEmpty()) {
                                        setLog("트랜스지방(g)(1회제공량당) : " + body.getI2790().getRow().get(0).getNutrCont9());
                                        cont9.setText(body.getI2790().getRow().get(0).getNutrCont9());
                                    } else
                                        cont9.setText("자료 없음");
                                }
                            });
                        } else {
                            setToast(activity, "제품 성분이 검색되지 않았습니다.");
                            setLog("제품 성분이 검색되지 않았습니다.");
                        }
                    }

                    @Override
                    public void onFailure(Call<MainI2790Response> call, Throwable t) {
                        setLog("Reponse Error : " + t.getMessage());
                    }
                });
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}