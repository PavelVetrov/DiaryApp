package com.example.diaryapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.diaryapp.R
import com.example.diaryapp.databinding.FragmentAddDiaryBinding
import com.example.diaryapp.domain.DiaryItem
import com.example.diaryapp.presentation.viewmodal.ViewModalAddDiary
import com.example.diaryapp.presentation.viewmodal.ViewModalFactory
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class FragmentAddDiary : Fragment() {

    private lateinit var binding: FragmentAddDiaryBinding
    private lateinit var viewModal: ViewModalAddDiary
    private var date: String = ""

    @Inject
    lateinit var viewModalFactory: ViewModalFactory

    private val component by lazy {
        (requireActivity().application as ApplicationDiary).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDiaryBinding.inflate(layoutInflater, container, false)
        viewModal = ViewModelProvider(this, viewModalFactory)[ViewModalAddDiary::class.java]

        setupDate()

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_nemu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_button -> addDiaryItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addDiaryItem() {

        val title = binding.editTitle.text.toString()
        val text = binding.editText.text.toString()
        viewModal.addDiaryItem(
            DiaryItem(
                title = title,
                text = text,
                date = date
            )
        )
        findNavController().popBackStack()
    }

    private fun setupDate() {

        binding.buttonCalendarView.setOnDateChangeListener(CalendarView.OnDateChangeListener { _, year, month, dayOfMonth ->
            val sdf = SimpleDateFormat("dd/MMMM/yyyy")
            val calendar = Calendar.getInstance()
            calendar[year, month] = dayOfMonth
            date = sdf.format(calendar.time)
            binding.buttonCalendarView.visibility = View.GONE
            binding.chooseTitle.visibility = View.GONE

        })
    }
}