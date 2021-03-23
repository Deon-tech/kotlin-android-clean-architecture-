package prieto.fernando.data.mapper

import prieto.fernando.data.model.CompanyInfoRepositoryModel
import prieto.fernando.data.model.LaunchRepositoryModel
import prieto.fernando.domain.model.CompanyInfoDomainModel
import prieto.fernando.domain.model.LaunchDomainModel
import prieto.fernando.domain.model.LinksDomainModel
import prieto.fernando.domain.model.RocketDomainModel
import javax.inject.Inject


class LaunchesRepositoryToDomainModelMapperImpl @Inject constructor() :
    LaunchesRepositoryToDomainModelMapper {
    override fun toDomainModel(
        launchRepositoryModel: List<LaunchRepositoryModel>
    ): List<LaunchDomainModel> =
        launchRepositoryModel.map { launchRepositoryModel ->
            val linksDomainModel = LinksDomainModel(
                missionPatchSmall = launchRepositoryModel.links.missionPatchSmall,
                wikipedia = launchRepositoryModel.links.wikipedia,
                videoLink = launchRepositoryModel.links.videoLink
            )

            val rocketDomainModel = RocketDomainModel(
                rocketName = launchRepositoryModel.rocket.rocketName,
                rocketType = launchRepositoryModel.rocket.rocketType
            )

            LaunchDomainModel(
                missionName = launchRepositoryModel.missionName,
                launchDate = launchRepositoryModel.launchDateLocal,
                rocket = rocketDomainModel,
                links = linksDomainModel,
                launchSuccess = launchRepositoryModel.launchSuccess
            )
        }

}

interface LaunchesRepositoryToDomainModelMapper {
    fun toDomainModel(launchRepositoryModel: List<LaunchRepositoryModel>): List<LaunchDomainModel>
}
