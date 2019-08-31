package com.example.trvelingingroup10.app_users;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trvelingingroup10.R;
import com.example.trvelingingroup10.travelers.Traveler;
import com.example.trvelingingroup10.utils.FireBaseInit;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;

public class GetUserBasicDetails extends AppCompatActivity {

    private ArrayList<EditText> editTexts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_basic_details);
        setTheEditTexts();
    }
    private void setTheEditTexts() {

        editTexts=new ArrayList<>();
        EditText name = findViewById(R.id.priavte_name_activity_getusername);
        EditText phone=findViewById(R.id.phone_number_enter_activity_get_user_name);
        EditText area=findViewById(R.id.enter_area_of_live_activity_user_name);

        ImageButton imageButton=findViewById(R.id.enter_profile_photo);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkAllFieldHaveData())
                    setTheImagePickIntent();
            }
        });
        editTexts.add(name);
        editTexts.add(phone);
        editTexts.add(area);


    }

    private void setTheImagePickIntent() {

        Intent pickImage = new Intent(Intent.ACTION_PICK);
        pickImage.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
        pickImage.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        pickImage.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        pickImage.setType("image/*");
        try
        {
            startActivityForResult(pickImage,1);

        }catch (Exception e)
        {

        }

    }
    private boolean checkAllFieldHaveData() {
        // check for empty field's and trow an error
        int x=0;
        for (EditText e:editTexts) {
            if(e.getText().toString().isEmpty())
            {
                x++;
                e.setError(e.getHint());
            }
        }
        return x == 0;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // pick a profile picture in order to upload to server
        if(requestCode==1&&resultCode==RESULT_OK)
        {
            saveUserBasicsInServer(data.getData());

        }
    }

    private void saveUserBasicsInServer(final Uri data) {
        // saves data on server
        final Traveler travelerClass=new Traveler(editTexts.get(0).getText().toString()
                ,editTexts.get(1).getText().toString()
                ,editTexts.get(2).getText().toString(),
                editTexts.get(3).getText().toString()+"/profileimage.jpeg");

        FireBaseInit.getFireBaseInit().getDatabase().collection("travelers")
                .add(travelerClass).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                task.getResult().set(travelerClass);
                travelerClass.putImageInServerWithInputStream(GetUserBasicDetails.this,data);
            }
        });
    }
}
