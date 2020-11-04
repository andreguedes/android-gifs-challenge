package br.com.gifs.data.source

import br.com.gifs.BuildConfig
import br.com.gifs.data.remote.GifApi
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private var retrofit: Retrofit? = null

    fun getClient(): GifApi {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(BuildConfig.LIMIT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(BuildConfig.LIMIT.toLong(), TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request()
                val originalHttpUrl: HttpUrl = request.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .addQueryParameter("limit", BuildConfig.LIMIT.toString())
                    .build()

                val requestBuilder: Request.Builder = request.newBuilder()
                    .url(url)
                chain.proceed(requestBuilder.build())
            }
            .build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        }
        return retrofit!!.create(GifApi::class.java)
    }

}