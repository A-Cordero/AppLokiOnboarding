package com.aridev.applokionboarding.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aridev.applokionboarding.R
import com.aridev.applokionboarding.model.ItemOnBoarding

class MainViewModel : ViewModel() {

    private val data = listOf<ItemOnBoarding>(
        ItemOnBoarding(image = R.drawable.photo1, title = "The Variant", body = "Mobius puts Loki to work, but not everyone at TVA is thrilled about the God of Mischief's presence."),
        ItemOnBoarding(image = R.drawable.photo2, title = "The Nexus Event", body = "Frayed nerves and paranoia infiltrate the TVA as Mobius and Hunter B-15 search for Loki and Sylvie."),
        ItemOnBoarding(image = R.drawable.photo3, title = "Journey Into Mystery", body = "Loki tries to escape The Void, a desolate purgatory where he meets variant versions of himself."),
    )

    private val dots = mutableListOf<Boolean>(
        true,
        false,
        false
    )

    private val viewStatePrivate = MutableLiveData<ViewState>()
    val viewState : LiveData<ViewState> get() = viewStatePrivate

    private fun currentState() = viewStatePrivate.value!!

    init {
        viewStatePrivate.value = ViewState()
        fetchData()
    }

    fun setPage(position : Int) {
        currentState().listDots[currentState().pageSelected] = false
        currentState().listDots[position] = true
        viewStatePrivate.value = currentState().copy(pageSelected = position)
    }

    private fun fetchData() {
        viewStatePrivate.value = currentState().copy(listOnboarding = data, listDots = dots)
    }

    data class ViewState(
        var listOnboarding : List<ItemOnBoarding> = listOf(),
        var pageSelected : Int = 0,
        var listDots : MutableList<Boolean> = mutableListOf()
    )
}