package com.example.banhang.ui.view.task.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.banhang.R
import com.example.banhang.databinding.ActivityNoiThatChiTietBinding
import com.example.banhang.ui.base.BaseActivityNew
import com.example.banhang.ui.view.task.data.ApiService
import com.example.banhang.ui.view.task.data.RetrofitBanHang
import com.example.banhang.ui.view.task.viewmodel.ViewModelNoiThat
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class NoiThatChiTietActivity : BaseActivityNew<ActivityNoiThatChiTietBinding>() {
    override fun getLayoutRes(): Int = R.layout.activity_noi_that_chi_tiet

    private val REQUEST_CODE = 100
    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri? = null


    private val viewModel: ViewModelNoiThat by viewModels()

    override fun getDataFromIntent() {
        val idnoithat = intent.getStringExtra("noithatid")

        idnoithat?.let { viewModel.getNoiThatCT(it) }
    }

    override fun initData() {
        binding.viewModelNoiThat = viewModel
        binding.executePendingBindings()
        checkAndRequestPermissions()

        binding.buttonSelectImage.setOnClickListener {
            openGallery()
        }

        binding.buttonUploadImage.setOnClickListener {
            uploadImageToServer()
        }
//        ==============
        viewModel.noiThatCT.observe(this) {
            binding.noithat = it
        }

        viewModel.soluong.observe(this){
            binding.soluong = it
        }
    }

    override fun initView() {

    }

    override fun setListener() {}

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, NoiThatActivity::class.java)
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { uri ->
                selectedImageUri = uri
                binding.imageView.setImageURI(uri)
            }
        }
    }

    private fun uploadImageToServer() {
        selectedImageUri?.let { uri ->
            val inputStream = contentResolver.openInputStream(uri)
            inputStream?.let { stream ->
                val file = File(uri.path)

                val requestFile =
                    RequestBody.create("image/*".toMediaTypeOrNull(), stream.readBytes())
                val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

                val serviceName =
                    RequestBody.create("text/plain".toMediaTypeOrNull(), "Tên dịch vụ của bạn")
                val description =
                    RequestBody.create("text/plain".toMediaTypeOrNull(), "Mô tả dịch vụ của bạn")

                RetrofitBanHang().server.uploadImage(body, serviceName, description)
                    .enqueue(object : Callback<ApiService.YourResponseModel> {
                        override fun onResponse(
                            call: Call<ApiService.YourResponseModel>,
                            response: Response<ApiService.YourResponseModel>,
                        ) {
                            if (response.isSuccessful) {
                                Log.d("Upload", "Success: ${response.body()}")
                            } else {
                                Log.e("Upload", "Failed: ${response.errorBody()?.string()}")
                            }
                        }

                        override fun onFailure(
                            call: Call<ApiService.YourResponseModel>,
                            t: Throwable,
                        ) {
                            Log.e("Upload", "Error occurred during upload: ${t.message}", t)
                        }
                    })
            } ?: run {
                Toast.makeText(this, "Unable to open input stream", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(this, "Please select an image first", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, you can proceed with your upload
            } else {
                // Permission denied, handle accordingly
            }
        }
    }
}