package com.example.simpsons.di

import android.app.Activity
import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpsons.ui.adapters.dif.HomeroAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ActivityComponent::class)
@Module
object AdapterModule
{
    @Provides
    fun provideLayoutManager(@ApplicationContext context: Context): RecyclerView.LayoutManager =
        LinearLayoutManager(context)
}

@Module
@InstallIn(ActivityComponent::class)
object YearsModule {
    @Provides
    fun provideCallback(activity: Activity) =
        activity as HomeroAdapter.CallbackClick
}