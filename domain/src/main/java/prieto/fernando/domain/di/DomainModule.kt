package prieto.fernando.domain.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import prieto.fernando.domain.SpaceXRepository
import prieto.fernando.domain.mapper.LaunchesDomainFilter
import prieto.fernando.domain.mapper.LaunchesDomainFilterImpl
import prieto.fernando.domain.usecase.*

@Module
@InstallIn(ApplicationComponent::class)
class DomainModule {
    @Provides
    @Reusable
    fun provideGetSampleUseCaseImpl(
        spaceXRepository: SpaceXRepository
    ): GetCompanyInfo = GetCompanyInfoImpl(spaceXRepository)

    @Provides
    @Reusable
    fun provideGetLaunches(
        spaceXRepository: SpaceXRepository,
        filter: LaunchesDomainFilter
    ): GetLaunchInfo = GetLaunchInfoImpl(spaceXRepository, filter)

    @Provides
    @Reusable
    fun providesLaunchDomainFilter():LaunchesDomainFilter =
        LaunchesDomainFilterImpl()

}
