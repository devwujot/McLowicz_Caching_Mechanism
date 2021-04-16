package com.decwujot.mclowicz.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.decwujot.mclowicz.data.local.McLowiczRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class McLowiczViewModel @Inject constructor(
        repository: McLowiczRepository
) : ViewModel() {

    val dishes = repository.getDishes().asLiveData()
}