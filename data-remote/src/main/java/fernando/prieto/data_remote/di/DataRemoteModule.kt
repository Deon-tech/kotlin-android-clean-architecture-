package fernando.prieto.data_remote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import fernando.prieto.data_remote.data.SpaceXRemoteSourceImpl
import prieto.fernando.data.SpaceXRemoteSource
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataRemoteModule {
    @Singleton
    @Provides
    fun provideSampleRemoteSource(): SpaceXRemoteSource = SpaceXRemoteSourceImpl()
}