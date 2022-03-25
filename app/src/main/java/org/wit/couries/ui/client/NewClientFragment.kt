package org.wit.couries.ui.client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.wit.couries.R
import org.wit.couries.databinding.FragmentNewClientBinding
import org.wit.couries.models.CouriesModel

class NewClientFragment : Fragment() {

    private var _binding: FragmentNewClientBinding? = null

    private val binding get() = _binding!!
    private lateinit var clientViewModel: ClientViewModel



    override fun onCreate (savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         clientViewModel =
            ViewModelProvider(this).get(ClientViewModel::class.java)

        _binding = FragmentNewClientBinding.inflate(inflater, container, false)
        val root: View = binding.root

        clientViewModel.observableStatus.observe(viewLifecycleOwner, Observer { status -> status?.let {render (status)} })

        // Inflate the layout for this fragment
        setAddButtonListener(binding)
      //  return inflater.inflate(R.layout.fragment_new_client, container, false)
        return root;
    }

    private fun render(status: Boolean) {
        when (status) {
            true -> {
                view?.let {
                    //Uncomment this if you want to immediately return to Report
                    //findNavController().popBackStack()
                }
            }
            false -> Toast.makeText(context,getString(R.string.couriesError),Toast.LENGTH_LONG).show()
        }
    }

    fun setAddButtonListener(layout:FragmentNewClientBinding){

        layout.clientButton.setOnClickListener{
            val name = layout.clientAddName.text
            val address = layout.clientAddAddress.text
            val contacts = layout.clientAddNumber.text.toString().toInt()

            clientViewModel.addCouries(CouriesModel(clientName = name.toString(), clientAddress = address.toString(), clientNumber = contacts))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
    }

}