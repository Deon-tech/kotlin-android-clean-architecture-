package prieto.fernando.data.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import prieto.fernando.data.SpaceXRemoteSource
import prieto.fernando.data.mapper.CompanyInfoRepositoryToDomainModelMapper
import prieto.fernando.data.mapper.CompanyInfoRepositoryToDomainModelMapperImpl
import prieto.fernando.data.mapper.LaunchesRepositoryToDomainModelMapper
import prieto.fernando.data.mapper.LaunchesRepositoryToDomainModelMapperImpl
import prieto.fernando.data.repository.SpaceXRepositoryImpl
import prieto.fernando.domain.SpaceXRepository

@Module
@InstallIn(ApplicationComponent::class)
class SpaceXRepositoryModule {
    @Provides
    @Reusable
    fun provideSampleRepository(
        spaceXRemoteSource: SpaceXRemoteSource,
        companyInfoRepositoryMapper: CompanyInfoRepositoryToDomainModelMapper,
        launchInfoRepositoryMapper: LaunchesRepositoryToDomainModelMapper
    ): SpaceXRepository = SpaceXRepositoryImpl(spaceXRemoteSource, companyInfoRepositoryMapper, launchInfoRepositoryMapper)

    @Provides
    @Reusable
    fun provideCompanyInfoRepositoryToDomainModelMapper(): CompanyInfoRepositoryToDomainModelMapper =
        CompanyInfoRepositoryToDomainModelMapperImpl()

    @Provides
    @Reusable
    fun provideLaunchesInfoRepositoryToDomainModelMapper(): LaunchesRepositoryToDomainModelMapper =
        LaunchesRepositoryToDomainModelMapperImpl()

}
