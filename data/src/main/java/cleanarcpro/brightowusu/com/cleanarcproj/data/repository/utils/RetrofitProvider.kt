package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils

import cleanarcpro.brightowusu.com.cleanarcproj.data.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    companion object {
        fun providesRetrofit(
                okHttpClient: OkHttpClient,
                gson: Gson) : Retrofit {
            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient)
                    .build()
        }
    }

}