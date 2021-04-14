package com.example.navigationdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class DashBoardFragment extends Fragment {
    Button startBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.dashboard_fragment, container, false);
        startBtn = root.findViewById(R.id.startBtn);

        return root;
    }
//
//    public void performStart (View v) {
//            Toast.makeText(getActivity(), "Start btn clicked", Toast.LENGTH_LONG).show();
//    }


}
