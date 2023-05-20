package org.d3if3033.mbakul.ui.histori

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3033.mbakul.R
import org.d3if3033.mbakul.databinding.ItemHistoriBinding
import org.d3if3033.mbakul.db.UntungEntity
import org.d3if3033.mbakul.model.hitungUntung
import java.text.SimpleDateFormat
import java.util.*


class HistoriAdapter : ListAdapter<UntungEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UntungEntity>() {
            override fun areContentsTheSame(oldData: UntungEntity, newData: UntungEntity): Boolean {
                return oldData.id == newData.id
            }

            override fun areItemsTheSame(oldData: UntungEntity, newData: UntungEntity): Boolean {
                return oldData == newData
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

        @SuppressLint("StringFormatMatches")
        fun bind(item: UntungEntity) = with(binding) {
            val hasilUntung = item.hitungUntung()

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            untungTextView.text = root.context.getString(R.string.total_x, hasilUntung.untung)
        }
    }
}