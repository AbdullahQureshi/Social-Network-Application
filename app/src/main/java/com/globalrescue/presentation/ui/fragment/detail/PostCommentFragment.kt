package com.globalrescue.presentation.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.globalrescue.R
import com.globalrescue.core.utils.ProgressDialog
import com.globalrescue.core.utils.SnackBar
import com.globalrescue.data.entity.PostCommentsModel
import com.globalrescue.databinding.FragmentPostCommentBinding
import com.globalrescue.domain.ProgressBarState
import com.globalrescue.domain.UIComponent
import com.globalrescue.presentation.adapter.PostCommentAdapter
import com.globalrescue.presentation.ui.fragment.favourites.FavouritesEvent
import com.globalrescue.presentation.ui.fragment.favourites.FavouritesUIState
import com.globalrescue.presentation.ui.fragment.favourites.FavouritesViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Type


@AndroidEntryPoint
class PostCommentFragment : Fragment() {

    private var _binding: FragmentPostCommentBinding? = null
    private lateinit var postCommentAdapter: PostCommentAdapter
    private val favouritesViewModel by viewModels<FavouritesViewModel>()
    private var postId: Int = 0
    private var isFavourites: Boolean = false

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPostCommentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intializeAdapter()
        setClickListener()
        observeViewModel()

        favouritesViewModel.onEvent(FavouritesEvent.isFavourites(postId))

    }

    private fun setClickListener() {
        binding.floatingActionButton.setOnClickListener {

            if (isFavourites) {
                favouritesViewModel.onEvent(FavouritesEvent.setFavourites(postId, false))
            } else {
                favouritesViewModel.onEvent(FavouritesEvent.setFavourites(postId, true))

            }

        }

    }

    private fun observeViewModel() {

        favouritesViewModel.uiState.observe(viewLifecycleOwner, Observer {
            when (it) {

                is FavouritesUIState.FavouritesSetSuccess -> {

                    if (it.boolean) {
                        SnackBar.showSnackBar(requireActivity(), R.string.favourites_success_message)

                    } else {
                        SnackBar.showSnackBar(requireActivity(), R.string.favourites_error_message)

                    }

                    setFavourites(it.boolean)

                }

                is FavouritesUIState.isFavouritesFetched -> {

                    setFavourites(it.boolean)
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
                        else -> {}
                    }

                }

                else -> {}
            }
        })

    }


    private fun setFavourites(isFavourites: Boolean) {

        this.isFavourites = isFavourites

        if (isFavourites) {
            binding.floatingActionButton.setImageDrawable(getDrawable(requireContext(), R.drawable.ic_favorite))
        } else {
            binding.floatingActionButton.setImageDrawable(getDrawable(requireContext(), R.drawable.ic_unchecked_favorite))

        }
    }

    private fun intializeAdapter() {

        val listType: Type = object : TypeToken<ArrayList<PostCommentsModel>>() {}.type
        val postCommentList = Gson().fromJson<ArrayList<PostCommentsModel>>(arguments?.getString("postCommentsList"), listType)
        postId = postCommentList.get(0).postId
        postCommentAdapter = PostCommentAdapter(postCommentList)
        binding.rv.adapter = postCommentAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}