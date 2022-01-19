package com.tkw.kr.myapplication.component.main

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.tkw.kr.myapplication.R
import com.tkw.kr.myapplication.base.BaseView
import com.tkw.kr.myapplication.component.github.GithubActivity
import com.tkw.kr.myapplication.component.map.GoogleMapActivity
import com.tkw.kr.myapplication.core.factory.MyProviderFactory
import com.tkw.kr.myapplication.util.setOnSingleClickListener
import kotlinx.android.synthetic.main.activity_main.*
import java.security.MessageDigest

class MainActivity : BaseView<MainViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main
    override lateinit var viewModel: MainViewModel

    override fun initView() {
        viewModel = ViewModelProvider(this, MyProviderFactory()).get(MainViewModel::class.java)
        getAppKeyHash()
    }

    override fun initObserver() {

    }

    override fun initListener() {
        btn_github.setOnSingleClickListener {
            val githubIntent = Intent(this@MainActivity, GithubActivity::class.java)
            startActivity(githubIntent)
        }

        btn_googlemap.setOnSingleClickListener {
            val googlemapIntent = Intent(this@MainActivity, GoogleMapActivity::class.java)
            startActivity(googlemapIntent)
        }
    }

    private fun getAppKeyHash() {
        try {
            val info: PackageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
            for(i in info.signatures.indices) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(info.signatures[i].toByteArray())
                val something = String(Base64.encode(md.digest(), 0))
                Log.e("Hash key", something)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}