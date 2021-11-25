package com.example.zap.presentation.main

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.example.zap.R
import com.example.zap.core.Either
import com.example.zap.presentation.details.DetailsActivity
import com.example.zap.presentation.main.adapter.AdapterListener
import com.example.zap.presentation.main.adapter.ButtonListener
import com.example.zap.presentation.main.adapter.ImmobileAdapter
import com.example.zap.presentation.model.ImmobileVO
import com.example.zap.presentation.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), ButtonListener, AdapterListener {

    companion object {
        val EXTRA_ID = "IMMOBILE_ID"
        private val TAM_PAGE = 20
    }

    private val viewModel: MainViewModel by viewModel()
    private val adapter: ImmobileAdapter by inject()
    private var immobiles = listOf<ImmobileVO>()
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeLivaData()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        rv_immobile.adapter = adapter
        adapter.listenerViva = this
        adapter.listenerZap = this
        adapter.adapterListener = this
        rv_immobile.layoutManager = LinearLayoutManager(this)
    }

    private fun setupButtons() {
        setupPageNavigationVisibility()
        previous_button.setOnClickListener {
            currentPage -= 1
            adapter.setImmobiles(getPage(currentPage))
            scrollToTop()
            setupPageNavigationVisibility()
        }
        next_button.setOnClickListener {
            currentPage += 1
            adapter.setImmobiles(getPage(currentPage))
            scrollToTop()
            setupPageNavigationVisibility()
        }
    }

    private fun scrollToTop() {
        val smoothScroller: SmoothScroller = object : LinearSmoothScroller(this) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }
        smoothScroller.targetPosition = 0
        rv_immobile.layoutManager?.startSmoothScroll(smoothScroller)
    }

    private fun setupPageNavigationVisibility() {
        tv_page_index.text = (currentPage + 1).toString()
        tv_page_index.visibility = View.VISIBLE
        when (currentPage) {
            0 -> {
                previous_button.visibility = View.INVISIBLE
                next_button.visibility = View.VISIBLE
            }
            lastPageIndex() -> {
                previous_button.visibility = View.VISIBLE
                next_button.visibility = View.INVISIBLE
            }
            else -> {
                previous_button.visibility = View.VISIBLE
                next_button.visibility = View.VISIBLE
            }
        }
    }

    private fun subscribeLivaData() {
        viewModel.dataLiveData.observe(
            this,
            {
                hideLoading()
                when (it) {
                    is Either.Left -> {
                        Toast.makeText(
                            this,
                            getString(R.string.error_loading),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is Either.Right -> {
                        immobiles = it.value
                        adapter.setImmobiles(getPage(currentPage))
                        setupButtons()
                    }
                }
            }
        )
    }

    private fun getPage(page: Int): List<ImmobileVO> {
        val firstIndex = 0 + page * TAM_PAGE
        val lastIndex = (page + 1) * TAM_PAGE - 1
        if (lastIndex> immobiles.count()) {
            return immobiles.subList(firstIndex, immobiles.count())
        } else {
            return immobiles.subList(firstIndex, lastIndex)
        }
    }

    private fun lastPageIndex(): Int {
        return immobiles.count() / TAM_PAGE
    }

    override fun onClickButton(view: View) {
        showLoading()
        when (view.id) {
            R.id.iv_logo_zap -> {
                currentPage = 0
                viewModel.getZapData()
            }
            R.id.iv_logo_viva_real -> {
                currentPage = 0
                viewModel.getVivaRealData()
            }
        }
    }

    override fun onSelectedItem(view: View, selectedItem: ImmobileVO) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(EXTRA_ID, selectedItem.id)
        val options = ActivityOptions.makeSceneTransitionAnimation(this, view, ImmobileAdapter.TRANSITION_NAME)
        startActivity(intent, options.toBundle())
    }
}
