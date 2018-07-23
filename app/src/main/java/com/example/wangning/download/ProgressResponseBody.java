package com.example.wangning.download;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RecoverySystem;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/7/16.
 */
public class ProgressResponseBody extends ResponseBody {
    public static final int UPDATE = 0x01;
    public static final String TAG = ProgressResponseBody.class.getName();
    private ResponseBody responseBody;
    private ProgressListener mListener;
    private BufferedSource bufferedSource;
    private Handler myHandler;

    public ProgressResponseBody(ResponseBody body, ProgressListener listener) {
        responseBody = body;
        mListener = listener;
        if (myHandler == null) {
            myHandler = new MyHandler();
        }
    }

    class MyHandler extends Handler {

        public MyHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE:
                    ProgressModel progressModel = (ProgressModel) msg.obj;
                    if (mListener != null)
                        mListener.onProgress(progressModel.getCurrentBytes(), progressModel.getContentLength(), progressModel.isDone());
                    break;

            }
        }


    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {

        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(mySource(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source mySource(Source source) {

        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {


                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;

                Message msg = Message.obtain();
                msg.what = UPDATE;
                msg.obj = new ProgressModel(totalBytesRead, contentLength(), totalBytesRead == contentLength());
                myHandler.sendMessage(msg);
                Log.i(TAG, "currentBytes==" + totalBytesRead + "==contentLength==" + contentLength());

                Buffer sink2 = sink.clone();
                output(sink2.inputStream(), file);

                return bytesRead;
            }
        };
    }

    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/d.apk");

    private int seek = 0;

    private void output(InputStream inputStream, File outputFile) throws IOException {
        FileOutputStream out = new FileOutputStream(outputFile,true);

        byte buf[] = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) > 0) {
            seek = seek + len;
            Log.e(TAG, "output: seek=" + seek);
            out.write(buf, 0, len);
        }
        out.close();
        inputStream.close();
    }
}


