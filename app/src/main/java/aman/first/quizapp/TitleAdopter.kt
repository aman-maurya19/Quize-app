package aman.first.quizapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TitleAdopter(
    private val titleList: List<Title>,
    private val onItemClick: (Title) -> Unit
) : RecyclerView.Adapter<TitleAdopter.TitleHolder>() {

    class TitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.quiz_title)
        val titleImage: ImageView = itemView.findViewById(R.id.logo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quiz_type, parent, false)
        return TitleHolder(view)
    }

    override fun onBindViewHolder(holder: TitleHolder, position: Int) {
        val item = titleList[position]

        holder.titleText.text = item.subject
        holder.titleImage.setImageResource(item.image)   // ⬅️ drawable se image set

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = titleList.size
}
