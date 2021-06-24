package com.example.monitoreo

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import com.googlecode.tesseract.android.TessBaseAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImagenAlerta : AppCompatActivity()
{
    private lateinit var alertasR: List<AlertaResponse>

    private var bitmap:Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        alertasR= emptyList()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imagen_alerta)
        val tutor_email=intent.getStringExtra("tutor_email")
        val id=intent.getIntExtra("id_hijo",0)

        val image_alerta=findViewById<ImageView>(R.id.image_alert)
        lifecycleScope.launch{
            obtenerAlertas(tutor_email.toString(),id,image_alerta)
        }
        val btnGI=findViewById<Button>(R.id.btnGI)
        btnGI.setOnClickListener {
            saveToSharedStorage()
        }
    }
    private fun saveToSharedStorage():File{
        val dateInMilis=System.currentTimeMillis()
        val filename="Captura-${dateInMilis}.jpg"
        val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        val newImage: ContentValues = ContentValues().apply{
            put(MediaStore.Images.Media.DISPLAY_NAME,filename)
            put(MediaStore.MediaColumns.MIME_TYPE,"image/jpeg")
            put(MediaStore.MediaColumns.DATE_ADDED,dateInMilis)
            put(MediaStore.MediaColumns.DATE_MODIFIED,dateInMilis)
            put(MediaStore.MediaColumns.SIZE,bitmap!!.byteCount)
            put(MediaStore.MediaColumns.WIDTH,bitmap!!.width)
            put(MediaStore.MediaColumns.HEIGHT,bitmap!!.height)
        }
        val newImageUri=contentResolver.insert(collection,newImage)

        contentResolver.openOutputStream(newImageUri!!,"w").use{
            bitmap!!.compress(Bitmap.CompressFormat.JPEG,100,it)
        }

        newImage.clear()
        contentResolver.update(newImageUri,newImage,null,null)
        val file=File(newImageUri.path)
        return file
    }
    suspend fun obtenerAlertas(tutor_email:String,id:Int,image_alerta:ImageView)=
        withContext(Dispatchers.Main){
            var a=0
            val ida=intent.getIntExtra("id_alerta",0)
            RetrofitClient.instance.tablaAlertasLast(tutor_email,id)
                .enqueue(object: Callback<List<AlertaResponse>> {
                    override fun onResponse(call: Call<List<AlertaResponse>>, response: Response<List<AlertaResponse>>)
                    {
                        if(response.code()==200)
                        {
                            alertasR=response.body()!!
                            for(i in 0..alertasR.size-1)
                            {
                                if(alertasR[i].id_alerta==ida)
                                {
                                    a=i
                                }
                            }
                            val imageString=alertasR[a].img
                            val decodeByte = Base64.decode(imageString,Base64.DEFAULT)
                            bitmap=BitmapFactory.decodeByteArray(decodeByte,0,decodeByte.size)
                            image_alerta.setImageBitmap(bitmap)
                        }
                        else
                        {
                            alertasR= emptyList()
                        }
                    }
                    override fun onFailure(call: Call<List<AlertaResponse>>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        alertasR= emptyList()
                    }
                })
        }
}