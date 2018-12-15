package com.jaewoo.camera;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    private GalleryFling galleryFling;
    ViewHolder holder;
    ArrayList<Uri> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        imageList = fetchAllImages(this);

        galleryFling = (GalleryFling) findViewById(R.id.galleryFling);
        galleryFling.setAdapter(new GalleryAdapter(this));
        galleryFling.setSelection(0); // default view
    }

    ArrayList<Uri> fetchAllImages(Context context) {
        // DATA는 이미지 파일의 스트림 데이터 경로를 나타냅니다.
        String[] projection = { MediaStore.Images.Media.DATA };

        Cursor imageCursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // 이미지 컨텐트 테이블
                projection, // DATA를 출력
                null,       // 모든 개체 출력
                null,
                MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC"); // 정렬 안 함

        ArrayList<Uri> result = new ArrayList<>(imageCursor.getCount());
        int dataColumnIndex = imageCursor.getColumnIndex(projection[0]);

        if (imageCursor == null) {
            // Error 발생
            // 적절하게 handling 해주세요
        } else if (imageCursor.moveToFirst()) {
            do {
                String filePath = imageCursor.getString(dataColumnIndex);
                Uri imageUri = Uri.parse(filePath);
                result.add(imageUri);
            } while(imageCursor.moveToNext());
        } else {
            // imageCursor가 비었습니다.
        }
        imageCursor.close();
        return result;
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.test_button1:
                // 사진 삭제 기능 넣어야 함

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
            return imageList.size();
        }

        @Override
        public Object getItem(int position) {
            return imageList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LinearLayout linearLayout = (LinearLayout) mLayoutInflater.inflate(R.layout.gallery_linear_1, null);

            ImageView imageView_linear = (ImageView) linearLayout.findViewById(R.id.imageView_linear);

            if(convertView==null){
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.gallery_linear_1, parent, false);
                holder = new ViewHolder();
                holder.imageView = (ImageView) convertView.findViewById(R.id.imageView_linear);
                convertView.setTag(holder);
            }
            else{
                holder = (ViewHolder) convertView.getTag();
            }
            imageView_linear.setImageURI(imageList.get(position));

            return linearLayout;
        }
    }
    static class ViewHolder {
        ImageView imageView;
    }
}
