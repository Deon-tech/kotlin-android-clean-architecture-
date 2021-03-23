package fernando.prieto.data_remote.mapper

import dagger.Reusable
import fernando.prieto.data_remote.model.LaunchesResponse
import prieto.fernando.data.model.LaunchRepositoryModel
import prieto.fernando.data.model.LinksRepositoryModel
import prieto.fernando.data.model.RocketRepositoryModel
import javax.inject.Inject

const val DEFAULT_PATCH = "https://images2.imgbox.com/3c/0e/T8iJcSN3_o.png"

@Reusable
class LaunchesInfoResponseToRepositoryModelMapperImpl @Inject constructor(
    private val dateFormatter: DateFormatter
) : LaunchesInfoResponseToRepositoryMapper {
    override fun toRepositoryModel(
        launchesResponse: List<LaunchesResponse>
    ): List<LaunchRepositoryModel> =
        launchesResponse.map { launchResponse ->

            val linksRepositoryModel = LinksRepositoryModel(
                missionPatchSmall = launchResponse.links.missionPatchSmall ?: DEFAULT_PATCH,
                wikipedia = launchResponse.links.wikipedia.orEmpty(),
                videoLink = launchResponse.links.videoLink.orEmpty()
            )

            val rocketRepositoryModel = RocketRepositoryModel(
                rocketName = launchResponse.rocket.rocketName,
                rocketType = launchResponse.rocket.rocketType
            )

            LaunchRepositoryModel(
                missionName = launchResponse.missionName,
                launchDateLocal = dateFormatter.format(launchResponse.launchDate),
                rocket = rocketRepositoryModel,
                links = linksRepositoryModel,
                launchSuccess = launchResponse.launchSuccess

            )

        }
}

interface LaunchesInfoResponseToRepositoryMapper {
    fun toRepositoryModel(launchesResponse: List<LaunchesResponse>): List<LaunchRepositoryModel>
}