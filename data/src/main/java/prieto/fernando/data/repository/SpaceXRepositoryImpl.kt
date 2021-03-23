package prieto.fernando.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import prieto.fernando.data.SpaceXRemoteSource
import prieto.fernando.data.mapper.CompanyInfoRepositoryToDomainModelMapper
import prieto.fernando.data.mapper.LaunchesRepositoryToDomainModelMapper
import prieto.fernando.domain.SpaceXRepository
import prieto.fernando.domain.model.CompanyInfoDomainModel
import prieto.fernando.domain.model.LaunchDomainModel
import prieto.fernando.domain.model.SampleDomainModel
import javax.inject.Inject

class SpaceXRepositoryImpl @Inject constructor(
    private val spaceXRemoteSource: SpaceXRemoteSource,
    private val companyInfoDomainMapper: CompanyInfoRepositoryToDomainModelMapper,
    private val launchDomainMapper: LaunchesRepositoryToDomainModelMapper
) : SpaceXRepository {

    override suspend fun getCompanyInfo(): Flow<CompanyInfoDomainModel> =
        spaceXRemoteSource.getCompanyInfo()
            .map { companyInfoRepositoryModel ->
                companyInfoDomainMapper.toDomainModel(companyInfoRepositoryModel)
            }


    override suspend fun getAllLaunches(): Flow<List<LaunchDomainModel>> =
        spaceXRemoteSource.getAllLaunches().map { allLaunchesRepositoryModel ->
            launchDomainMapper.toDomainModel(allLaunchesRepositoryModel)

        }
}
