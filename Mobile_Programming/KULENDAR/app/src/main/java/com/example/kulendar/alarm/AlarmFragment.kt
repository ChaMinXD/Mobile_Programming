package com.example.kulendar.alarm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.kulendar.DB.Alarm
import com.example.kulendar.DB.AlarmDatabase
import com.example.kulendar.Login.MainActivity2
import com.example.kulendar.databinding.FragmentAlarmBinding
import com.google.android.material.tabs.TabLayoutMediator


class AlarmFragment : Fragment() {

    private val TAG="ALARMFragment"

    lateinit var binding: FragmentAlarmBinding

    //탭타이틀 설정
    private val tabTitleArray = arrayListOf(
        "알림 추가",
        "주요 알리미"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container:
        ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val email = arguments?.getString("EMAIL")!!
        Log.d("알람 이메일 ","${email} 도착")
        // Inflate the layout for this fragment
        binding = FragmentAlarmBinding.inflate(inflater, container, false)

        //탭레이아웃과 뷰페이저 연결
        val alarmAdapter = AlarmVPAdapter(this, email)
        binding.alarmViewPager.adapter = alarmAdapter
        TabLayoutMediator(binding.alarmTabLayout, binding.alarmViewPager) { tab, position ->
            tab.text = tabTitleArray[position]

        }.attach()

        val t1 = AlarmTab1Fragment()
        val t2 = AlarmTab2Fragment()

        (requireContext() as MainActivity2).setDataAtFragment(t1, email)
        (requireContext() as MainActivity2).setDataAtFragment(t2, email)

        return binding.root //setContentView
    }

}