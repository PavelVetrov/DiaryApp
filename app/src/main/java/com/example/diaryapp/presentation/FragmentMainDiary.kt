package com.example.diaryapp.presentation


import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.diaryapp.R
import com.example.diaryapp.databinding.FragmentMainDiaryBinding
import javax.inject.Inject


class FragmentMainDiary : Fragment() {

    private lateinit var binding: FragmentMainDiaryBinding
    private lateinit var diaryListAdapter: DiaryListAdapter
    private lateinit var viewModal: ViewModalMainDiary
    private lateinit var preferences: SharedPreferences
    private var prefColorTheme = DEFAULT_NUMBER

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
    ): View {
        binding = FragmentMainDiaryBinding.inflate(layoutInflater, container, false)
        viewModal = ViewModelProvider(this, viewModalFactory)[ViewModalMainDiary::class.java]

        preferences = requireActivity().getSharedPreferences(PREFERENCES_APP, Context.MODE_PRIVATE)

        viewModal.diaryList.observe(viewLifecycleOwner) {
            diaryListAdapter.submitList(it)
        }
        setupTheme()
        recyclerLaunch()

        binding.buttonAddShopItem.setOnClickListener {
            launchAddFragment()
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setupTheme() {

        prefColorTheme = preferences.getInt(BACKGROUND_THEME, DEFAULT_NUMBER)

        when (prefColorTheme) {

            0 -> themeChoose(0)
            1 -> themeChoose(1)
            2 -> themeChoose(2)
            3 -> themeChoose(3)
            4 -> themeChoose(4)
        }
    }

    private fun recyclerLaunch() {
        val rvItemDiary = binding.rvDiary
        diaryListAdapter = DiaryListAdapter()

        rvItemDiary.adapter = diaryListAdapter

        setupShotClick()
        setupDeleteItem()
    }

    private fun setupShotClick() {

        diaryListAdapter.onDiaryItemShotClick = {

            val diaryItemId = it.id
            findNavController().navigate(
                FragmentMainDiaryDirections.actionFragmentMainDiaryToFragmentEditDiary(diaryItemId)
            )
        }
    }

    private fun setupDeleteItem() {

        diaryListAdapter.onDiaryItemDeleteClick = {
            viewModal.deleteItem(it)
        }
    }

    private fun launchAddFragment() {

        findNavController().navigate(R.id.action_fragmentMainDiary_to_fragmentAddDiary)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.theme_change -> dialogChoose()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun dialogChoose() {

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Choose a theme")
        val theme = arrayOf("Dark", "Blue", "Purple", "Light pink", "White classic")
        builder.setItems(theme) { _, which ->
            when (which) {
                0 -> {
                    themeChoose(0)
                    prefSaveTheme(0)
                }
                1 -> {
                    themeChoose(1)
                    prefSaveTheme(1)
                }
                2 -> {
                    themeChoose(2)
                    prefSaveTheme(2)
                }
                3 -> {
                    themeChoose(3)
                    prefSaveTheme(3)
                }
                4 -> {
                    themeChoose(4)
                    prefSaveTheme(4)
                }
            }
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun prefSaveTheme(themeInt: Int) {

        preferences.edit().putInt(BACKGROUND_THEME, themeInt).apply()

    }

    private fun themeChoose(themeColor: Int) {

        when (themeColor) {
            0 -> binding.root.setBackgroundResource(R.drawable.background_black).apply {
                binding.buttonAddShopItem.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.dark_gray
                    )
                )
                (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(
                    ColorDrawable(
                        ContextCompat.getColor(
                            requireActivity(),
                            R.color.dark_gray
                        )
                    )
                )
            }

            1 -> binding.root.setBackgroundResource(R.drawable.background_blue).apply {
                binding.buttonAddShopItem.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.dark_blue
                    )
                )
                (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(
                    ColorDrawable(
                        ContextCompat.getColor(
                            requireActivity(),
                            R.color.dark_blue
                        )
                    )
                )
            }
            2 -> binding.root.setBackgroundResource(R.drawable.backgound_purpel).apply {
                binding.buttonAddShopItem.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.purple_200
                    )
                )
                (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(
                    ColorDrawable(
                        ContextCompat.getColor(
                            requireActivity(),
                            R.color.purple_200
                        )
                    )
                )
            }
            3 -> binding.root.setBackgroundResource(R.drawable.background_white).apply {
                binding.buttonAddShopItem.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.light_pink
                    )
                )
                (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(
                    ColorDrawable(
                        ContextCompat.getColor(
                            requireActivity(),
                            R.color.light_pink
                        )
                    )
                )
            }
            4 -> binding.root.setBackgroundResource(R.color.white).apply {
                binding.buttonAddShopItem.backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireActivity(),
                            R.color.light_gray
                        )
                    )
                (activity as AppCompatActivity).supportActionBar?.setBackgroundDrawable(
                    ColorDrawable(
                        ContextCompat.getColor(
                            requireActivity(),
                            R.color.light_gray
                        )
                    )
                )
            }
        }

    }

    companion object {
        const val PREFERENCES_APP = "preferences"
        const val BACKGROUND_THEME = "background theme"
        const val DEFAULT_NUMBER = 4
    }
}