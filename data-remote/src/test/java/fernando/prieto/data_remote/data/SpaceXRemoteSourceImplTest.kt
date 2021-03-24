package fernando.prieto.data_remote.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import fernando.prieto.data_remote.ApiService
import fernando.prieto.data_remote.mapper.CompanyInfoResponseToRepositoryMapper
import fernando.prieto.data_remote.mapper.LaunchesInfoResponseToRepositoryMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import prieto.fernando.core_android_test.MainCoroutineRule


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SpaceXRemoteSourceImplTest {

    private lateinit var cut: SpaceXRemoteSourceImpl

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var companyInfoRepositoryMapper: CompanyInfoResponseToRepositoryMapper

    @Mock
    lateinit var launchInfoRepositoryMapper: LaunchesInfoResponseToRepositoryMapper



    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        cut = SpaceXRemoteSourceImpl(
            apiService,
            companyInfoRepositoryMapper,
            launchInfoRepositoryMapper

        )
    }

    @Test
    fun `When getCompanyInfo then apiService invoked`() {
        runBlockingTest {
            // When
            whenever(apiService.getCompanyInfo()).thenReturn(mock())

            cut.getCompanyInfo()

            // Then
            verify(apiService, times(1)).getCompanyInfo()
        }
    }

    @Test
    fun `When getAllLaunches then apiService invoked`() {
        runBlockingTest {
            // When
            whenever(apiService.getAllLaunches()).thenReturn(mock())

            cut.getAllLaunches()

            // Then
            verify(apiService, times(1)).getAllLaunches()
        }

}
    // No tests, as there's no logic in the Class Under Test yet

}