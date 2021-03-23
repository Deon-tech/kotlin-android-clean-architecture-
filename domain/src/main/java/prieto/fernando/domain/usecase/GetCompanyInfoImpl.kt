package prieto.fernando.domain.usecase

import kotlinx.coroutines.flow.Flow
import prieto.fernando.domain.SpaceXRepository
import prieto.fernando.domain.model.CompanyInfoDomainModel
import prieto.fernando.domain.model.SampleDomainModel
import javax.inject.Inject

class GetCompanyInfoImpl @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) : GetCompanyInfo {
    override suspend fun execute(): Flow<CompanyInfoDomainModel> = spaceXRepository.getCompanyInfo()
}

interface GetCompanyInfo {
    suspend fun execute(): Flow<CompanyInfoDomainModel>
}
