package com.globalrescue.presentation.ui.fragment.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.globalrescue.core.utils.ProgressDialog
import com.globalrescue.core.utils.SnackBar
import com.globalrescue.databinding.FragmentPostBinding
import com.globalrescue.domain.ProgressBarState
import com.globalrescue.domain.UIComponent
import com.globalrescue.presentation.adapter.ItemClickListener
import com.globalrescue.presentation.adapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null
    private val postViewModel by viewModels<PostViewModel>()
    private lateinit var postAdapter: PostAdapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intializeAdapter()

        postViewModel.onEvent(PostEvent.GetPosts)


        observeViewModel()

    }

    private fun intializeAdapter() {
        postAdapter = PostAdapter(arrayListOf())
        postAdapter.setClickListener(object : ItemClickListener {
            override fun onItemClick(id: Int) {

                postViewModel.onEvent(PostEvent.GetPostComments(1))


            }
        })
        binding.rv.adapter = postAdapter
    }

    private fun observeViewModel() {

        postViewModel.uiState.observe(viewLifecycleOwner, Observer {
            when (it) {


                is PostUIState.PostsFetched -> {

                    postAdapter.updateList(ArrayList(it.postList))

                }

                is PostUIState.Loading -> {

                    when (it.progressBarState) {
                        is ProgressBarState.Loading -> {
                            ProgressDialog.showProgressDialog(requireContext())
                        }
                        is ProgressBarState.Idle -> {
                            ProgressDialog.hideProgressBar()
                        }

                    }
                }

                is PostUIState.Error -> {


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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}