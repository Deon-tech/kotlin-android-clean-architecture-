package prieto.fernando.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import prieto.fernando.data.SampleRemoteSource
import prieto.fernando.data.mapper.SampleRepositoryToDomainModelMapper
import prieto.fernando.domain.SpaceXRepository
import prieto.fernando.domain.model.SampleDomainModel
import javax.inject.Inject

class SpaceXRepositoryImpl @Inject constructor(
    private val sampleRemoteSource: SampleRemoteSource,
    private val sampleDomainMapper: SampleRepositoryToDomainModelMapper
) : SpaceXRepository {
    override suspend fun getSample(): Flow<SampleDomainModel> =
        sampleRemoteSource.getSample()
            .map { sample ->
                sampleDomainMapper.toDomainModel(sample)
            }
}
