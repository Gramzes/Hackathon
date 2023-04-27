package com.gramzin.hackathon.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gramzin.hackathon.R
import com.gramzin.hackathon.databinding.FragmentTopWordsBinding
import com.gramzin.hackathon.presentation.adapters.WordListAdapter
import com.gramzin.hackathon.presentation.viewModel.TopWordsViewModel

class TopWordsFragment : Fragment() {
    lateinit var binding: FragmentTopWordsBinding
    lateinit var adapter: WordListAdapter
    val viewModel: TopWordsViewModel by viewModels{
        TopWordsViewModel.Factory(requireActivity().application, requireArguments().getString("root")!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopWordsBinding.inflate(inflater, container, false)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = WordListAdapter {
            val string = requireArguments().getString("root")!!
            val bundle = bundleOf("root" to string, "word" to it)
            findNavController().navigate(R.id.action_topWordsFragment_to_foundWordsListFragment, bundle)
        }
        binding.recyclerView.adapter = adapter
        viewModel.words.observe(viewLifecycleOwner){
            adapter.addWords(it)
        }
        return binding.root
    }

}