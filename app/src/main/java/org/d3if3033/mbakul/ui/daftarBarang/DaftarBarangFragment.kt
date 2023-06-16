package org.d3if3033.mbakul.ui.daftarBarang

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if3033.mbakul.databinding.FragmentDaftarBarangBinding
import org.d3if3033.mbakul.network.DaftarBarangApi

class DaftarBarangFragment: Fragment() {

    private val viewModel: DaftarBarangViewModel by lazy {
        ViewModelProvider(this)[DaftarBarangViewModel::class.java]
    }

    private lateinit var binding: FragmentDaftarBarangBinding
    private lateinit var myAdapter: DaftarBarangAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentDaftarBarangBinding.inflate(layoutInflater, container, false)
        myAdapter = DaftarBarangAdapter()
        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(context,
                    RecyclerView.VERTICAL)
            )
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner){
            myAdapter.updateData(it)
        }
        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }
    }
    private fun updateProgress(status: DaftarBarangApi.ApiStatus) {
        when (status) {
            DaftarBarangApi.ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            DaftarBarangApi.ApiStatus.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
            }
            DaftarBarangApi.ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}