package com.example.imagify

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.imagify.databinding.ActivityDetailsBinding
import com.example.imagify.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

@Suppress("DEPRECATION")
class Details : AppCompatActivity() {

    val PICK_IMAGE=1
    lateinit var uri:Uri
    private lateinit var appDb:profileDb
    private lateinit var binding: ActivityDetailsBinding

    private lateinit var Fname:EditText
    private lateinit var Email:EditText
    private lateinit var Phone:EditText
    private lateinit var img:ImageView

    val REQUEST_IMAGE_CAPTURE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        img=findViewById(R.id.imageView3)
        appDb= profileDb.getDatabase(this)

        val btnUpload=findViewById<Button>(R.id.buttonupload)
        val btnclick=findViewById<Button>(R.id.buttonCapture)

        val myViewModel=viewModel(this)

        Fname=findViewById(R.id.etFirstName)
        Email=findViewById(R.id.etPhoneNo)
        Phone=findViewById(R.id.etPhoneNo)

        btnUpload.setOnClickListener{
            val intent=Intent(Intent.ACTION_GET_CONTENT)
            intent.type="image/*"
            startActivityForResult(Intent.createChooser(intent,"select image"),PICK_IMAGE)
        }
        btnclick.setOnClickListener{
            dispatchTakePictureIntent()
        }
        val subBtn=findViewById<Button>(R.id.button)
        subBtn.setOnClickListener{
            val fname=Fname.text.toString()
            val email=Email.text.toString()
            val phone=Phone.text.toString()

            val bitmap=(img.getDrawable() as BitmapDrawable).getBitmap()
            val stream=ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
            val image = stream.toByteArray()
            val profile=Profile(null,fname,email,image,phone)

            myViewModel.insertUser(profile)


        }
//        intent = Intent(this,MainActivity::class.java)
//        startActivity(intent)
//        finish()

    }

//    private fun writeData() {
//        val fname=findViewById<EditText>(R.id.etFirstName).text.toString()
//        val email=findViewById<EditText>(R.id.etEmail).text.toString()
//        val phone=findViewById<EditText>(R.id.etPhoneNo).text.toString()
//        val img=findViewById<ImageView>(R.id.imageView3)
//
//        val bitmap=(img.getDrawable() as BitmapDrawable).getBitmap()
//        val stream=ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream)
//        val image = stream.toByteArray()
//
//        if(fname.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && img!=null)
//        {
//            val profile=Profile(null,fname,email,image,phone)
//            GlobalScope.launch(Dispatchers.IO) {
//                appDb.profiledao().insert(profile)
//            }
//            Log.d("AKSHAT","INSERTED")
//            Toast.makeText(this,"Inserted",Toast.LENGTH_LONG).show()
//        }
//        else{
//            Toast.makeText(this,"Enter Data Properly",Toast.LENGTH_LONG).show()
//            Log.d("AKSHAT","FAILED@")
//        }
//
//
//    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this,"Camera couldn'y be opened",Toast.LENGTH_LONG).show()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== RESULT_OK && requestCode==PICK_IMAGE)
        {
            if (data != null) {
                uri = data.data!!
            }
            val bitMap:Bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri)
            img.setImageBitmap(bitMap)

        }
        else if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                img.setImageBitmap(imageBitmap)
            }
    }
}