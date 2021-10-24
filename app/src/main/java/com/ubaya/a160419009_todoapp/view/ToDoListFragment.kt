package com.ubaya.a160419009_todoapp.view

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.a160419009_todoapp.R
import com.ubaya.a160419009_todoapp.model.Todo
import com.ubaya.a160419009_todoapp.viewmodel.ListTodoViewModel
import kotlinx.android.synthetic.main.fragment_to_do_list.*


class ToDoListFragment : Fragment() {
    private lateinit var viewModel:ListTodoViewModel
    private var todoListAdapter:TodoListAdapter = TodoListAdapter(arrayListOf(),
        {item -> doClick(item)})

    fun doClick(item:Any){
        viewModel.clearTask(item as Todo)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListTodoViewModel::class.java)
        viewModel.refresh()

        recToDoList.layoutManager = LinearLayoutManager(context)
        recToDoList.adapter = todoListAdapter

        fabAdd.setOnClickListener {
            val action = ToDoListFragmentDirections.actionCreateToDo()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            todoListAdapter.updateTodoList(it)
            with(txtEmpty){
                if(it.isEmpty()){
                    visibility = View.VISIBLE
                } else {
                    visibility = View.GONE
                }
            }

        })
    }

}