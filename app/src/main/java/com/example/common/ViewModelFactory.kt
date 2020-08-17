package com.example.common

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        providers[modelClass]?.let { return it.get() as T }
        throw IllegalArgumentException("No such provider for " + modelClass.canonicalName)
    }
}

inline fun <reified VM : ViewModel> ViewModelProvider.Factory.getViewModel(activity: FragmentActivity) =
    ViewModelProviders.of(activity, this).get(VM::class.java)