package ru.mrrobot1413.test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.map
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mrrobot1413.test.R
import ru.mrrobot1413.test.databinding.FragmentHomeBinding
import ru.mrrobot1413.test.network.viewModels.AlbumViewModel
import ru.mrrobot1413.test.ui.paging.AlbumAdapter


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val albumViewModel by lazy {
        ViewModelProvider(this).get(AlbumViewModel::class.java)
    }
    private val adapter by lazy {
        AlbumAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumViewModel.albums.observe(viewLifecycleOwner, {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                binding?.apply {
                    progressBar.isVisible = loadStates.refresh is LoadState.Loading
                    recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
                    if(loadStates.refresh is LoadState.Error){
                        showSnackbar(getString(R.string.error_occurred))
                    }
                }
            }
        }

        albumViewModel.getAlbums()

        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(
            binding?.recyclerView?.context,
            (binding?.recyclerView?.layoutManager as LinearLayoutManager).orientation
        )
        binding?.recyclerView?.addItemDecoration(dividerItemDecoration)
    }

    private fun showSnackbar(text: String) {
        view?.let {
            Snackbar.make(it, text, Snackbar.LENGTH_INDEFINITE)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}