package org.wit.couries.ui.client

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import org.wit.couries.R
import org.wit.couries.adapters.CouriesAdapter
import org.wit.couries.databinding.FragmentClientBinding
import org.wit.couries.main.CouriesApp
import org.wit.couries.models.CouriesModel
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import org.wit.couries.adapters.CouriesClickListener


class ClientFragment : Fragment(), CouriesClickListener {

    lateinit var app: CouriesApp
    private var _binding: FragmentClientBinding? = null
    private val binding get() = _binding!!
    private lateinit var clientViewModel: ClientViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClientBinding.inflate(inflater, container, false)

        val root: View = binding.root
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
//        val clientViewModel =
//            ViewModelProvider(this).get(ClientViewModel::class.java)

        clientViewModel = ViewModelProvider(this).get(ClientViewModel::class.java)
        clientViewModel.observableCouriesList.observe(viewLifecycleOwner, Observer{
            couries ->
            couries?.let {render(couries)}
        })




        val textView: TextView = binding.clientsNotFound
        clientViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main_client, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun render (couriesList:List<CouriesModel>){
        binding.recyclerView.adapter = CouriesAdapter(couriesList, this)
        if (couriesList.isEmpty()){
            binding.recyclerView.visibility = View.GONE
            binding.clientsNotFound.visibility = View.VISIBLE
        }else{
            binding.recyclerView.visibility = View.VISIBLE
            binding.clientsNotFound.visibility = View.GONE
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        clientViewModel.load()
    }

    override fun onCouriesClick(couries: CouriesModel) {
        val action =ClientFragmentDirections.actionNavClientToNavNewClient()
        findNavController().navigate(action)
    }


}