package fernando.prieto.data_remote.data

import fernando.prieto.data_remote.ApiService
import fernando.prieto.data_remote.mapper.CompanyInfoResponseToRepositoryMapper
import fernando.prieto.data_remote.mapper.LaunchesInfoResponseToRepositoryMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import prieto.fernando.data.SpaceXRemoteSource
import prieto.fernando.data.model.CompanyInfoRepositoryModel
import prieto.fernando.data.model.LaunchRepositoryModel
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class SpaceXRemoteSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val companyInfoRepoMapper: CompanyInfoResponseToRepositoryMapper,
    private val launchInfoRepoMapper: LaunchesInfoResponseToRepositoryMapper

) : SpaceXRemoteSource {
    private val companyInfoChannel = ConflatedBroadcastChannel<CompanyInfoRepositoryModel>()
    private val launchInfoChannel = ConflatedBroadcastChannel<List<LaunchRepositoryModel>>()


    override suspend fun getCompanyInfo(): Flow<CompanyInfoRepositoryModel> {

        val companyInfoResponse = apiService.getCompanyInfo()
        val companyInfoRepositoryModel =
            companyInfoRepoMapper.toRepositoryModel(companyInfoResponse)
        companyInfoChannel.offer(companyInfoRepositoryModel)
        return companyInfoChannel.asFlow()
    }

    override suspend fun getAllLaunches(): Flow<List<LaunchRepositoryModel>> {
        val launchInfoResponse = apiService.getAllLaunches()
        val launchRepositoryModel = launchInfoRepoMapper.toRepositoryModel(launchInfoResponse)
        launchInfoChannel.offer(launchRepositoryModel)
        return launchInfoChannel.asFlow()
    }
}