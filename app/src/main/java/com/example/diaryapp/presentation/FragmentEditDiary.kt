package com.example.diaryapp.presentation

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.diaryapp.R
import com.example.diaryapp.databinding.FragmentEditDiaryBinding
import com.example.diaryapp.domain.DiaryItem
import com.example.diaryapp.presentation.viewmodal.ViewModalEditDiary
import com.example.diaryapp.presentation.viewmodal.ViewModalFactory
import java.util.*
import javax.inject.Inject


class FragmentEditDiary : Fragment() {

    private lateinit var binding: FragmentEditDiaryBinding
    private lateinit var viewModal: ViewModalEditDiary
    private val args: FragmentEditDiaryArgs by navArgs()
    private var diaryItemId: Int = DiaryItem.UNDEFINED_ID
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
        binding = FragmentEditDiaryBinding.inflate(layoutInflater, container, false)
        viewModal = ViewModelProvider(this, viewModalFactory)[ViewModalEditDiary::class.java]

        setupDate()

        diaryItemId = args.diaryItemId
        viewModal.getDiaryItem(diaryItemId)
        viewModal.diaryItem.observe(viewLifecycleOwner) {
            date = it.date
            binding.editText.setText(it.text)
            binding.editTitle.setText(it.title)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.save_nemu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_button -> saveDiaryItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupDate() {

        binding.buttonCalendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance()
            val getDate = calendar.set(dayOfMonth, year, month)
            date = getDate.toString()
            binding.buttonCalendarView.visibility = View.GONE
            binding.chooseTitle.visibility = View.GONE
        }
    }

    private fun saveDiaryItem() {

        val title = binding.editTitle.text.toString()
        val text = binding.editText.text.toString()
        viewModal.editDiaryItem(
            text = text,
            title = title,
            date = date
        )
        findNavController().popBackStack()
    }
}