package com.example.myapplication.forTests.ex2

import com.example.myapplication.forTests.ex2.authtoken.AuthTokenCache
import com.example.myapplication.forTests.ex2.eventbus.EventBusPoster
import com.example.myapplication.forTests.ex2.networking.LoginHttpEndpointSync
import net.bytebuddy.implementation.bind.annotation.Argument
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseSyncTestMock2 {

    companion object {
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val AUTH_TOKEN = "authToken"
    }

    @Mock
    private lateinit var loginHttpEndPintSyncMock: LoginHttpEndpointSync

    private val authTokenCacheMock = Mockito.mock(AuthTokenCache::class.java)
    private val eventBusPosterMock = Mockito.mock(EventBusPoster::class.java)
    private lateinit var SUT: LoginUseCaseSync


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = LoginUseCaseSync(loginHttpEndPintSyncMock,authTokenCacheMock,eventBusPosterMock)

     success()

    }

    @Test
    fun loginSync_success_usernameAndPasswordPassedToEndpoint() {
        val ac: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)
        SUT.loginSync(LoginUseCaseSyncTestMock.USERNAME, LoginUseCaseSyncTestMock.PASSWORD)
        Mockito.verify(loginHttpEndPintSyncMock, Mockito.times(1)).loginSync(ac.capture(),ac.capture())
        val list = ac.allValues
        assertThat(list.get(0), CoreMatchers.`is`(LoginUseCaseSyncTestMock.USERNAME))
        assertThat(list.get(1), CoreMatchers.`is`(LoginUseCaseSyncTestMock.PASSWORD))

    }

    private fun success() {
        Mockito.`when`(loginHttpEndPintSyncMock.loginSync(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(LoginHttpEndpointSync.EndpointResult(LoginHttpEndpointSync.EndpointResultStatus.SUCCESS, AUTH_TOKEN))

    }
}