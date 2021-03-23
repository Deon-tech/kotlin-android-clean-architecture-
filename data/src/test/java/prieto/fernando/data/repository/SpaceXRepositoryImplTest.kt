package prieto.fernando.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
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
import prieto.fernando.data.SpaceXRemoteSource
import prieto.fernando.data.mapper.CompanyInfoRepositoryToDomainModelMapper

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SpaceXRepositoryImplTest {
    private lateinit var cut: SpaceXRepositoryImpl

    @Mock
    lateinit var spaceXRemoteSource: SpaceXRemoteSource

    @Mock
    lateinit var companyInfoDomainMapper: CompanyInfoRepositoryToDomainModelMapper

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        cut =
            SpaceXRepositoryImpl(spaceXRemoteSource, companyInfoDomainMapper)
    }

    @Test
    fun `When getSample then sampleRemoteSource invoked`() {
        runBlockingTest {
            // When
            whenever(spaceXRemoteSource.getSample()).thenReturn(mock())

            cut.getSample()

            // Then
            verify(spaceXRemoteSource, times(1)).getSample()
        }
    }
}
