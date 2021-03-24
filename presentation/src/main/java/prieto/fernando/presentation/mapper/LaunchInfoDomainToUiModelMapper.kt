package prieto.fernando.presentation.mapper

import prieto.fernando.domain.model.CompanyInfoDomainModel
import prieto.fernando.domain.model.LaunchDomainModel
import prieto.fernando.presentation.model.CompanyInfoUiModel
import prieto.fernando.presentation.model.LaunchUiModel
import prieto.fernando.presentation.model.LinksUiModel
import prieto.fernando.presentation.model.RocketUiModel
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class LaunchInfoDomainToUiModelMapperImpl @Inject constructor(
    private val dateTransformer: DateTransformer
): LaunchInfoDomainToUiModelMapper {
    override fun toUiModel(launchesDomainModel: List<LaunchDomainModel>): List<LaunchUiModel> =
        launchesDomainModel.map { launchDomainModel ->
            val linksUiModel = LinksUiModel(
                missionPatchSmall = launchDomainModel.links.missionPatchSmall,
                wikipedia = launchDomainModel.links.wikipedia,
                videoLink = launchDomainModel.links.videoLink
            )

            val rocketUiModel = RocketUiModel(
                rocketName = launchDomainModel.rocket.rocketName,
                rocketType = launchDomainModel.rocket.rocketType
            )

            LaunchUiModel(
                missionName = launchDomainModel.missionName,
                launchDate = dateTransformer.dateToDateString(launchDomainModel.launchDate),
                rocket = rocketUiModel,
                links = linksUiModel,
                launchSuccess = launchDomainModel.launchSuccess,
                isPastLaunch = dateTransformer.isPast(launchDomainModel.launchDate),
                differenceOfDays = dateTransformer.getDifferenceOfDays(launchDomainModel.launchDate)
            )
        }

    }



interface LaunchInfoDomainToUiModelMapper {
    fun toUiModel(
        launchesDomainModel: List<LaunchDomainModel>
    ): List<LaunchUiModel>

}
