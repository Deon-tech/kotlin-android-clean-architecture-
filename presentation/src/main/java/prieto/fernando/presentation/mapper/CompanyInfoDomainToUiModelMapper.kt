package prieto.fernando.presentation.mapper

import prieto.fernando.domain.model.CompanyInfoDomainModel
import prieto.fernando.domain.model.SampleDomainModel
import prieto.fernando.presentation.model.CompanyInfoUiModel
import prieto.fernando.presentation.model.SampleUiModel
import javax.inject.Inject


class CompanyInfoDomainToUiModelMapperImpl @Inject constructor() :
    CompanyInfoDomainToUiModelMapper {
    override fun toUiModel(companyInfoDomainModel: CompanyInfoDomainModel): CompanyInfoUiModel =
        CompanyInfoUiModel(
            name = companyInfoDomainModel.name,
            founder = companyInfoDomainModel.founder,
            foundedYear = companyInfoDomainModel.founded,
            employees = companyInfoDomainModel.employees,
            launchSites = companyInfoDomainModel.launchSites,
            valuation = companyInfoDomainModel.valuation
        )
}


interface CompanyInfoDomainToUiModelMapper {
    fun toUiModel(companyInfoDomainModel: CompanyInfoDomainModel): CompanyInfoUiModel
}
