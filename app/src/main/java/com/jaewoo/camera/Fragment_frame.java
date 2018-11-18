package com.jaewoo.camera;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        final View view2 = inflater.inflate(R.layout.camera, container, false);

        view.findViewById(R.id.button1).setOnClickListener(this);
        view.findViewById(R.id.button2).setOnClickListener(this);
        view.findViewById(R.id.button3).setOnClickListener(this);
        view.findViewById(R.id.button4).setOnClickListener(this);
        view.findViewById(R.id.button5).setOnClickListener(this);
        view.findViewById(R.id.button6).setOnClickListener(this);
        view.findViewById(R.id.button7).setOnClickListener(this);
        view.findViewById(R.id.button8).setOnClickListener(this);

        imageView = (ImageView) view2.findViewById(R.id.imageVIew);

        return view;
    }

    @Override
    public void onClick(View v){

        try {
            switch (v.getId()) {
                case R.id.button1:
                    Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.person2);
                    imageView.setImageBitmap(bMap);
                    imageView.setImageResource(R.drawable.person2);
                    imageView.invalidate();
                    break;
                case R.id.button2:
                    imageView.setBackgroundResource(R.drawable.person3);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.person3));
                    imageView.setImageResource(R.drawable.person3);
                    imageView.setVisibility(View.VISIBLE);
                    imageView.invalidate();
                    break;
                case R.id.button3:
                    imageView.setBackgroundResource(R.drawable.person4);
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.person4));
                    imageView.setImageResource(R.drawable.person4);
                    imageView.setVisibility(View.VISIBLE);
                    break;
                case R.id.button4:
                    imageView.setImageResource(R.drawable.person5);
                    imageView.invalidate();
                    break;
                case R.id.button5:
                    imageView.setBackgroundResource(R.drawable.person6);
                    break;
                case R.id.button6:
                    imageView.setBackgroundResource(R.drawable.person7);
                    break;
                case R.id.button7:
                    imageView.setBackgroundResource(R.drawable.person8);
                    break;
                case R.id.button8:
                    imageView.setBackgroundResource(R.drawable.person9);
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
