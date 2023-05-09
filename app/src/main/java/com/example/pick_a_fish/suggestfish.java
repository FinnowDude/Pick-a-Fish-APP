package com.example.pick_a_fish;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;


public class suggestfish extends AppCompatActivity {
    ImageView imgCamera, imgGallery,imgView;
    ProgressDialog progressDialog;
    Button btnUpload;
    EditText txtfFishes;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;


    int imageSize =224;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestfish);



        imgCamera = findViewById(R.id.imageView4);
        imgGallery = findViewById(R.id.imageView11);
        imgView = findViewById(R.id.imageView3);
        btnUpload = findViewById(R.id.btnUpload);



        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UploadImage();

            }
        });
        // FireBase
        storageReference = FirebaseStorage.getInstance().getReference("Images");
        databaseReference = FirebaseDatabase.getInstance().getReference("Images");
        progressDialog = new ProgressDialog(suggestfish.this);


        //Camera
        imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)                {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 3);
                    }else{
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                    }
                }
            }


        });

        // Gallery
        imgGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    imgGallery.setVisibility(View.INVISIBLE);
                    TextView txtv4 = findViewById(R.id.textView4);
                    txtv4.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);

            }
        });





    }

    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }


    public void UploadImage() {

        if (FilePathUri != null) {

            progressDialog.setTitle("Image is Uploading...");
            progressDialog.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String TempImageName = txtfFishes.getText().toString().trim();
                            progressDialog.dismiss();
                            @SuppressWarnings("VisibleForTests")
                            uploadinfo imageUploadInfo = new uploadinfo(TempImageName, taskSnapshot.getUploadSessionUri().toString());
                            String ImageUploadId = databaseReference.push().getKey();
                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                            Toast.makeText(suggestfish.this, "Successfully Uploaded, Thank you!", Toast.LENGTH_LONG).show();
                        }
                    });
        } else {

                Toast.makeText(suggestfish.this, "Please Select Image/Take Image or please Turn on your Internet.", Toast.LENGTH_LONG).show();

        }
    }
    private boolean isConnected(suggestfish dashboardActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) dashboardActivity.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return (wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected());
    }
    // Image Display Resize
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            FilePathUri = data.getData();
            if(requestCode == 3){

                Bitmap image = (Bitmap) data.getExtras().get("data");
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                imgView.setImageBitmap(image);

            }else{

                Bitmap image = null;
                try{
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), FilePathUri);
                } catch (IOException e){
                    e.printStackTrace();
                }
                imgView.setImageBitmap(image);
                }

        }
    }
}