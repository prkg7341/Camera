package com.jaewoo.camera;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Fragment_frame extends Fragment implements View.OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_frame, container, false);

        view.findViewById(R.id.button1).setOnClickListener(this);
        view.findViewById(R.id.button2).setOnClickListener(this);
        view.findViewById(R.id.button3).setOnClickListener(this);
        view.findViewById(R.id.button4).setOnClickListener(this);
        view.findViewById(R.id.button5).setOnClickListener(this);
        view.findViewById(R.id.button6).setOnClickListener(this);
        view.findViewById(R.id.button7).setOnClickListener(this);
        view.findViewById(R.id.button8).setOnClickListener(this);
        view.findViewById(R.id.button9).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v){

        try {
            switch (v.getId()) {
                case R.id.button1:
                    MainActivity.camera.setImage(R.drawable.person01);
                    break;
                case R.id.button2:
                    MainActivity.camera.setImage(R.drawable.person02);
                    break;
                case R.id.button3:
                    MainActivity.camera.setImage(R.drawable.person03);
                    break;
                case R.id.button4:
                    MainActivity.camera.setImage(R.drawable.person04);
                    break;
                case R.id.button5:
                    MainActivity.camera.setImage(R.drawable.person05);
                    break;
                case R.id.button6:
                    MainActivity.camera.setImage(R.drawable.person06);
                    break;
                case R.id.button7:
                    MainActivity.camera.setImage(R.drawable.person07);
                    break;
                case R.id.button8:
                    MainActivity.camera.setImage(R.drawable.person08);
                    break;
                case R.id.button9:
                    MainActivity.camera.setImage(R.drawable.person09);
                    break;
            }
        } catch (NullPointerException e){
            Toast.makeText(getActivity(), "Null!", Toast.LENGTH_SHORT).show();
        }
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, new Fragment_btn())
                .commit();
    }
}


