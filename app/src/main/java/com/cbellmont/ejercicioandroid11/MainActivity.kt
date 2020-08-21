package com.cbellmont.ejercicioandroid11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

interface MainActivityAdapterInterface {
    fun onPersonajeCliked(personaje: Personaje)
}

interface MainActivityFragmentInterface {
    fun onMainActivityAttached()
    fun onMainActivityDetached()

}


class MainActivity : AppCompatActivity(), MainActivityAdapterInterface, MainActivityFragmentInterface {

    private lateinit var adapter : PersonajesAdapter
    private val listaPersonajes = loadData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createRecyclerView()
    }

    private fun createRecyclerView() {
        adapter = PersonajesAdapter(this, listaPersonajes)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }


    private fun loadData() : List<Personaje>{
        val personaje1 = Personaje("Aragorn", "Humano", R.mipmap.aragorn, true)
        val personaje2 = Personaje("Gandalf", "Mago", R.mipmap.gandalf, true)
        val personaje3 = Personaje("Boromir", "Humano", R.mipmap.boromir, true)
        val personaje4 = Personaje("Legolas", "Elfo", R.mipmap.legolas, true)
        val personaje5 = Personaje("Orco Feo", "Orco", R.mipmap.orco, false)
        val personaje6 = Personaje("Smagu", "Dragon", R.mipmap.smagu, false)

        return mutableListOf(personaje1,personaje2,personaje3,personaje4,personaje5,personaje6).apply { shuffle() }
    }

    override fun onPersonajeCliked(personaje: Personaje) {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frameLayout, PersonajeFragment.getFragment(personaje, this))
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onMainActivityAttached() {
        recyclerView.visibility = View.GONE
        frameLayout.visibility = View.VISIBLE
    }

    override fun onMainActivityDetached() {
        recyclerView.visibility = View.VISIBLE
        frameLayout.visibility = View.GONE
    }
}