package com.decwujot.mclowicz.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.Observer
import com.decwujot.mclowicz.data.model.Dish
import com.decwujot.mclowicz.databinding.ActivityMclowiczBinding
import com.decwujot.mclowicz.utility.Resource
import com.decwujot.mclowicz.utility.reObserve

@AndroidEntryPoint
class McLowiczActivity : AppCompatActivity() {

    private val viewModel: McLowiczViewModel by viewModels()
    lateinit var binding: ActivityMclowiczBinding
    lateinit var mcLowiczAdapter: McLowiczAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMclowiczBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initObservers()
    }

    private fun initRecyclerView() {
        mcLowiczAdapter = McLowiczAdapter()
        binding.apply {
            recyclerView.apply {
                adapter = mcLowiczAdapter
            }
        }
    }

    private fun initObservers() {
        with(viewModel) {
            dishes.reObserve(
                    this@McLowiczActivity,
                    dishesObserver()
            )
        }
    }

    private fun dishesObserver(): Observer<Resource<List<Dish>>> {
        return Observer { result ->
            mcLowiczAdapter.submitList(result.data)
            binding.progressBar.isVisible =
                    result is Resource.Loading && result.data.isNullOrEmpty()
            binding.errorMessage.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
            binding.errorMessage.text = result.error?.localizedMessage
        }
    }
}