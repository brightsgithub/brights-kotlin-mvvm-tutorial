package cleanarcpro.brightowusu.com.cleanarcproj.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cleanarcpro.brightowusu.com.cleanarcproj.MainApplication
import cleanarcpro.brightowusu.com.cleanarcproj.R
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UIPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.row_past_experience.view.*


class PreviousExperiencesAdaptor : RecyclerView.Adapter<
        PreviousExperiencesAdaptor.Companion.PreviousExperiencesViewHolder>(){

    lateinit var onItemClickListener: OnItemClickListener<UIPastExperience>
    var pastExperiences: List<UIPastExperience> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousExperiencesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_past_experience, parent, false)
        return PreviousExperiencesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pastExperiences.size
    }

    override fun onBindViewHolder(holder: PreviousExperiencesViewHolder, position: Int) {
        val previousExp = pastExperiences[position]
        populateRow(holder, previousExp)
    }

    private fun populateRow(
            holder: PreviousExperiencesViewHolder,
            item: UIPastExperience) {


        holder.companyName.text = item.companyName
        holder.roleName.text = item.roleName
        MainApplication.getPicasso().load(item.companyLogo).into(holder.companyLogo)
    }

    companion object {
        class PreviousExperiencesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val companyName = itemView.companyName
            val roleName = itemView.roleName
            val companyLogo = itemView.companyLogo
        }
    }


}