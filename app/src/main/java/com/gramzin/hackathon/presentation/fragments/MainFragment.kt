package com.gramzin.hackathon.presentation.fragments

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gramzin.hackathon.R
import com.gramzin.hackathon.data.BookService
import com.gramzin.hackathon.databinding.FragmentMainBinding
import com.gramzin.hackathon.java.RootFinder
import com.gramzin.hackathon.java.aot.WordformMeaning
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val chooseFileButton = binding.chooseFileButton
        chooseFileButton.setOnClickListener {
            downloadFile()
        }
        return binding.root
    }

    private val documentPicker = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data

            uri?.let {
                val cursor: Cursor = requireContext().getContentResolver().query(uri, null, null, null, null)!!
                var displayName = ""
                try {
                    if (cursor != null && cursor.moveToFirst()) {
                        val colInd = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        displayName = cursor.getString(colInd)
                    }
                } finally {
                    cursor.close()
                }
                val extension = displayName.substringAfterLast('.')
                if (extension == "fb2" || extension == "epub" || extension == "txt") {
                    val job = Job()
                    val coroutineScope = CoroutineScope(job)
                    coroutineScope.launch(Dispatchers.Main) {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.chooseFileButton.isEnabled = false
                        val bookText = BookService.getTextByUri(uri, requireContext())
                        if (WordformMeaning.archiveIS == null) {
                            val bfReader = requireActivity().application
                                .assets.open("root/roots.txt").bufferedReader()
                            WordformMeaning.archiveIS = requireActivity().application
                                .assets.open("root/MRD.BIN")
                            WordformMeaning.initMeth()
                            RootFinder.txtFile = bfReader
                            RootFinder.initMeth()
                        }
                        binding.chooseFileButton.isEnabled = true
                        binding.progressBar.visibility = View.GONE
                        val bundle = bundleOf("root" to bookText)
                        findNavController().navigate(R.id.action_mainFragment_to_topWordsFragment, bundle)
                    }
                }else{
                    Toast.makeText(requireContext(),
                        requireContext().getText(R.string.wrong_extension),
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                openSelectDocIntent()
            } else {
                Toast.makeText(requireContext(),
                    requireContext().getText(R.string.permsion_denied), Toast.LENGTH_LONG).show()
            }
        }



    private fun downloadFile(){
        when {
            Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ->{
                openSelectDocIntent()
            }
            ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                openSelectDocIntent()
            }
            else -> {
                requestPermissionLauncher.launch(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun openSelectDocIntent(){
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "*/*"
        }
        documentPicker.launch(intent)
    }

}