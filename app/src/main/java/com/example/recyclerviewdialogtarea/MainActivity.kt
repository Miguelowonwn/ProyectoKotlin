package com.example.recyclerviewdialogtarea

import Adapter
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvCardViews: RecyclerView
    private lateinit var rvAdapter: Adapter
    private lateinit var cvCambiarColor: CardView

    private lateinit var h1Barra: CardView
    private lateinit var h2Barra: CardView
    private lateinit var h3Barra: CardView

    private lateinit var listaCardviews: MutableList<BarraClass>

    // Hago un diccionario con todos los colores según su opacidad
    private val colorMap = mapOf(
        20 to mapOf(
            "Negro" to R.color.black20,
            "Blanco" to R.color.white20,
            "Rojo" to R.color.Rojo20,
            "Naranja" to R.color.Naranja20,
            "Amarillo" to R.color.Amarillo20,
            "Verde" to R.color.Verde20,
            "Cián" to R.color.Cian20,
            "Azul" to R.color.Azul20,
            "Violeta" to R.color.Violeta20
        ),
        35 to mapOf(
            "Negro" to R.color.black35,
            "Blanco" to R.color.white35,
            "Rojo" to R.color.Rojo35,
            "Naranja" to R.color.Naranja35,
            "Amarillo" to R.color.Amarillo35,
            "Verde" to R.color.Verde35,
            "Cián" to R.color.Cian35,
            "Azul" to R.color.Azul35,
            "Violeta" to R.color.Violeta35
        ),
        50 to mapOf(
            "Negro" to R.color.black50,
            "Blanco" to R.color.white50,
            "Rojo" to R.color.Rojo50,
            "Naranja" to R.color.Naranja50,
            "Amarillo" to R.color.Amarillo50,
            "Verde" to R.color.Verde50,
            "Cián" to R.color.Cian50,
            "Azul" to R.color.Azul50,
            "Violeta" to R.color.Violeta50
        ),
        65 to mapOf(
            "Negro" to R.color.black65,
            "Blanco" to R.color.white65,
            "Rojo" to R.color.Rojo65,
            "Naranja" to R.color.Naranja65,
            "Amarillo" to R.color.Amarillo65,
            "Verde" to R.color.Verde65,
            "Cián" to R.color.Cian65,
            "Azul" to R.color.Azul65,
            "Violeta" to R.color.Violeta65
        ),
        80 to mapOf(
            "Negro" to R.color.black80,
            "Blanco" to R.color.white80,
            "Rojo" to R.color.Rojo80,
            "Naranja" to R.color.Naranja80,
            "Amarillo" to R.color.Amarillo80,
            "Verde" to R.color.Verde80,
            "Cián" to R.color.Cian80,
            "Azul" to R.color.Azul80,
            "Violeta" to R.color.Violeta80
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializo una lista con variables de tipo BarraClass
        listaCardviews = mutableListOf(
            BarraClass("V1 (20%)", ContextCompat.getColor(this, R.color.cardView1), View.generateViewId(), 20),
            BarraClass("V2 (35%)", ContextCompat.getColor(this, R.color.cardView2), View.generateViewId(), 35),
            BarraClass("V3 (50%)", ContextCompat.getColor(this, R.color.cardView3), View.generateViewId(), 50),
            BarraClass("V4 (65%)", ContextCompat.getColor(this, R.color.cardView4), View.generateViewId(), 65),
            BarraClass("V5 (80%)", ContextCompat.getColor(this, R.color.cardView5), View.generateViewId(), 80)
        )

        initComponents()
        initListeners()
    }

    private fun initComponents() {
        rvCardViews = findViewById(R.id.rvCardViews)
        rvCardViews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Inicializo el adapter y manejo los updates
        rvAdapter = Adapter(listaCardviews) { position, updatedColor ->
            // Update the specific BarraClass item with the new color
            listaCardviews[position].colorCardView = updatedColor
            rvAdapter.notifyItemChanged(position)
        }

        rvCardViews.adapter = rvAdapter

        cvCambiarColor = findViewById(R.id.cvCambiarColor)
        h1Barra = findViewById(R.id.cvH1)
        h2Barra = findViewById(R.id.cvH2)
        h3Barra = findViewById(R.id.cvH3)
    }

    private fun initListeners() {
        cvCambiarColor.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_colores)

        val btnAplicarColor: CardView = dialog.findViewById(R.id.cvAplicarColor)
        val rgBarras: RadioGroup = dialog.findViewById(R.id.rgBarras)
        val rgColores: RadioGroup = dialog.findViewById(R.id.rgColores)

        btnAplicarColor.setOnClickListener {
            val selectedIdBarras = rgBarras.checkedRadioButtonId
            val selectedIdColores = rgColores.checkedRadioButtonId

            // Si cualquiera de los 2 radiogroups no tienen un botón seleccionado, muestra el mensaje
            if (selectedIdBarras == -1 || selectedIdColores == -1) {
                Toast.makeText(this, "Por favor, selecciona una barra y un color", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedRadioButtonBarras: RadioButton = dialog.findViewById(selectedIdBarras)
            val selectedRadioButtonColores: RadioButton = dialog.findViewById(selectedIdColores)

            val baseColorName = selectedRadioButtonColores.text.toString()

            when (selectedRadioButtonBarras.text) {
                getString(R.string.dialog_h1) -> {
                    val opacityLevel = 20 // Example, change this dynamically if needed
                    applyColorToBarra(h1Barra, opacityLevel, baseColorName)
                }
                getString(R.string.dialog_h2) -> {
                    val opacityLevel = 50 // Example, change this dynamically if needed
                    applyColorToBarra(h2Barra, opacityLevel, baseColorName)
                }
                getString(R.string.dialog_h3) -> {
                    val opacityLevel = 80 // Example, change this dynamically if needed
                    applyColorToBarra(h3Barra, opacityLevel, baseColorName)
                }
                // Manejo de items BarraClass
                getString(R.string.dialog_v1) -> updateBarraClassItem(0, baseColorName)
                getString(R.string.dialog_v2) -> updateBarraClassItem(1, baseColorName)
                getString(R.string.dialog_v3) -> updateBarraClassItem(2, baseColorName)
                getString(R.string.dialog_v4) -> updateBarraClassItem(3, baseColorName)
                getString(R.string.dialog_v5) -> updateBarraClassItem(4, baseColorName)
            }
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun applyColorToBarra(barra: CardView, opacityLevel: Int, baseColorName: String) {
        if (colorMap[opacityLevel]?.containsKey(baseColorName) == true) {
            val colorResId = colorMap[opacityLevel]?.get(baseColorName)
            if (colorResId != null) {
                barra.setCardBackgroundColor(ContextCompat.getColor(this, colorResId))
            }
        }
    }



    private fun updateBarraClassItem(position: Int, baseColorName: String) {
        val opacityPercentage = listaCardviews[position].opacity

        // Obtengo el ID del color del diccionario
        val colorResId = colorMap[opacityPercentage]?.get(baseColorName) ?: R.color.black

        // Obtengo el color
        val color = ContextCompat.getColor(this, colorResId)

        // Actualizo el color
        listaCardviews[position].colorCardView = color
        rvAdapter.notifyItemChanged(position)
    }

}
