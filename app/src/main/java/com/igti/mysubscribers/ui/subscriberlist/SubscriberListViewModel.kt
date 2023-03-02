package com.igti.mysubscribers.ui.subscriberlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.igti.mysubscribers.data.db.entity.SubscriberEntity
import com.igti.mysubscribers.repository.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberListViewModel(
    private val repository: SubscriberRepository
): ViewModel() {

    private val _allSubscribersEvent = MutableLiveData<List<SubscriberEntity>>()
    val allSubscribersEvent: LiveData<List<SubscriberEntity>>
        get() = _allSubscribersEvent

    private val _deleteSubscribersEvent = MutableLiveData<Unit>()
    val deleteSubscribersEvent: LiveData<Unit>
        get() = _deleteSubscribersEvent

    fun getSubscribers() = viewModelScope.launch {
        try {
            repository.deleteAllSubscribers()
            _deleteSubscribersEvent.postValue(Unit)
        } catch (ex: Exception) {
            Log.e(TAG, ex.toString())
        }
    }

    companion object {
        private val TAG = SubscriberListViewModel::class.java.simpleName
    }
}