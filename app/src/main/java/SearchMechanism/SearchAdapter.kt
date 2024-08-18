package SearchMechanism

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.try2.R

class SearchAdapter(private val userList: ArrayList<UserDataClass>)
    : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    var onItemClick: ((UserDataClass) -> Unit)? = null
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.name_search)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_adapter, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = userList[position]

        // Set the name in the TextView
        holder.nameTextView.text = user.name

        // Set up the click listener
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(user)
        }
    }
}
