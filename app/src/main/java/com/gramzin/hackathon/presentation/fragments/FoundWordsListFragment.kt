package com.gramzin.hackathon.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gramzin.hackathon.data.Word
import com.gramzin.hackathon.databinding.FragmentFoundWordsListBinding
import com.gramzin.hackathon.presentation.viewModel.FoundWordsViewModel

class FoundWordsListFragment : Fragment() {
    lateinit var binding: FragmentFoundWordsListBinding
    private val viewModel: FoundWordsViewModel by viewModels{
        FoundWordsViewModel.Factory(requireArguments().getString("root")!!,
            requireArguments().getParcelable<Word>("word")!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoundWordsListBinding.inflate(inflater, container, false)
        viewModel.wordsContext.observe(viewLifecycleOwner){

        }
        return binding.root
    }
}