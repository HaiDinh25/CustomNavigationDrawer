package com.haidv.customnavigationdrawer.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.haidv.customnavigationdrawer.R
import com.haidv.customnavigationdrawer.adapter.DailyActivityAdapter
import com.haidv.customnavigationdrawer.databinding.FragmentHomeBinding
import com.haidv.customnavigationdrawer.models.DailyActivity
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private var adapter: DailyActivityAdapter? = null
    private var listDay = ArrayList<DailyActivity>()

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        getDayOfWeek()
        initUI()
        return binding?.root
    }

    private fun getDayOfWeek(){
        val date: LocalDate = LocalDate.now()
        for (i in 0..6) {
            val dateLocal: LocalDate = date.minusDays(i.toLong())
            val formatDate = dateLocal.format(DateTimeFormatter.ISO_LOCAL_DATE)

            val text = formatDate.substring(5, 7)
            val num = formatDate.substring(8, 10)
            val day = DailyActivity(text, num)
            listDay.add(day)
        }
    }

    private fun initUI() {
        binding?.let {
            adapter = DailyActivityAdapter(listDay)

            it.recycleDaily.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            it.recycleDaily.itemAnimator = DefaultItemAnimator()
            it.recycleDaily.adapter = adapter
        }
    }

}