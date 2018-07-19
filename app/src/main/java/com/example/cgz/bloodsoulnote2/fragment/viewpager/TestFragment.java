package com.example.cgz.bloodsoulnote2.fragment.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cgz.bloodsoulnote2.R;

import static com.example.cgz.bloodsoulnote2.ipc.messenger.MessengerService.TAG;

public class TestFragment extends Fragment {

    private static final String FLAG = "flag";

    private boolean isCreate;
    private boolean isHasLaodOnce;

    public static TestFragment newInstance(String flag) {
        TestFragment fragment = new TestFragment();
        if (!TextUtils.isEmpty(flag)) {
            Bundle bundle = new Bundle();
            bundle.putString(FLAG, flag);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        log("   1-->onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
        log("    2-->onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        log("    3-->onCreateView");
        return inflater.inflate(R.layout.layout_test_fragment,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        log("   4-->onActivityCreated");
        load();
    }

    @Override
    public void onStart() {
        super.onStart();
        log("   5-->onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        log("   6-->onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        log("   7-->onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        log("   8-->onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        log("   9-->onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        log("   10-->onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        log("   11-->onDetach");
    }

    private void log (String methodName){
        Bundle bundle = getArguments();
        String flag = bundle.getString(FLAG);
        if (TextUtils.isEmpty(flag)) {
            flag = "unknown";
        }
        Log.e(flag,"-------->"+methodName);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        log(" setUserVisibleHint : " + isVisibleToUser);
        load();
    }

    private void load() {
        if (isCreate && getUserVisibleHint() && !isHasLaodOnce){
            log("开始进行网络请求了");
            isCreate = false;
            isHasLaodOnce = true;
        }
    }

}
