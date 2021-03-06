package com.tkw.kr.myapplication.component.github

import com.tkw.kr.myapplication.core.network.MainApiServer
import com.tkw.kr.myapplication.core.network.MainApiServer.toSingle
import com.tkw.kr.myapplication.core.network.base.NetResult
import com.tkw.kr.myapplication.core.network.base.NetResultCallback
import io.reactivex.Single

interface GithubModel {
    suspend fun getReposCoroutine(query: String): NetResult<GithubRepos>             //코루틴
    fun getReposCallback(query: String, callback: NetResultCallback<GithubRepos>)  //콜백
    fun getReposSingle(query: String): Single<GithubRepos>                       //Single
}

class GithubModelImpl: GithubModel {
    override suspend fun getReposCoroutine(query: String): NetResult<GithubRepos> {
        return MainApiServer.parsingResponseSuspend {
            MainApiServer.API.getRepositories(query)
        }
    }

    override fun getReposCallback(query: String, callback: NetResultCallback<GithubRepos>) {
        MainApiServer.parsingResponse(MainApiServer.API.getRepositories2(query), callback)
    }

    override fun getReposSingle(query: String): Single<GithubRepos> {
        return MainApiServer.API.getRepositories2(query).toSingle()
    }
}