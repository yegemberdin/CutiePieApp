package com.example.myapplication.forTests.ex2

import com.example.myapplication.forTests.ex2.authtoken.AuthTokenCache
import com.example.myapplication.forTests.ex2.eventbus.EventBusPoster
import com.example.myapplication.forTests.ex2.eventbus.LoggedInEvent
import com.example.myapplication.forTests.ex2.networking.LoginHttpEndpointSync
import com.example.myapplication.forTests.ex2.networking.NetworkErrorException

class LoginUseCaseSync {
    enum class UseCaseResult {
        SUCCESS,
        FAILURE,
        NETWORK_ERROR
    }

    private var mLoginHttpEndpointSync: LoginHttpEndpointSync
    private var mAuthTokenCache: AuthTokenCache
    private var mEventBusPoster: EventBusPoster

    constructor(
        loginHttpEndpointSync: LoginHttpEndpointSync,
        authTokenCache: AuthTokenCache,
        eventBusPoster: EventBusPoster
    ) {
        mLoginHttpEndpointSync = loginHttpEndpointSync
        mAuthTokenCache = authTokenCache
        mEventBusPoster = eventBusPoster
    }

    fun loginSync(username: String, password: String): UseCaseResult {
        val endpointEndpointResult: LoginHttpEndpointSync.EndpointResult
        try {
            endpointEndpointResult = mLoginHttpEndpointSync.loginSync(username, password)
        } catch (e: NetworkErrorException) {
            return UseCaseResult.NETWORK_ERROR
        }


        if (isSuccessfulEndpointResult(endpointEndpointResult)) {
            mAuthTokenCache.cacheAuthToken(endpointEndpointResult.getAuthToken())
            mEventBusPoster.postEvent(LoggedInEvent())
            return UseCaseResult.SUCCESS
        } else {
            return UseCaseResult.FAILURE
        }
    }

    private fun isSuccessfulEndpointResult(endpointResult: LoginHttpEndpointSync.EndpointResult): Boolean {
        return endpointResult.getStatus() === LoginHttpEndpointSync.EndpointResultStatus.SUCCESS
    }
}