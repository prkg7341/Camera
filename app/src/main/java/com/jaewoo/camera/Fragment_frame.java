package com.jaewoo.camera;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_frame extends Fragment implements View.OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_frame, container, false);

        view.findViewById(R.id.button).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v){
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new Fragment_btn())
                .commit();
    }
}
