package com.example.myapplication.forTests.ex2

import com.example.myapplication.forTests.ex2.authtoken.AuthTokenCache
import com.example.myapplication.forTests.ex2.eventbus.EventBusPoster
import com.example.myapplication.forTests.ex2.networking.LoginHttpEndpointSync
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseSyncTestMock {

    companion object {
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val AUTH_TOKEN = "authToken"
    }


   @Mock
   private lateinit var loginHttpEndPintSyncMock: LoginHttpEndpointSync

    private val authTokenCacheMock = mock(AuthTokenCache::class.java)
    private val eventBusPosterMock = mock(EventBusPoster::class.java)

//
//    @Mock
//    private lateinit var eventBusPosterMock: EventBusPoster

    private lateinit var SUT: LoginUseCaseSync

//    private val SUT = LoginUseCaseSync(loginHttpEndPintSyncMock,authTokenCacheMock,eventBusPosterMock)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = LoginUseCaseSync(loginHttpEndPintSyncMock,authTokenCacheMock,eventBusPosterMock)
        success()
         }

    @Test
    fun loginSync_success_usernameAndPasswordPassedToEndpoint() {
        val ac: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)
        SUT.loginSync(USERNAME, PASSWORD)
        verify(loginHttpEndPintSyncMock, times(1)).loginSync(ac.capture(),ac.capture())
        val list = ac.allValues
        assertThat(list.get(0),`is`(USERNAME))
        assertThat(list.get(1),`is`(PASSWORD))

    }

    private fun success() {
        `when`(loginHttpEndPintSyncMock.loginSync(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(LoginHttpEndpointSync.EndpointResult(LoginHttpEndpointSync.EndpointResultStatus.SUCCESS, AUTH_TOKEN))

    }



}