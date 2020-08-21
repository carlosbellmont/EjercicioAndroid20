package com.cbellmont.ejercicioandroid20

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_personaje.*

class PersonajeFragment : Fragment() {

    companion object {
        const val CLAVE_1 = "myClave1"

        fun getFragment(personaje: Personaje, mainInterface : MainActivityFragmentInterface) : PersonajeFragment {
            return PersonajeFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(CLAVE_1,personaje )
                    setListener(mainInterface)
                }
            }
        }
    }

    lateinit var personaje : Personaje
    private var mainInterface: MainActivityFragmentInterface? = null

    fun setListener(mainInterface: MainActivityFragmentInterface) {
        this.mainInterface = mainInterface
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainInterface?.onMainActivityAttached()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        personaje = arguments?.getSerializable(CLAVE_1) as Personaje
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_personaje, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iwFoto.setImageResource(personaje.image)
        tvNombre.text = personaje.nombre
        tvRaza.text = personaje.raza
    }

    override fun onDetach() {
        mainInterface?.onMainActivityDetached()
        super.onDetach()
    }

}