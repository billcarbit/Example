package com.example.wangning.download;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.wangning.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okio.ByteString;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/7/16.
 */
public class DownloadActivity extends AppCompatActivity implements ProgressListener {

    private static final String BASE_URL = "http://localhost:8080/hello/";

    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .addNetworkInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    okhttp3.Response response = chain.proceed(chain.request());
                    return response.newBuilder().body(new ProgressResponseBody(response.body(), DownloadActivity.this)).build();
                }
            })
            .build();


    public Retrofit mRetrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.newBuilder().build())
            .baseUrl(BASE_URL).build();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownload();
            }
        });
    }

    private void startDownload() {
        DownloadRetrofit service = mRetrofit.create(DownloadRetrofit.class);
        final Call<ResponseBody> call = service.downloadTxt();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    ByteString byteString = response.body().source().readByteString();
                    byte[] byteArray = response.body().source().readByteArray();
                    byte[] byteArray2 = response.body().source().buffer().readByteArray();
                    InputStream inputStream = response.body().source().inputStream();
                    Log.e("startDownload", "onResponse: byteString=" + byteString);
                    Log.e("startDownload", "onResponse: byteArray=" + byteArray.toString());
                    Log.e("startDownload", "onResponse: byteArray2=" + byteArray2.toString());
                    Log.e("startDownload", "onResponse: inputStream=" + inputStream);
                    Log.e("startDownload", "onResponse: response.message=" + response.message());
                    writeResponseBodyToDisk(response.body());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onProgress(long currentBytes, long contentLength, boolean done) {
        Log.e("ASASA", "onProgress: currentBytes=" + currentBytes + ",contentLength=" + contentLength + ",done=" + done);
    }

    File file = new File(Environment.getExternalStorageDirectory() + "/my_text.txt");


    private void output(String content) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            method3(file.toString(), content);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void output(InputStream inputStream) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream out = new FileOutputStream(file);
        byte buf[] = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.close();
        inputStream.close();
    }


    public static void method3(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content + "\r\n");
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("AASDDD", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
