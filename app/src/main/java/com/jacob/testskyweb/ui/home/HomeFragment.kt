package com.jacob.testskyweb.ui.home

import androidx.core.widget.NestedScrollView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.jacob.testskyweb.base.BaseFragment
import com.jacob.testskyweb.databinding.FragmentHomeBinding
import com.jacob.testskyweb.models.FilmModel
import com.jacob.testskyweb.utils.gone
import com.jacob.testskyweb.utils.visible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private var page: Int = 1
    private val limit: Int = 10
    private val arrayListData: MutableList<FilmModel> = mutableListOf()

    override fun setUpView() {
        binding.apply {
            scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                    page++
                    binding.progressBar.visible()
                    homeViewModel.getFilms(page, limit)
                }
            })
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    override fun setUpViewModel() {
        homeViewModel.getFilms(page, limit)
        homeViewModel.state.observe(viewLifecycleOwner, { films ->
            films.forEach {
                arrayListData.add(it)
            }
            binding.recyclerView.adapter = HomeAdapter().also {
                it.setDataInList(arrayListData)
            }
        })
        homeViewModel.isLoading.observe(viewLifecycleOwner, {
            if (!it) binding.progressBar.gone()
        })

    }
}