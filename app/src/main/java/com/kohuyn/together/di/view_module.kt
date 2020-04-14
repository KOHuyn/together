package com.kohuyn.together.di

import com.kohuyn.together.ui.home.HomeViewModel
import com.kohuyn.together.ui.count.CountViewModel
import com.kohuyn.together.ui.home.homefrag.HomeFragViewModel
import com.kohuyn.together.ui.lockscreen.LockScreenViewModel
import com.kohuyn.together.ui.memories.MemoriesViewModel
import com.kohuyn.together.ui.memories.addstory.AddStoryViewModel
import com.kohuyn.together.ui.setting.SettingViewModel
import com.kohuyn.together.ui.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModule :Module = module {
    viewModel { SplashViewModel(get(),get()) }
    viewModel { HomeViewModel(get(),get()) }
    viewModel { HomeFragViewModel(get(),get()) }
    viewModel { CountViewModel(get(),get()) }
    viewModel { MemoriesViewModel(get(),get()) }
    viewModel { SettingViewModel(get(),get()) }
    viewModel { AddStoryViewModel(get(),get()) }
    viewModel { LockScreenViewModel(get(),get()) }
}