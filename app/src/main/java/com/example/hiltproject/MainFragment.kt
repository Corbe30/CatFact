package com.example.hiltproject

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import com.example.hiltproject.ViewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment(): Fragment() {

    companion object {
        fun newInstance(
            catFactCount: Int
        ): MainFragment {
            return MainFragment().apply {
                val bundle = Bundle()
                bundle.putInt(MainActivity.CAT_FACT, catFactCount)
                arguments = bundle
            }
        }
    }

    private var catFactCount: Int = 1
    @Inject
    lateinit var viewModel : MainViewModel

    override fun onCreateView(
        inflater : LayoutInflater,
        container : ViewGroup?,
        savedInstanceState : Bundle?
    ) : View? {

//        initViewModel()
        initData()

        return ComposeView(requireContext()).apply {
            setContent {
                MaterialTheme() {
                    CatFactScreen(modifier = Modifier, viewModel)
                }
            }
        }
    }

//    private fun initViewModel(){
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//    }

    private fun initData(){
        catFactCount = arguments?.getInt(MainActivity.CAT_FACT, 1) ?: 1
        viewModel.getCatFact() // catFactCount
    }
}