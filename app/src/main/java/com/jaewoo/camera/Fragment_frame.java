package com.jaewoo.camera;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class Fragment_frame extends Fragment implements View.OnClickListener {

    ImageView imageView;

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

        imageView = (ImageView) view.findViewById(R.id.imageVIew);

        return view;
    }

    @Override
    public void onClick(View v){

        try {
            switch (v.getId()) {
                case R.id.button1:
                    MainActivity.camera.setImage(R.drawable.person1);
                    break;
                case R.id.button2:
                    MainActivity.camera.setImage(R.drawable.person2);
                    break;
                case R.id.button3:
                    MainActivity.camera.setImage(R.drawable.person3);
                    break;
                case R.id.button4:
                    MainActivity.camera.setImage(R.drawable.person4);
                    break;
                case R.id.button5:
                    MainActivity.camera.setImage(R.drawable.person5);
                    break;
                case R.id.button6:
                    MainActivity.camera.setImage(R.drawable.person6);
                    break;
                case R.id.button7:
                    MainActivity.camera.setImage(R.drawable.person7);
                    break;
                case R.id.button8:
                    MainActivity.camera.setImage(R.drawable.person8);
                    break;
                case R.id.button9:
                    MainActivity.camera.setImage(R.drawable.person9);
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
