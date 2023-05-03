package com.example.pick_a_fish;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pick_a_fish.ml.FishModel;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class scan_camera extends AppCompatActivity {

    Button scan_images, gallery_image;
    ImageView scanned_image, img_info, img_back;
    TextView result_txt, txt_SN, txt_CN, txt_EN, txt_ENVI, txt_DESC;
    Dialog mDialog;
    int imageSize = 224;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_camera);

        scan_images = findViewById(R.id.scan_btn);
        gallery_image = findViewById(R.id.gallery_btn);
        scanned_image = findViewById(R.id.scan_img);
        result_txt = findViewById(R.id.resultTxt);
        img_info = findViewById(R.id.info_img);
        img_back = findViewById(R.id.back_img);

        //fish INFO CALL

        txt_SN = findViewById(R.id.txtSN);
        txt_CN = findViewById(R.id.txtCN);
        txt_EN = findViewById(R.id.txtEN);
        txt_ENVI = findViewById(R.id.txtENVI);
        txt_DESC = findViewById(R.id.txtDESC);

        //Back to Navigation Activity

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(scan_camera.this, navigation.class);
                startActivity(intent);
                finish();

            }
        });

        //Info Dialog/Modal Popup
        mDialog = new Dialog(this);

        SharedPreferences preferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        String FirstTime = preferences.getString("FirstTimeInstall", "");

        if (FirstTime.equals("Yes")) {

            mDialog.setContentView(R.layout.tip_scanimage);
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mDialog.show();


        } else {

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("FirstTimeInstall", "Yes");
            editor.apply();


        }


        img_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.setContentView(R.layout.tip_scanimage);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();


            }
        });


        //Take photo - Camera Open
        scan_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 3);
                    scanned_image.setVisibility(View.VISIBLE);

                } else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                    scanned_image.setVisibility(View.INVISIBLE);

                }

            }
        });

        //Pick picture and open galley

        gallery_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(cameraIntent, 1);
                scanned_image.setVisibility(View.VISIBLE);
            }
        });


    }

    public void classifyImage(Bitmap image) {

        try {
            FishModel model = FishModel.newInstance(getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());
            int pixel = 0;
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat(((val & 0xFF) & 0xFF) * (1.f / 255.f));

                }


            }

            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            FishModel.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidence = outputFeature0.getFloatArray();
            //find the index of the class with the highest confidence
            int maxPos = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidence.length; i++) {
                if (confidence[i] > maxConfidence) {
                    maxConfidence = confidence[i];
                    maxPos = i;
                }
            }
            String[] classes = {"Bangus", "Bolinao", "Dalagang Bukid", "Galunggong", "Hasa hasa", "Maya maya", "Sapsap", "Tamban", "Tilapia", "Tulingan"};


            float mxconfi = 0;

            mxconfi = maxConfidence * 100;


            if (mxconfi >= 70) {



                result_txt.setText(classes[maxPos]);
                result_txt.setTextColor(Color.parseColor("#FFFFFF"));

                if(result_txt.getText() == "Bangus"){

                    txt_SN.setText(getResources().getString(R.string.sSN1));
                    txt_CN.setText(getResources().getString(R.string.sCN1));
                    txt_EN.setText(getResources().getString(R.string.sEN1));
                    txt_ENVI.setText(getResources().getString(R.string.sENVI1));
                    txt_DESC.setText(getResources().getString(R.string.sDESC1));

                }

                if(result_txt.getText() == "Bolinao"){

                    txt_SN.setText(getResources().getString(R.string.sSN2));
                    txt_CN.setText(getResources().getString(R.string.sCN2));
                    txt_EN.setText(getResources().getString(R.string.sEN2));
                    txt_ENVI.setText(getResources().getString(R.string.sENVI2));
                    txt_DESC.setText(getResources().getString(R.string.sDESC2));

                }

                if(result_txt.getText() == "Dalagang Bukid"){

                    txt_SN.setText(getResources().getString(R.string.sSN3));
                    txt_CN.setText(getResources().getString(R.string.sCN3));
                    txt_EN.setText(getResources().getString(R.string.sEN3));
                    txt_ENVI.setText(getResources().getString(R.string.sENVI3));
                    txt_DESC.setText(getResources().getString(R.string.sDESC3));

                }
                if(result_txt.getText() == "Galunggong"){

                    txt_SN.setText(getResources().getString(R.string.sSN4));
                    txt_CN.setText(getResources().getString(R.string.sCN4));
                    txt_EN.setText(getResources().getString(R.string.sEN4));
                    txt_ENVI.setText(getResources().getString(R.string.sENVI4));
                    txt_DESC.setText(getResources().getString(R.string.sDESC4));

                }
                if(result_txt.getText() == "Hasa hasa"){

                    txt_SN.setText(getResources().getString(R.string.sSN5));
                    txt_CN.setText(getResources().getString(R.string.sCN5));
                    txt_EN.setText(getResources().getString(R.string.sEN5));
                    txt_ENVI.setText(getResources().getString(R.string.sENVI5));
                    txt_DESC.setText(getResources().getString(R.string.sDESC5));

                }
                if(result_txt.getText() == "Maya maya"){

                    txt_SN.setText(getResources().getString(R.string.sSN6));
                    txt_CN.setText(getResources().getString(R.string.sCN6));
                    txt_EN.setText(getResources().getString(R.string.sEN6));
                    txt_ENVI.setText(getResources().getString(R.string.sENVI6));
                    txt_DESC.setText(getResources().getString(R.string.sDESC6));

                }
                if(result_txt.getText() == "Sapsap"){

                    txt_SN.setText(getResources().getString(R.string.sSN7));
                    txt_CN.setText(getResources().getString(R.string.sCN7));
                    txt_EN.setText(getResources().getString(R.string.sEN7));
                    txt_ENVI.setText(getResources().getString(R.string.sENVI7));
                    txt_DESC.setText(getResources().getString(R.string.sDESC7));

                }
                if(result_txt.getText() == "Tamban"){

                    txt_SN.setText(getResources().getString(R.string.sSN8));
                    txt_CN.setText(getResources().getString(R.string.sCN8));
                    txt_EN.setText(getResources().getString(R.string.sEN8));
                    txt_ENVI.setText(getResources().getString(R.string.sENVI8));
                    txt_DESC.setText(getResources().getString(R.string.sDESC8));

                }
                if(result_txt.getText() == "Tilapia"){

                    txt_SN.setText(getResources().getString(R.string.sSN9));
                    txt_CN.setText(getResources().getString(R.string.sCN9));
                    txt_EN.setText(getResources().getString(R.string.sEN9));
                    txt_ENVI.setText(getResources().getString(R.string.sENVI9));
                    txt_DESC.setText(getResources().getString(R.string.sDESC9));

                }
                if(result_txt.getText() == "Tulingan"){

                    txt_SN.setText(getResources().getString(R.string.sSN10));
                    txt_CN.setText(getResources().getString(R.string.sCN10));
                    txt_EN.setText(getResources().getString(R.string.sEN10));
                    txt_ENVI.setText(getResources().getString(R.string.sENVI10));
                    txt_DESC.setText(getResources().getString(R.string.sDESC10));

                }

            } else {

                result_txt.setTextColor(Color.parseColor("#DC2525"));
                result_txt.setText("Couldn't Identify");
                txt_CN.setText("");
                txt_SN.setText("Scientific Name");
                txt_EN.setText("");
                txt_ENVI.setText("");
                txt_DESC.setText("");

            }


            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == 3) {
                Bitmap image = (Bitmap) data.getExtras().get("data");
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                scanned_image.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                classifyImage(image);
            } else {
                Uri dat = data.getData();
                Bitmap image = null;
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), dat);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                scanned_image.setImageBitmap(image);

                image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                classifyImage(image);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}