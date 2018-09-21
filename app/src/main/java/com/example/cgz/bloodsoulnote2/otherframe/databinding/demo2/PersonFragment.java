package com.example.cgz.bloodsoulnote2.otherframe.databinding.demo2;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cgz.bloodsoulnote2.R;

public class PersonFragment extends Fragment {

    public static PersonFragment newInstance() {
        PersonFragment fragment = new PersonFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", "value");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, R.layout.databinding_fragment, container, false);
        PersonViewModel vm = new PersonViewModel(getActivity(), binding);
        return binding.getRoot();
    }
}
