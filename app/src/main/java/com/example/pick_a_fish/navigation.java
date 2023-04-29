package com.example.pick_a_fish;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.pick_a_fish.Modals.Post;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class navigation extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;

    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    Dialog popAddpost;

    ImageView popupUserImage, popupPostImage,popupAddbtn,profile_img_home, img_bg_nav;
    TextView popupTitle,popupDescription,nav_name;
    ProgressBar popupClickProgress;

    private static final int PReqcode = 2;
    private static final int REQUESCODE = 2;
    private Uri pickedImgUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        //Assign Variable
        bottomNavigation = findViewById(R.id.navigation_bottom);




        //Activity Profile When click profile image



        profile_img_home = findViewById(R.id.profile_img2);
        profile_img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent profileActivity = (new Intent(getApplicationContext(), profile_activity.class));
                startActivity(profileActivity);


            }
        });


        img_bg_nav = findViewById(R.id.nav_img_bg);



        //Add or Create a Post

        iniPopup();
        setupPopupImageClick();


        nav_name = findViewById(R.id.txtFname);


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popAddpost.show();
            }
        });


        //Add menu Item

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.homeicon));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.infoicon));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_people_alt_24));
      //  bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.bookmark_icon));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //Initialize Fragments
                Fragment fragment = null;

                switch (item.getId()) {
                    case 1:
                        //Initialize Home Fragment
                        //Get id 1

                        img_bg_nav.setImageResource(R.drawable.layout_corners);
                        nav_name.setText("HOME");
                        fab.setVisibility(View.INVISIBLE);
                        fragment = new home_fragment();
                        break;
                    case 2:
                        //Initialize Information Fragment
                        //Get id 2
                        img_bg_nav.setImageResource(R.drawable.layout_corners);
                        nav_name.setText("Fish Informations");
                        fab.setVisibility(View.INVISIBLE);
                        fragment = new information_fragment();
                        break;
                    case 3:
                        //Initialize Recipe Fragment
                        //Get id

                        img_bg_nav.setImageResource(R.color.white);
                        nav_name.setText("Forum");
                        fab.setVisibility(View.VISIBLE);
                        fragment = new recipe_fragment();
                        break;
                    //case 4:
                        //Initialize Bookmark Fragment
                        //Get id 4
                        //fab.setVisibility(View.INVISIBLE);
                        //fragment = new bookmark_fragment();
                        //break;
                }
                loadFragment(fragment);
            }
        });

        //Set notification count

        //Set Home Fragment initially Selected
        bottomNavigation.show(1, false);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });




    }



    private void setupPopupImageClick() {
        popupPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndRequestForPermission();
            }
        });
    }

    private void checkAndRequestForPermission() {

        if(ContextCompat.checkSelfPermission(navigation.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(navigation.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(navigation.this, "Please Accept for required permission", Toast.LENGTH_SHORT).show();

            }

            else {
                ActivityCompat.requestPermissions(navigation.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PReqcode);
            }
        }
        else{
            openGallery();
        }

    }
    private void openGallery() {
        //TODO: Open gallery and Pick image from Gallery
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {
            //the user has successfully pick an image and save to firebase
            pickedImgUri = data.getData();
            popupPostImage.setImageURI(pickedImgUri);


        }
    }


    //Fragment loader
    private void loadFragment(Fragment fragment) {
        //Replace Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();

    }

    private void iniPopup() {
        popAddpost = new Dialog(this);
        popAddpost.setContentView(R.layout.popup_add_post);
        popAddpost.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popAddpost.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT,Toolbar.LayoutParams.WRAP_CONTENT);
        popAddpost.getWindow().getAttributes().gravity = Gravity.TOP;

        //ini pop widgets

        popupUserImage = popAddpost.findViewById(R.id.popup_user_image);
        popupPostImage = popAddpost.findViewById(R.id.popup_image);
        popupTitle = popAddpost.findViewById(R.id.popup_title);
        popupDescription = popAddpost.findViewById(R.id.popup_description);
        popupAddbtn = popAddpost.findViewById(R.id.popup_add);
        popupClickProgress = popAddpost.findViewById(R.id.popup_progressBar);


        // load user current profile photo

        Glide.with(navigation.this).load(currentUser.getPhotoUrl()).into(popupUserImage);
        Glide.with(navigation.this).load(currentUser.getPhotoUrl()).into(profile_img_home);






        // Add post click Listener

        popupAddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupAddbtn.setVisibility(View.INVISIBLE);
                popupClickProgress.setVisibility(View.VISIBLE);

                // Test all fields

                if(!popupTitle.getText().toString().isEmpty()
                        && !popupDescription.getText().toString().isEmpty()
                        && pickedImgUri !=null){

                    //Firebase Database store Images

                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("blog_images");
                    StorageReference imageFilePath = storageReference.child(pickedImgUri.getLastPathSegment());
                    imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageDownloadLink = uri.toString();
                                    //Create post Object
                                    Post post = new Post(popupTitle.getText().toString(),
                                                           popupDescription.getText().toString(),imageDownloadLink,
                                                            currentUser.getUid(),
                                                            currentUser.getPhotoUrl().toString());

                                    // Add post to the Database
                                    addPost(post);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Uploading to database goes wrong

                                    showMessage(e.getMessage());
                                    popupAddbtn.setVisibility(View.INVISIBLE);
                                    popupClickProgress.setVisibility(View.VISIBLE);

                                }
                            });
                        }
                    });






                }else {
                    showMessage("Please verify all Input fields and pick a image");
                    popupAddbtn.setVisibility(View.VISIBLE);
                    popupClickProgress.setVisibility(View.INVISIBLE);

                }


            }
        });




    }

    private void addPost(Post post) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Posts").push();

        //get post unique ID and update post key

        String key = myRef.getKey();
        post.setPostKey(key);


        // Post to FirebaseDatabase

        myRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                showMessage("Post Added Succesfully");
                popupAddbtn.setVisibility(View.INVISIBLE);
                popupClickProgress.setVisibility(View.VISIBLE);
                popAddpost.dismiss();
            }
        });



    }

    private void showMessage(String message) {

        Toast.makeText(navigation.this,message,Toast.LENGTH_LONG).show();

    }

}

