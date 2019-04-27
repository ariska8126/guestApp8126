package com.example.guestapp8126.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.guestapp8126.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegisterActivity extends AppCompatActivity {

    private EditText edt_guest_name, edt_guest_password,
            edt_guest_email, edt_guest_password2;
    private ImageView imgv_guest_photo;
    private Button btn_guest_register;
    private ProgressBar pb_guest_register;
    private FirebaseAuth auth;
    private FirebaseUser user;
    static int REQUESTCODE = 1;
    static int PReqCode = 1;
    Uri pickedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init view
        edt_guest_name = findViewById(R.id.edt_name_register);
        edt_guest_email = findViewById(R.id.edt_email_register);
        edt_guest_password = findViewById(R.id.edt_password_register);
        edt_guest_password2 = findViewById(R.id.edt_password2_register);

        imgv_guest_photo = findViewById(R.id.imgv_guest_photo_register);

        btn_guest_register= findViewById(R.id.btn_guest_register);

        pb_guest_register = findViewById(R.id.pb_guest_Register);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        pb_guest_register.setVisibility(View.INVISIBLE);

        //onclick
        imgv_guest_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= 22){

                    checkAndRequestPermission();
                }
                else
                {
                    openGallery();
                }
            }
        });


        btn_guest_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btn_guest_register.setVisibility(View.INVISIBLE);
                pb_guest_register.setVisibility(View.VISIBLE);

                final String name = edt_guest_name.getText().toString();
                final String email = edt_guest_email.getText().toString();
                final String password = edt_guest_password.getText().toString();
                final String password2 = edt_guest_password2.getText().toString();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || !password.equals(password2) || pickedImageUri == null){

                    showMessage("Pastikan semua form telah diisi beserta foto profil");
                    btn_guest_register.setVisibility(View.VISIBLE);
                    pb_guest_register.setVisibility(View.INVISIBLE);
                }
                else
                {
                    CreateUserAccount(email, password, name);
                }
            }
        });


    }

    private void openGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null){

            pickedImageUri = data.getData();
            imgv_guest_photo.setImageURI(pickedImageUri);
        }
    }

    private void checkAndRequestPermission() {

        if (ContextCompat.checkSelfPermission(RegisterActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(this, "please accept for request permission", Toast.LENGTH_SHORT).show();
            }
            else
            {
                ActivityCompat.requestPermissions(RegisterActivity.this, new String[]
                        {Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);

            }
        }
        else
        {
            openGallery();
        }


    }

    private void CreateUserAccount(String email, String password, final String name) {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    showMessage("Account Created");

                    //update profil picture and name
                    updateUserInfo(name, pickedImageUri, user);
                }
                else
                {
                    showMessage("account created failed" +task.getException().getMessage());
                    btn_guest_register.setVisibility(View.VISIBLE);
                    pb_guest_register.setVisibility(View.INVISIBLE);
                }


            }
        });

    }

    private void updateUserInfo(final String name, Uri pickedImageUri, final FirebaseUser currentUser) {

        StorageReference guestPhotoReference = FirebaseStorage.getInstance().getReference().child("guestPhotos");
        final StorageReference imageFilePath = guestPhotoReference.child(pickedImageUri.getLastPathSegment());
        imageFilePath.putFile(pickedImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                //get url photo;
                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name).setPhotoUri(uri).build();

                        currentUser.updateProfile(profileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){

                                    showMessage("Register Completed");
                                    updateUI();
                                }
                            }
                        });
                    }
                });
            }
        });

    }

    private void updateUI() {

        Intent lengkapiProfile = new Intent(getApplicationContext(), CompletingProfileActivity.class);
        startActivity(lengkapiProfile);
        finish();
    }

    private void showMessage(String s) {

        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }


}
