package com.example.missionalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

    public class gallery_notify extends Fragment {
        private static final int RESULT_OK =0 ;
        ImageView imageView;
        Button button;
        private static final int PICK_IMAGE = 100;
        Uri imageUri;
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            View v = inflater.inflate(R.layout.activity_gallery_notify, container, false);
            imageView = v.findViewById(R.id.imageView);
            button = v.findViewById(R.id.buttonLoadPicture);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openGallery();
                }
            });
            return v;
        }

        private void openGallery() {
            Intent gallery = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, PICK_IMAGE);
        } 
        public void onActivityResult(int requestCode, int resultCode, Intent
                data){
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
                imageUri = data.getData();
                imageView.setImageURI(imageUri);
            }
        
        }
    }
