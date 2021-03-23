package prieto.fernando.domain

import kotlinx.coroutines.flow.Flow
import prieto.fernando.domain.model.CompanyInfoDomainModel
import prieto.fernando.domain.model.LaunchDomainModel
import prieto.fernando.domain.model.SampleDomainModel

interface SpaceXRepository {
    suspend fun getCompanyInfo(): Flow<CompanyInfoDomainModel>
    suspend fun getAllLaunches(): Flow<List<LaunchDomainModel>>
}