package com.example.wangning.download;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.wangning.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
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
        // final Call<ResponseBody> call = service.downloadApk("bytes=0-9999999999999");
        final Call<ResponseBody> call = service.downloadApk();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    String length = response.headers().get("Content-Length");
                    Log.e("AAA", "onResponse: length=" + length);

                    final InputStream inputStream = response.body().source().inputStream();
                    //writeFile2Disk(response,file);

                } catch (Exception e) {
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

    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/c.apk");


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
                Log.e("sasas", "writeResponseBodyToDisk: sasa=" + inputStream.read());
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

    public static void writeFile2Disk(Response<ResponseBody> response, File file) {
        OutputStream os = null;
        InputStream is = response.body().byteStream();
        try {
            os = new FileOutputStream(file);
            int len;
            byte[] buff = new byte[1024];
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
