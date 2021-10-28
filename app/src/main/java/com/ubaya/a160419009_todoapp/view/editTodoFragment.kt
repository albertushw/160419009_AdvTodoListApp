package com.ubaya.a160419009_todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ubaya.a160419009_todoapp.R
import com.ubaya.a160419009_todoapp.databinding.FragmentEditToDoBinding
import com.ubaya.a160419009_todoapp.model.Todo
import com.ubaya.a160419009_todoapp.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_to_do.*

class editTodoFragment : Fragment(), RadioClickListener, TodoSaveChangesListener {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding: FragmentEditToDoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentEditToDoBinding>(inflater,R.layout.fragment_edit_to_do, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = editTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        dataBinding.radiolistener = this
        dataBinding.listener = this

//        txtJudulToDo.text = "Edit Todo"
//        btnCreateTodo.text = "Save Changes"

//        btnCreateTodo.setOnClickListener {
//            val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
//            viewModel.update(txtTitle.text.toString(),
//                txtNotes.text.toString(),
//                radio.tag.toString().toInt(), uuid)
//            Toast.makeText(view.context, "Todo Updated", Toast.LENGTH_SHORT).show()
//        }
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo = it
//            txtTitle.setText(it.title)
//            txtNotes.setText(it.notes)
//
//            if(it.priority==3){
//                radioHigh.isChecked=true
//            }else if(it.priority==2){
//                radioMedium.isChecked = true
//            }else{
//                radioLow.isChecked = true
//            }
        })
    }

    override fun onRadioClick(v: View, obj: Todo) {
        obj.priority = v.tag.toString().toInt()
    }

    override fun onTodoSaveChanges(v: View, obj: Todo) {
        viewModel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
    }
}