package org.d3if3033.mbakul.ui.hitung

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3033.mbakul.R
import org.d3if3033.mbakul.databinding.FragmentHitungBinding
import org.d3if3033.mbakul.db.UntungDb
import org.d3if3033.mbakul.model.HasilUntung

class HitungFragment : Fragment() {

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(R.id.action_hitungFragment_to_aboutFragment)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private lateinit var binding : FragmentHitungBinding

    private val viewModel: HitungViewModel by lazy {
        val db = UntungDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { HasilUntung() }
        binding.shareButton.setOnClickListener { shareData() }
        viewModel.getHasilUntung().observe(requireActivity(), { showResult(it) })
    }

    private fun shareData() {
        val message = getString(R.string.bagikan_template)
        binding.totalTextView.text
    }
    private val shareIntent = Intent(Intent.ACTION_SEND)
//    shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
//    if (shareIntent.resolveActivity(
//    requireActivity().packageManager) != null) {
//        startActivity(shareIntent)
//    }


    private fun HasilUntung() {
        val angkaPenjualan = binding.hasilPenjualanInp.text.toString()
        if (TextUtils.isEmpty(angkaPenjualan)){
            Toast.makeText(context, R.string.input_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val modalJualan = binding.modalInp.text.toString()
        if (TextUtils.isEmpty(modalJualan)){
            Toast.makeText(context, R.string.input_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val hargaJual = binding.hargaJualInp.text.toString()
        if (TextUtils.isEmpty(hargaJual)){
            Toast.makeText(context, R.string.input_invalid, Toast.LENGTH_LONG).show()
            return
        }
        viewModel.hitungUntung(
            angkaPenjualan.toFloat(),
            modalJualan.toFloat(),
            hargaJual.toFloat()
        )
    }

    @SuppressLint("StringFormatMatches")
    private fun showResult(result: HasilUntung?){
        if (result == null) return
        binding.totalTextView.text = getString(R.string.total_x, result.untung)
        binding.notifikasiTersimpan.text = getString(R.string.notifikasiTersimpan)
    }
}