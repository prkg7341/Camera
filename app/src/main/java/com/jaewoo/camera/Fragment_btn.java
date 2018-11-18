package com.jaewoo.camera;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_btn extends Fragment implements View.OnClickListener{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_btn, container, false);

        view.findViewById(R.id.btn_capture).setOnClickListener(this);
        view.findViewById(R.id.btn_gallery).setOnClickListener(this);
        view.findViewById(R.id.btn_frame).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_capture:
                MainActivity.camera.takePicture();
                break;

            case R.id.btn_gallery:


                break;
            case R.id.btn_frame:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, new Fragment_frame())
                        .commit();
                break;
        }
    }
}
