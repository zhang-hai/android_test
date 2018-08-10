package com.sxw.unittestdemo.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.sxw.unittestdemo.utils.AESUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhanghai on 2018/7/5.
 * function：
 */

public class GsonAESConverterFactory extends Converter.Factory {

    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static GsonAESConverterFactory create() {
        return create(new Gson());
    }

    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
    public static GsonAESConverterFactory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new GsonAESConverterFactory(gson);
    }

    private final Gson gson;

    private GsonAESConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {

        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new JsonResponseBodyConverter<>(gson, adapter);  //响应
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {

        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new JsonRequestBodyConverter<>(gson, adapter); //请求
    }


//    /**
//     * Created by zhang on 2016/5/31.
//     * <p>
//     * 自定义请求RequestBody 加密
//     */
    public class JsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
        private final Charset UTF_8 = Charset.forName("UTF-8");

        private final Gson gson;
        private final TypeAdapter<T> adapter;

        JsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
            JsonWriter jsonWriter = gson.newJsonWriter(writer);
            adapter.write(jsonWriter, value);
            jsonWriter.close();
            return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
            //加密
//            APIBodyData data = new APIBodyData();
//            Log.i("xiaozhang", "request中传递的json数据：" + value.toString());
//            data.setData(XXTEA.Encrypt(value.toString(), HttpConstant.KEY));
//            String postBody = gson.toJson(data); //对象转化成json
//            Log.i("xiaozhang", "转化后的数据：" + postBody);
//            return RequestBody.create(MEDIA_TYPE, postBody);
        //这里需要，特别注意的是，request是将T转换成json数据。你要在T转换成json之后再做加密。再将数据post给服务器，同时要注意，你的T到底指的那个对象
        }

    }


    /**
     * Created by zhang on 2016/5/31.
     * 自定义响应ResponseBody
     */
    public class JsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

        private final Gson gson;
        private final TypeAdapter<T> adapter;

        JsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override
        public T convert(ResponseBody responseBody) throws IOException {

            String response=responseBody.string();

            try {
                T result = adapter.fromJson(response);//.read(jsonReader);
//                if (jsonReader.peek() != JsonToken.END_DOCUMENT) {
//                    throw new JsonIOException("JSON document was not fully consumed.");
//                }
                return result;
            } finally {
                responseBody.close();
            }

//            String strResult = response.substring(1, response.length() - 1);
//            String result = null;//解密
//            try {
//                result = AESUtils.Decrypt(strResult);
//                Log.i("xiaozhang", "解密的服务器数据：" + result);
////                PageBean pageBean=mGson.fromJson(result,PageBean.class);
//                return null;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//   //需要注意的是，response是将string转换成T，string需要先解密成json再转换成T，同样要注意你的T指代的谁。
//            return null;
        }

    }
}
