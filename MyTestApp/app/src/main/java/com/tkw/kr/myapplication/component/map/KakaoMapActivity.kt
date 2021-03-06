package com.tkw.kr.myapplication.component.map

import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.tkw.kr.myapplication.R
import com.tkw.kr.myapplication.base.BaseView
import com.tkw.kr.myapplication.core.factory.MyProviderFactory
import kotlinx.android.synthetic.main.activity_kakaomap.*
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class KakaoMapActivity: BaseView<MapViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_kakaomap
    override lateinit var viewModel: MapViewModel

    override fun initView() {
        viewModel = ViewModelProvider(this, MyProviderFactory()).get(MapViewModel::class.java)
        val mapView = MapView(this)
        val mapViewContainer = map_view_kakao as ViewGroup
        mapViewContainer.addView(mapView)
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.541, 126.986), 9, true)
    }

    override fun initObserver() {

    }

    override fun initListener() {

    }
}