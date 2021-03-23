package prieto.fernando.domain.usecase

import android.location.Criteria
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import prieto.fernando.domain.SpaceXRepository
import prieto.fernando.domain.mapper.LaunchesDomainFilter
import prieto.fernando.domain.model.CompanyInfoDomainModel
import prieto.fernando.domain.model.LaunchDomainModel
import java.nio.ByteOrder
import javax.inject.Inject

class GetLaunchInfoImpl @Inject constructor(
    private val spaceXRepository: SpaceXRepository,
    private val launchesDomainFilter: LaunchesDomainFilter
) : GetLaunchInfo {
    override suspend fun execute(
        yearFilterCriteria: Int,
        ascendantOrder: Boolean
    ): Flow<List<LaunchDomainModel>> =
        spaceXRepository.getAllLaunches().map { allLaunchesDomainModel ->
        launchesDomainFilter.filter(
            allLaunchesDomainModel,
            yearFilterCriteria,
            ascendantOrder
        )
    }
}

interface GetLaunchInfo {
    suspend fun execute(
        yearFilterCriteria: Int,
        ascendantOrder: Boolean
    ): Flow<List<LaunchDomainModel>>
}