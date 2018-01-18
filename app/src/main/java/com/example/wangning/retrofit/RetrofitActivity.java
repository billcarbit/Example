package com.example.wangning.retrofit;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.wangning.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-04-11
 * @since JDK 1.8
 */
public class RetrofitActivity extends Activity
        implements View.OnClickListener {
    private static final String TAG = "RetrofitActivity";
    final static int CODE_OPEN_PHOTO_ALBUM = 0x1;//从手机相册中选择
    private static final String BASE_URL = "http://localhost:8080/hello/";
    Retrofit mRetrofit;
    Button btnUploadFile;
    Button btnGetJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        initRetrofit();
        initView();
        initData();

    }

    void initRetrofit() {
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //可以接收自定义的Gson，当然也可以不传
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    void initView() {
        btnGetJson = (Button) findViewById(R.id.btn_get_json);
        btnUploadFile = (Button) findViewById(R.id.btn_upload_file);
    }

    void initData() {
        btnGetJson.setOnClickListener(this);
        btnUploadFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_json:
                httpGetJson();
                break;
            case R.id.btn_upload_file:
                httpUploadFile();
                break;
            default:
                break;
        }
    }

    void httpUploadFile() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");//从所有图片中进行选择
        startActivityForResult(intent, CODE_OPEN_PHOTO_ALBUM);


    }

    // 因为调用了Camera和Gally所以要判断他们各自的返回情况,他们启动时是这样的startActivityForResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case CODE_OPEN_PHOTO_ALBUM: {
                Uri uri = data.getData();
                String realPath = getRealFilePath(this, uri);
                Log.e(TAG, "onActivityResult: uri=" + uri.toString());
                Log.e(TAG, "onActivityResult: uri.getPath=" + uri.getPath());
                Log.e(TAG, "onActivityResult: uri.realPath=" + realPath);
                upLoadImg(realPath);
                break;
            }
            default:
                break;
        }
    }


    public void upLoadImg(String imgUrl) {
        Map<String, RequestBody> params = new HashMap<>();
        File file = new File(imgUrl);
        // create RequestBody instance from file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        //一定要加("AttachmentKey\"; filename=\"" +，不然失败
        params.put("AttachmentKey\"; filename=\"" + file.getName(), requestFile);
        // create upload service client
        MyFirstServlet service = mRetrofit.create(MyFirstServlet.class);
        // finally, execute the request
        Call<ResponseBody> call = service.uploadFiles(params);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e(TAG, "onResponse: upLoadImg SUCCESS");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable e) {
                Log.e(TAG, "onResponse: upLoadImg FAIL");
                e.printStackTrace();

            }
        });

    }


    void httpGetJson() {
        MyFirstServlet service = mRetrofit.create(MyFirstServlet.class);
        Blog blog = new Blog();
        blog.setContent("新建的Blog");
        blog.setTitle("测试");
        Call<MyFirstServletResp> call = service.getJsonData(blog);
        call.enqueue(new Callback<MyFirstServletResp>() {
            @Override
            public void onResponse(Call<MyFirstServletResp> call, Response<MyFirstServletResp> response) {
                // 已经转换为想要的类型了
                MyFirstServletResp result = response.body();
                Log.e(TAG, "onResponse: result=" + result.toJson());
            }

            @Override
            public void onFailure(Call<MyFirstServletResp> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public interface MyFirstServlet {

        @POST("MyFirstServlet")
        Call<MyFirstServletResp> getJsonData(@Body Blog blog);

        @Multipart
        @POST("uploadFile")
        Call<ResponseBody> uploadFiles(@PartMap Map<String, RequestBody> params);

    }

    /**
     * uri转path
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * path转uri
     */
    private Uri getUri(String path) {
        Uri uri = null;
        if (path != null) {
            path = Uri.decode(path);
            Log.d(TAG, "path2 is " + path);
            ContentResolver cr = this.getContentResolver();
            StringBuffer buff = new StringBuffer();
            buff.append("(")
                    .append(MediaStore.Images.ImageColumns.DATA)
                    .append("=")
                    .append("'" + path + "'")
                    .append(")");
            Cursor cur = cr.query(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    new String[]{MediaStore.Images.ImageColumns._ID},
                    buff.toString(), null, null);
            int index = 0;
            for (cur.moveToFirst(); !cur.isAfterLast(); cur
                    .moveToNext()) {
                index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
// set _id value
                index = cur.getInt(index);
            }
            if (index == 0) {
//do nothing
            } else {
                Uri uri_temp = Uri.parse("content://media/external/images/media/" + index);
                Log.d(TAG, "uri_temp is " + uri_temp);
                if (uri_temp != null) {
                    uri = uri_temp;
                }
            }
        }
        return uri;
    }


}
