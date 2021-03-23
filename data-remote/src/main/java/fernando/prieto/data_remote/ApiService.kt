package fernando.prieto.data_remote

import fernando.prieto.data_remote.model.LaunchesResponse
import kotlinx.coroutines.flow.Flow
import prieto.fernando.data_api.model.CompanyInfoResponse
import retrofit2.http.GET

interface ApiService {
    @GET("info")
    suspend fun getCompanyInfo(): CompanyInfoResponse

    @GET("launches")
    suspend fun getAllLaunches(): List<LaunchesResponse>
}
