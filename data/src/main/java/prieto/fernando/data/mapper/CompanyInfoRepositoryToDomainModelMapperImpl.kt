package prieto.fernando.data.mapper

import prieto.fernando.data.model.CompanyInfoRepositoryModel
import prieto.fernando.data.model.SampleRepositoryModel
import prieto.fernando.domain.model.CompanyInfoDomainModel
import prieto.fernando.domain.model.SampleDomainModel
import javax.inject.Inject

class CompanyInfoRepositoryToDomainModelMapperImpl @Inject constructor() :
    CompanyInfoRepositoryToDomainModelMapper {
    override fun toDomainModel(companyInfoRepositoryModel: CompanyInfoRepositoryModel) =
        CompanyInfoDomainModel(
            name = companyInfoRepositoryModel.name,
            founder = companyInfoRepositoryModel.founder,
            founded = companyInfoRepositoryModel.founded,
            employees = companyInfoRepositoryModel.employees,
            launchSites = companyInfoRepositoryModel.launchSites,
            valuation = companyInfoRepositoryModel.valuation
        )

    private fun transformAmountInteger(amount: String) = try {
        amount.toInt()
    } catch (exception: Exception) {
        throw UnsupportedOperationException("Amount not transformable $amount")
    }

}

interface CompanyInfoRepositoryToDomainModelMapper {
    fun toDomainModel(companyInfoRepositoryModel: CompanyInfoRepositoryModel): CompanyInfoDomainModel
}
