package com.jaewoo.camera;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    ViewHolder holder;
    ArrayList<Uri> imageList;

    GalleryFling galleryFling;
    private int deletedPosition=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 상태바를 안보이도록 합니다.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_sub);

        imageList = getImages(this);

        galleryFling = (GalleryFling) findViewById(R.id.galleryFling);
        galleryFling.setAdapter(new GalleryAdapter(this));
        galleryFling.setSelection(0); // default view
    }

    ArrayList<Uri> getImages(Context context) {
        // DATA는 이미지 파일의 스트림 데이터 경로를 나타냅니다.
        String[] projection = { MediaStore.Images.Media.DATA };

        String selection = "bucket_display_name='DCIM'";

        Cursor imageCursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // 이미지 컨텐트 테이블
                projection, // DATA를 출력
                selection, // 해당 앱으로 찍은 사진만 출력
                null,
                MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");

        ArrayList<Uri> result = new ArrayList<>(imageCursor.getCount());
        int dataColumnIndex = imageCursor.getColumnIndex(projection[0]);

        if (imageCursor.moveToFirst()) {
            do {
                String filePath = imageCursor.getString(dataColumnIndex);
                Log.i("여기",filePath);
                Uri imageUri = Uri.parse(filePath);
                result.add(imageUri);
            } while(imageCursor.moveToNext());
        }

        imageCursor.close();
        return result;
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.test_button1:
                delete();
                imageList = getImages(this);
                break;
            case R.id.test_button2:
                finish();
                break;
        }
    }

    private void delete() {
        File file = new File(imageList.get(deletedPosition).getPath());
        imageList.remove(deletedPosition);
        file.delete();
        getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
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
            deletedPosition = position;
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
            if(imageList.size()!=0) {
                Bitmap bitmap = getBitmapFromUri(imageList.get(position)) != null ? getBitmapFromUri(imageList.get(position)) : getBitmapFromUri(imageList.get((position + 1)%imageList.size()));
                bitmap = rotatedBitmap(bitmap);
                imageView_linear.setImageBitmap(bitmap);
            }
            return linearLayout;
        }
        private Bitmap getBitmapFromUri(Uri imageUri) {

            Bitmap bitmap;

            try {
                bitmap = android.provider.MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse("file://" + imageUri));
                return bitmap;
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        private Bitmap rotatedBitmap(Bitmap bitmap) {
            if(bitmap.getHeight()<bitmap.getWidth()){
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
                return Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
            }
            else return bitmap;
        }
    }
    static class ViewHolder {
        ImageView imageView;
    }
}
