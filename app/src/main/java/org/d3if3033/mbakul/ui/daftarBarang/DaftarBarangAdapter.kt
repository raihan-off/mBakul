package org.d3if3033.mbakul.ui.daftarBarang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if3033.mbakul.R
import org.d3if3033.mbakul.databinding.ListItemBinding
import org.d3if3033.mbakul.model.DaftarBarang
import org.d3if3033.mbakul.network.DaftarBarangApi

class DaftarBarangAdapter : RecyclerView.Adapter<DaftarBarangAdapter.ViewHolder>() {

    private val data = mutableListOf<DaftarBarang>()

    fun updateData(newData: List<DaftarBarang>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(daftarBarang: DaftarBarang) = with(binding){
            namaTextView.text = daftarBarang.nama
            hargaTextView.text= daftarBarang.harga
            Glide.with(imageView.context)
                .load(DaftarBarangApi.getDaftarBarangUrl(daftarBarang.imageId))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageView)

            root.setOnClickListener {
//                val message = root.context.getString(R.string.message, hewan.nama)
//                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() : Int {
        return data.size
    }
}