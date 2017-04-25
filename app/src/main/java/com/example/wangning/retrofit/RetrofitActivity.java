package com.example.wangning.retrofit;

import android.app.Activity;
import android.os.Bundle;

import com.example.wangning.R;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * file explain
 *
 * @author wangning
 * @version 1.0 2017-04-11
 * @since JDK 1.8
 */
public class RetrofitActivity extends Activity {
    private static final String TAG = "RetrofitActivity";
    Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

/*        mRetrofit = new Retrofit.Builder()
                .baseUrl(URLConstant.BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/


        final MockWebServer server = new MockWebServer();
        server.enqueue(new MockResponse().setBody("{\"name\": \"Jason\"}"));
        server.enqueue(new MockResponse());
        new Thread(){

            @Override
            public void run() {
                try {
                    server.start();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(server.url("/"))
                            .addConverterFactory(new ChunkingConverterFactory())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Service service = retrofit.create(Service.class);

                    Repo retrofitRepo = new Repo("square", "retrofit");

                    try {
                        service.sendNormal(retrofitRepo).execute();

                        RecordedRequest normalRequest = null;
                        try {
                            normalRequest = server.takeRequest();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(
                                "Normal @Body Transfer-Encoding: " + normalRequest.getHeader("Transfer-Encoding"));

                        service.sendChunked(retrofitRepo).execute();
                        RecordedRequest chunkedRequest = null;
                        try {
                            chunkedRequest = server.takeRequest();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(
                                "@Chunked @Body Transfer-Encoding: " + chunkedRequest.getHeader("Transfer-Encoding"));

                        server.shutdown();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }

    interface Service {
        @POST("/")
        Call<ResponseBody> sendNormal(@Body Repo repo);

        @POST("/")
        Call<ResponseBody> sendChunked(@Chunked @Body Repo repo);
    }

    @Target(PARAMETER)
    @Retention(RUNTIME)
    @interface Chunked {
    }

    static class Repo {
        final String owner;
        final String name;

        Repo(String owner, String name) {
            this.owner = owner;
            this.name = name;
        }
    }

    /**
     * A converter which removes known content lengths to force chunking when {@code @Chunked} is
     * present on {@code @Body} params.
     */
    static class ChunkingConverterFactory extends Converter.Factory {
        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                              Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            boolean isBody = false;
            boolean isChunked = false;
            for (Annotation annotation : parameterAnnotations) {
                isBody |= annotation instanceof Body;
                isChunked |= annotation instanceof Chunked;
            }
            if (!isBody || !isChunked) {
                return null;
            }

            // Look up the real converter to delegate to.
            final Converter<Object, RequestBody> delegate =
                    retrofit.nextRequestBodyConverter(this, type, parameterAnnotations, methodAnnotations);
            // Wrap it in a Converter which removes the content length from the delegate's body.
            return new Converter<Object, RequestBody>() {
                @Override
                public RequestBody convert(Object value) throws IOException {
                    final RequestBody realBody = delegate.convert(value);
                    return new RequestBody() {
                        @Override
                        public MediaType contentType() {
                            return realBody.contentType();
                        }

                        @Override
                        public void writeTo(BufferedSink sink) throws IOException {
                            realBody.writeTo(sink);
                        }
                    };
                }
            };
        }
    }
}
