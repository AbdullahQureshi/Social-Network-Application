package com.globalrescue.presentation.ui.fragment.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.globalrescue.R
import com.globalrescue.core.utils.ProgressDialog
import com.globalrescue.core.utils.SnackBar
import com.globalrescue.databinding.FragmentFavouritesBinding
import com.globalrescue.domain.ProgressBarState
import com.globalrescue.domain.UIComponent
import com.globalrescue.presentation.adapter.FavouritesAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val favouritesViewModel by viewModels<FavouritesViewModel>()
    private lateinit var favouritesAdapter: FavouritesAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intializeAdapter()

        favouritesViewModel.onEvent(FavouritesEvent.GetFavourites)

        observeViewModel()

    }


    private fun intializeAdapter() {
        favouritesAdapter = FavouritesAdapter(arrayListOf())
        binding.rv.adapter = favouritesAdapter
    }


    private fun observeViewModel() {

        favouritesViewModel.uiState.observe(viewLifecycleOwner, Observer {

            when (it) {

                is FavouritesUIState.FavouritesFetched -> {

                    if (it.postList.isNotEmpty()) {

                        favouritesAdapter.updateList(ArrayList(it.postList))

                    } else {

                        SnackBar.showSnackBar(requireActivity(), R.string.empty_error_message)
                    }

                }

                is FavouritesUIState.Loading -> {

                    when (it.progressBarState) {
                        is ProgressBarState.Loading -> {
                            ProgressDialog.showProgressDialog(requireContext())
                        }
                        is ProgressBarState.Idle -> {
                            ProgressDialog.hideProgressBar()
                        }

                    }
                }

                is FavouritesUIState.Error -> {


                    when (it.uiComponent) {
                        is UIComponent.SnackBar -> {
                            SnackBar.showSnackBar(requireActivity(), it.uiComponent.message)

                        }

                    }

                }

                else -> {}
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}