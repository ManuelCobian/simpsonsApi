package com.example.simpsons.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.network.network.models.ApiModel
import com.example.simpsons.databinding.ActivityMainBinding
import com.example.simpsons.ui.adapters.dif.HomeroAdapter
import com.example.simpsons.viewmodels.MainViewModel
import com.google.android.gms.ads.AdRequest
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), HomeroAdapter.CallbackClick {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var adapters: HomeroAdapter

    @Inject
    lateinit var laM: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBanner()
        setUpViewModel()
        setupRecyclerView()
        setUpObervers()
    }

    private fun setupBanner() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun setUpViewModel() {
        val vm: MainViewModel by viewModels()
        viewModel = vm
        viewModel.getResponse()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = laM
            adapter = this@MainActivity.adapters
        }
    }

    private fun setUpObervers() {
        viewModel.onSubModuleListChange().observe(this){
            adapters.submitList(it)
        }
    }

    override fun onItemClicked(module: ApiModel) {
        TODO("Not yet implemented")
    }
}