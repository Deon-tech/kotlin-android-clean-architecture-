package fernando.prieto.data_remote.mapper

import prieto.fernando.data.model.CompanyInfoRepositoryModel
import prieto.fernando.data_api.model.CompanyInfoResponse
import javax.inject.Inject

class CompanyInfoResponseToRepositoryModelMapperImpl @Inject constructor():
    CompanyInfoResponseToRepositoryMapper
{
    override fun toRepositoryModel(companyInfoResponse: CompanyInfoResponse): CompanyInfoRepositoryModel =
        CompanyInfoRepositoryModel(
            name = companyInfoResponse.name,
            founded = companyInfoResponse.founded,
            founder = companyInfoResponse.founder,
            employees = companyInfoResponse.employees,
            launchSites = companyInfoResponse.launchSites,
            valuation = companyInfoResponse.valuation
        )
    }


interface CompanyInfoResponseToRepositoryMapper {
    fun toRepositoryModel(companyInfoResponse: CompanyInfoResponse): CompanyInfoRepositoryModel
}

