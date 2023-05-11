package com.example.pick_a_fish;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class information_activity extends AppCompatActivity {


    TextView FNtxt,infoSN,infoCN,infoEN,infoENVI,infoDESC;
    ImageSlider imageSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        FNtxt = findViewById(R.id.txtFN);
        Intent intent = getIntent();
        String fishname = intent.getExtras().getString("fishname");
        FNtxt.setText(fishname);


        //content finder

        infoSN = findViewById(R.id.infoSN);
        infoCN = findViewById(R.id.infoCN);
        infoEN = findViewById(R.id.infoEN);
        infoENVI = findViewById(R.id.infoENVI);
        infoDESC = findViewById(R.id.infoDESC);


        //Condition

        if(fishname.equals("Bangus")){

            //Fish information
            infoSN.setText(R.string.sSN1);
            infoCN.setText(R.string.sCN1);
            infoEN.setText(R.string.sEN1);
            infoENVI.setText(R.string.sENVI1);
            infoDESC.setText(R.string.sDESC1);


            //Image Slider
            imageSlider = findViewById(R.id.imgSlider);
            List<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(R.drawable.banugs_five, ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.banugs_two,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.banugs_three,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.banugs_four,ScaleTypes.CENTER_CROP));
            imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
        }
        else if (fishname.equals("Bolinao")) {
            //Fish information
            infoSN.setText(R.string.sSN2);
            infoCN.setText(R.string.sCN2);
            infoEN.setText(R.string.sEN2);
            infoENVI.setText(R.string.sENVI2);
            infoDESC.setText(R.string.sDESC2);

            //Image Slider
            imageSlider = findViewById(R.id.imgSlider);
            List<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(R.drawable.ancho_one, ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.ancho_two,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.ancho_three,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.ancho_four,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.ancho_five,ScaleTypes.CENTER_CROP));
            imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
        }
        else if (fishname.equals("Dalagang Bukid")) {

            //Fish information
            infoSN.setText(R.string.sSN3);
            infoCN.setText(R.string.sCN3);
            infoEN.setText(R.string.sEN3);
            infoENVI.setText(R.string.sENVI3);
            infoDESC.setText(R.string.sDESC3);

            //Image Slider
            imageSlider = findViewById(R.id.imgSlider);
            List<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(R.drawable.dalaga_one, ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.dalaga_two,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.dalaga_three,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.dalaga_four,ScaleTypes.CENTER_CROP));
            imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
        }
        else if (fishname.equals("Galunggong")) {

            //Fish information
            infoSN.setText(R.string.sSN4);
            infoCN.setText(R.string.sCN4);
            infoEN.setText(R.string.sEN4);
            infoENVI.setText(R.string.sENVI4);
            infoDESC.setText(R.string.sDESC4);



            //Image Slider
            imageSlider = findViewById(R.id.imgSlider);
            List<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(R.drawable.galung_one, ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.galung_four,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.galung_two, ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.galung_five, ScaleTypes.CENTER_CROP));
            imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
        }
        else if (fishname.equals("Hasa hasa")) {


            //Fish information
            infoSN.setText(R.string.sSN5);
            infoCN.setText(R.string.sCN5);
            infoEN.setText(R.string.sEN5);
            infoENVI.setText(R.string.sENVI5);
            infoDESC.setText(R.string.sDESC5);

            //Image Slider
            imageSlider = findViewById(R.id.imgSlider);
            List<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(R.drawable.hasa_one, ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.hasa_two,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.hasa_three, ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.hasa_four,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.hasa_five, ScaleTypes.CENTER_CROP));
            imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
        }
        else if (fishname.equals("Maya maya")) {

            //Fish information
            infoSN.setText(R.string.sSN6);
            infoCN.setText(R.string.sCN6);
            infoEN.setText(R.string.sEN6);
            infoENVI.setText(R.string.sENVI6);
            infoDESC.setText(R.string.sDESC6);

            //Image Slider
            imageSlider = findViewById(R.id.imgSlider);
            List<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(R.drawable.maya_one, ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.maya_two,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.maya_three,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.maya_four,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.maya_five,ScaleTypes.CENTER_CROP));
            imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
        }
        else if (fishname.equals("Sapsap")) {

            //Fish information
            infoSN.setText(R.string.sSN7);
            infoCN.setText(R.string.sCN7);
            infoEN.setText(R.string.sEN7);
            infoENVI.setText(R.string.sENVI7);
            infoDESC.setText(R.string.sDESC7);


            //Image Slider
            imageSlider = findViewById(R.id.imgSlider);
            List<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(R.drawable.sap_one, ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.sap_five,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.sap_two,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.sap_three,ScaleTypes.CENTER_CROP));



            imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
        }
        else if (fishname.equals("Tamban")) {

            //Fish information
            infoSN.setText(R.string.sSN8);
            infoCN.setText(R.string.sCN8);
            infoEN.setText(R.string.sEN8);
            infoENVI.setText(R.string.sENVI8);
            infoDESC.setText(R.string.sDESC8);



            //Image Slider
            imageSlider = findViewById(R.id.imgSlider);
            List<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(R.drawable.tamban_one, ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.tamban_two,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.tamban_three,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.tamban_four,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.tamban_five,ScaleTypes.CENTER_CROP));
            imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
        }
        else if (fishname.equals("Tilapia")) {

            //Fish information
            infoSN.setText(R.string.sSN9);
            infoCN.setText(R.string.sCN9);
            infoEN.setText(R.string.sEN9);
            infoENVI.setText(R.string.sENVI9);
            infoDESC.setText(R.string.sDESC9);



            //Image Slider
            imageSlider = findViewById(R.id.imgSlider);
            List<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(R.drawable.tilapia_one, ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.tilapia_two,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.tilapia_three,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.tilapia_four,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.tilapia_five,ScaleTypes.CENTER_CROP));
            imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
        }
        else if (fishname.equals("Tulingan")) {

            //Fish information
            infoSN.setText(R.string.sSN10);
            infoCN.setText(R.string.sCN10);
            infoEN.setText(R.string.sEN10);
            infoENVI.setText(R.string.sENVI10);
            infoDESC.setText(R.string.sDESC10);

            //Image Slider
            imageSlider = findViewById(R.id.imgSlider);
            List<SlideModel> slideModels = new ArrayList<>();
            slideModels.add(new SlideModel(R.drawable.tulingan_two, ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.tulingan_one,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.tulingan_three,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.tulingan_four,ScaleTypes.CENTER_CROP));
            slideModels.add(new SlideModel(R.drawable.tulingan_five,ScaleTypes.CENTER_CROP));
            imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
        }

    }
}