package com.jaewoo.camera;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SubActivity extends AppCompatActivity {

    private int[] linears;
    private GalleryFling galleryFling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        linears = new int[]{
            R.layout.gallery_linear_1,
            R.layout.gallery_linear_1,
            R.layout.gallery_linear_1
        };

        galleryFling = (GalleryFling) findViewById(R.id.galleryFling);
        galleryFling.setAdapter(new GalleryAdapter(this));
        galleryFling.setSelection(3); // default view

        galleryFling.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(position<3){
                    galleryFling.setSelection(position+3);
                }
                else if(position>=6){
                    galleryFling.setSelection(position-3);
                }
                return true;
            }
        });
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.test_button1:

            case R.id.test_button2:
                finish();
                break;
        }
    }

    class GalleryAdapter extends BaseAdapter{
        Context mContext;
        LayoutInflater mLayoutInflater;

        public GalleryAdapter(Context context) {
            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return linears.length*3;
        }

        @Override
        public Object getItem(int position) {
            return linears[position % linears.length];
        }

        @Override
        public long getItemId(int position) {
            return position % linears.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            position = position % linears.length;

            int linearId = linears[position];
            LinearLayout linearLayout = (LinearLayout) mLayoutInflater.inflate(linearId, null);

            ImageView imageView_linear = (ImageView) linearLayout.findViewById(R.id.imageView_linear);

            switch (position){
                case 0:
                    imageView_linear.setImageResource(R.drawable.person1);
                    break;
                case 1:
                    imageView_linear.setImageResource(R.drawable.person2);
                    break;
                case 2:
                    imageView_linear.setImageResource(R.drawable.person3);
                    break;
            }
            return linearLayout;
        }
    }
}
