package prieto.fernando.presentation

import android.location.Criteria
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import prieto.fernando.core.event.Event
import prieto.fernando.core.event.eventOf
import prieto.fernando.domain.usecase.*
import prieto.fernando.presentation.mapper.CompanyInfoDomainToUiModelMapper
import prieto.fernando.presentation.mapper.LaunchInfoDomainToUiModelMapper
import prieto.fernando.presentation.model.CompanyInfoUiModel
import prieto.fernando.presentation.model.LaunchUiModel
import javax.inject.Inject

private const val TAG = "FirstViewModel"

abstract class FirstViewModel @ViewModelInject constructor() : ViewModel() {
    abstract fun companyInfo()
    abstract fun launches(yearFilterCriteria: Int = -1, ascendantOrder: Boolean = false)
    abstract fun openLink(link: String)
    abstract fun onFilterClicked()

    abstract val companyInfo: LiveData<CompanyInfoUiModel>
    abstract val launches: LiveData<List<LaunchUiModel>>
    abstract val loadingHeader: LiveData<Boolean>
    abstract val loadingBody: LiveData<Boolean>
    abstract val headerError: LiveData<Event<Unit>>
    abstract val bodyError: LiveData<Event<Unit>>
    abstract val openLink: LiveData<Event<String>>
    abstract val showDialog: LiveData<Event<Unit>>
}

@ExperimentalCoroutinesApi
@FlowPreview
class FirstViewModelImpl @Inject constructor(
   private val getCompanyInfo: GetCompanyInfo,
   private val getLaunchInfo: GetLaunchInfo,
   private val companyInfoDomainToUiModelMapper: CompanyInfoDomainToUiModelMapper,
   private val launchInfoDomainToUiModelMapper: LaunchInfoDomainToUiModelMapper

) : FirstViewModel() {
    private val openLinkChannel = ConflatedBroadcastChannel<Event<String>>()
    private val showDialogChannel = ConflatedBroadcastChannel<Event<Unit>>()


    override val openLink: LiveData<Event<String>>
        get() = openLinkChannel.asFlow().asLiveData()
    override val showDialog: LiveData<Event<Unit>>
        get() = showDialogChannel.asFlow().asLiveData()

    private val _headerError = MediatorLiveData<Event<Unit>>()
    override val headerError: LiveData<Event<Unit>>
        get() = _headerError


    private val _bodyError = MediatorLiveData<Event<Unit>>()
    override val bodyError: LiveData<Event<Unit>>
        get() = _bodyError

    private val _loadingHeader = MediatorLiveData<Boolean>()
    override val loadingHeader: LiveData<Boolean>
        get() = _loadingHeader

    private val _loadingBody = MediatorLiveData<Boolean>()
    override val loadingBody: LiveData<Boolean>
        get() = _loadingBody

    private val _companyInfo = MediatorLiveData<CompanyInfoUiModel>()
    override val companyInfo: LiveData<CompanyInfoUiModel>
        get() = _companyInfo

    private val _launches = MediatorLiveData<List<LaunchUiModel>>()
    override val launches: LiveData<List<LaunchUiModel>>
        get() = _launches

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, exception.localizedMessage ?: "")
        _loadingBody.value = false
        _loadingHeader.value = false
    }

    override fun companyInfo() {
        viewModelScope.launch(errorHandler) {
            _loadingHeader.value = true
            getCompanyInfo.execute()
                .catch { throwable ->
                    Log.d(TAG, throwable.localizedMessage ?: "")
                    _headerError.postValue(eventOf(Unit))
                    _loadingHeader.value = false
                }
                .collect { companyDomainModel ->
                    val companyInfoUiModel =
                        companyInfoDomainToUiModelMapper.toUiModel(companyDomainModel)
                    _companyInfo.postValue(companyInfoUiModel)
                    _loadingHeader.value = false
                }
        }    }

    override fun launches(yearFilterCriteria: Int, ascendantOrder: Boolean) {
        viewModelScope.launch(errorHandler) {
            _loadingHeader.value = true
            getLaunchInfo.execute(yearFilterCriteria, ascendantOrder)
                .catch { throwable ->
                    Log.d(TAG, throwable.localizedMessage ?: "")
                    _headerError.postValue(eventOf(Unit))
                    _loadingHeader.value = false
                }
                .collect { launchesDomainModel ->
                    val launchUiModel =
                        launchInfoDomainToUiModelMapper.toUiModel(launchesDomainModel)
                    _launches.postValue(launchUiModel)
                    _loadingHeader.value = false
                }
        }    }

    override fun openLink(link: String) {
        openLinkChannel.offer(eventOf(link))
    }

    override fun onFilterClicked() {
        showDialogChannel.offer(eventOf(Unit))
    }


}
