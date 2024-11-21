package com.example.recyclerviewdialogtarea

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val cvBarraVertical: CardView = view.findViewById(R.id.cvBarra)
    private val textView: TextView = view.findViewById(R.id.tvTextoBarraVertical)

    fun render(cardView: BarraClass) {
        cvBarraVertical.setCardBackgroundColor(cardView.colorCardView)
        textView.text = cardView.label
    }

    fun getCardView(): CardView {
        return cvBarraVertical
    }
}
