package com.aridev.applokionboarding.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.aridev.applokionboarding.databinding.ActivityMainBinding
import com.aridev.applokionboarding.ui.adapter.AdapterDots
import com.aridev.applokionboarding.ui.adapter.AdapterOnboarding
import com.aridev.applokionboarding.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModels()

    private val adapterOnboarding = AdapterOnboarding(listOf())

    private val adapterDots = AdapterDots(listOf())

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setAdapter()
        setListeners()
        setViewPagerCallback()
        setObservers()
    }

    private fun setListeners() {
        binding.btnNext.setOnClickListener {
            binding.vpOnboarding.currentItem = binding.vpOnboarding.currentItem + 1
        }
    }

    private fun setViewPagerCallback() {
        binding.vpOnboarding.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.setPage(position)
            }
        })
    }

    private fun setAdapter() {
        binding.vpOnboarding.adapter = adapterOnboarding
        binding.rvDots.adapter = adapterDots
    }

    private fun setObservers() {
        viewModel.viewState.observe(this) {
            updateView(it)
        }
    }

    private fun updateView(viewState: MainViewModel.ViewState) {

        ObjectAnimator.ofFloat(binding.textTitle, View.ALPHA,0f,1f).apply {
            duration = 1000
            addUpdateListener {
                if(it.animatedFraction == 0f) {
                    binding.textTitle.text = viewState.listOnboarding[viewState.pageSelected].title
                }
            }

            start()
        }

        ObjectAnimator.ofFloat(binding.textBody, View.ALPHA,0f,1f).apply {
            duration = 1000
            addUpdateListener {
                if(it.animatedFraction == 0f) {
                    binding.textBody.text = viewState.listOnboarding[viewState.pageSelected].body
                }
            }

            start()
        }

        updateAdapter(viewState)
    }

    private fun updateAdapter(viewState: MainViewModel.ViewState) {
        adapterOnboarding.list = viewState.listOnboarding
        adapterDots.list = viewState.listDots
        adapterOnboarding.update()
        adapterDots.update()
    }
}