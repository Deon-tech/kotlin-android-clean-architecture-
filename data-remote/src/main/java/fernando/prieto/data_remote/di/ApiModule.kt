package fernando.prieto.data_remote.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import fernando.prieto.data_remote.ApiService
import fernando.prieto.data_remote.data.SpaceXRemoteSourceImpl
import fernando.prieto.data_remote.mapper.*
import okhttp3.OkHttpClient
import prieto.fernando.data.SpaceXRemoteSource
import retrofit2.Retrofit
import javax.inject.Singleton


@Module()
@InstallIn(ApplicationComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provideSpaceXRemoteSource(
        apiService: ApiService,
        companyInfoRepositoryMapper: CompanyInfoResponseToRepositoryMapper,
        launchesInfoRepositoryMapper: LaunchesInfoResponseToRepositoryMapper
    ): SpaceXRemoteSource =
        SpaceXRemoteSourceImpl(apiService, companyInfoRepositoryMapper, launchesInfoRepositoryMapper)

    @Reusable
    @Provides
    fun provideCompanyInfoResponseToRepositoryMapper(): CompanyInfoResponseToRepositoryMapper =
        CompanyInfoResponseToRepositoryModelMapperImpl()

    @Reusable
    @Provides
    fun provideLaunchesInfoResponseToRepositoryMapper(
        dateFormatter: DateFormatter
    ): LaunchesInfoResponseToRepositoryMapper =
        LaunchesInfoResponseToRepositoryModelMapperImpl(dateFormatter)

    @Reusable
    @Provides
    fun provideDateFormatter(): DateFormatter =
        DateFormatterImpl()


    companion object{
        @Provides
        @JvmStatic
        @Singleton
        internal fun provideApi(retrofit: Retrofit): ApiService =
            retrofit.create(ApiService::class.java)

        @Provides
        @JvmStatic
        @Singleton
        internal fun provideRetrofit(
            httpBuilder: OkHttpClient.Builder,
            retrofitBuilder: Retrofit.Builder
        ): Retrofit = retrofitBuilder
            .client(httpBuilder.build())
            .build()
    }

}