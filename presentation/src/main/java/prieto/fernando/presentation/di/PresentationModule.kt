package prieto.fernando.presentation.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import prieto.fernando.presentation.mapper.*

@Module
@InstallIn(ApplicationComponent::class)
class PresentationModule {
    @Provides
    @Reusable
    fun provideCompanyInfoDomainToUiModelMapper(): CompanyInfoDomainToUiModelMapper =
        CompanyInfoDomainToUiModelMapperImpl()

    @Provides
    @Reusable
    fun provideLaunchInfoDomainToUiModelMapper(dateTransformer: DateTransformer): LaunchInfoDomainToUiModelMapper =
        LaunchInfoDomainToUiModelMapperImpl(dateTransformer)

    @Provides
    @Reusable
    fun provideDateTimeProvider(): DateTimeProvider =
        DateTimeProvider()

    @Provides
    @Reusable
    fun provideDateTransformer(dateTimeProvider: DateTimeProvider): DateTransformer =
        DateTransformerImpl(dateTimeProvider)


}