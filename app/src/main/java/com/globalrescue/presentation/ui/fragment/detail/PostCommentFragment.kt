package com.globalrescue.presentation.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.globalrescue.data.entity.PostCommentsModel
import com.globalrescue.databinding.FragmentPostCommentBinding
import com.globalrescue.presentation.adapter.PostCommentAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class PostCommentFragment : Fragment() {

    private var _binding: FragmentPostCommentBinding? = null
    private lateinit var postCommentAdapter: PostCommentAdapter


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

    }

    private fun intializeAdapter() {

        val listType: Type = object : TypeToken<ArrayList<PostCommentsModel>>() {}.type
        val postCommentList = Gson().fromJson<ArrayList<PostCommentsModel>>(arguments?.getString("postCommentsList"), listType)
        postCommentAdapter = PostCommentAdapter(postCommentList)
        binding.rv.adapter = postCommentAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}