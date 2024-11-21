

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdialogtarea.BarraClass
import com.example.recyclerviewdialogtarea.R
import com.example.recyclerviewdialogtarea.ViewHolder

class Adapter(
    private val cardviews: MutableList<BarraClass>,
    private val onBarraClicked: (Int, Int) -> Unit // Callback para actualizar los colores
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cardviews.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(cardviews[position])

        // Paso el color actializado al callback cuando el item es clicado
        holder.itemView.setOnClickListener {
            onBarraClicked(position, cardviews[position].colorCardView)
        }
    }
}
