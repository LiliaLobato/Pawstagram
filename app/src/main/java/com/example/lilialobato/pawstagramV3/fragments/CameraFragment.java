package com.example.lilialobato.pawstagramV3.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.lilialobato.pawstagramV3.R;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class CameraFragment extends Fragment{


    Button upload_btn,capture_btn;
    ImageView captured_iv;
    Uri mImageUri;
    final int CAPTURE_IMAGE = 1,GALLARY_PICK = 2;
    Bitmap bitmap;
    String mStoryTitle,imageToString,mProfileImage;
    boolean OkToUpload;



    public CameraFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_camera, container, false);

        upload_btn = (Button) view.findViewById(R.id.upload_btn);
        capture_btn = (Button) view.findViewById(R.id.capture_btn);
        captured_iv = (ImageView) view.findViewById(R.id.captured_iv);

        OkToUpload = false;



      return view;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        capture_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String[] options = {"Choose From Gallery","Take Photo"};
                AlertDialog.Builder build = new AlertDialog.Builder(v.getContext());
                build.setTitle("Choose Image");
                build.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        switch (which) {
                            //choose from gallery
                            case 0:
                                Intent galleryIntent = new Intent();
                                galleryIntent.setType("image/*");
                                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(galleryIntent, "Select Image"), GALLARY_PICK);

                                break;

                            //take a photo using camera
                            case 1:

                                capturePhoto();

                                break;


                        }


                    }
                });

                build.show();





            }
        });


        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                storyAndImageTitle();

            }
        });


    }



    private void capturePhoto(){

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String imageName = "image.jpg";
           // mImageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),imageName));
          //File file = new File(Environment.getExternalStorageDirectory(),imageName);
          //mImageUri = FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", file);
          //cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,mImageUri);
             startActivityForResult(cameraIntent,CAPTURE_IMAGE);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAPTURE_IMAGE){

            if(resultCode == RESULT_OK){



                OkToUpload = true;
                bitmap =  (Bitmap) data.getExtras().get("data");
                Toast.makeText(getContext(),"Now Click on Upload Button to Upload image",Toast.LENGTH_LONG).show();
                    if(bitmap != null) {
                       // captured_iv.setImageBitmap(bitmap);
                    }



                }
            }





        if(requestCode == GALLARY_PICK){

            if(resultCode == RESULT_OK){


                OkToUpload = true;
                Uri uri =   data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),uri);
                    Toast.makeText(getContext(),"Now Click on Upload Button to Upload image",Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // captured_iv.setImageBitmap(bitmap);

            }


        }
    }

    private void storyAndImageTitle(){

        final EditText editText = new EditText(getContext());
        editText.setTextColor(Color.BLACK);
        editText.setHint("Set Title/Tags for story");
        editText.setHintTextColor(Color.GRAY);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Story Title");
        builder.setCancelable(false);
        builder.setView(editText);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if(OkToUpload) {
                    mStoryTitle = editText.getText().toString();
                    imageToString = convertImageToString();
                    uploadStory();
                }else{
                    Toast.makeText(getContext(),"Please take a photo first!",Toast.LENGTH_LONG).show();
                }



            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();





    }

    private String convertImageToString(){

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] imageByteArray = baos.toByteArray();
        String result =  Base64.encodeToString(imageByteArray,Base64.DEFAULT);

        return result;


    }

    private void uploadStory(){


        if(!OkToUpload){
            Toast.makeText(getContext(),"There is no image to upload!",Toast.LENGTH_LONG).show();

            return;
        }


        final ProgressDialog mProgrssDialog =  new ProgressDialog(getContext());
        mProgrssDialog.setTitle("Uploading Story");
        mProgrssDialog.setMessage("Please wait....");
        mProgrssDialog.show();




        OkToUpload = false;


    }
}