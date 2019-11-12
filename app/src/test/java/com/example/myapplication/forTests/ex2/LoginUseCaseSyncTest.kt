package com.example.myapplication.forTests.ex2

import com.example.myapplication.forTests.ex2.authtoken.AuthTokenCache
import com.example.myapplication.forTests.ex2.eventbus.EventBusPoster
import com.example.myapplication.forTests.ex2.eventbus.LoggedInEvent
import com.example.myapplication.forTests.ex2.networking.LoginHttpEndpointSync
import com.example.myapplication.forTests.ex2.networking.NetworkErrorException
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Test

class LoginUseCaseSyncTest {

    companion object {
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val AUTH_TOKEN = "authToken"
    }

    private var loginHttpEndPintSyncTd = LoginHttpEndPintSyncTd()
    private var authTokenCacheTd = AuthTokenCacheTd()
    private var eventBusPosterTd = EventBusPosterTd()
    private val SUT = LoginUseCaseSync(
        loginHttpEndpointSync = loginHttpEndPintSyncTd,
        authTokenCache = authTokenCacheTd, eventBusPoster = eventBusPosterTd
    )

    //test that pass and username are passed succesfully to endpoint
    @Test
    fun loginSync_success_usernameAndPasswordaPassedToEndpoint() {
        SUT.loginSync(username = USERNAME, password = PASSWORD)
        assertThat(loginHttpEndPintSyncTd.loginUsername, `is`(USERNAME))
        assertThat(loginHttpEndPintSyncTd.loginPassword, `is`(PASSWORD))

    }

    @Test
    fun loginSync_success_authTokenCached() {
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(authTokenCacheTd.authToken, `is`(AUTH_TOKEN))
    }

    @Test
    fun loginSync_generalError_authTokenNotCached() {
        loginHttpEndPintSyncTd.generalError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(authTokenCacheTd.authToken, `is`(""))
    }

    @Test
    fun loginSync_success_loggedInEventPosted() {
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(eventBusPosterTd.eventTd, `is`(instanceOf(LoggedInEvent::class.java)))
    }

    @Test
    fun loginSync_generalError_noInteractionWithEventBusPoster() {
        loginHttpEndPintSyncTd.generalError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(eventBusPosterTd.interactionsCount, `is`(0))
    }
    @Test
    fun loginSync_success_successReturned() {
        val res = SUT.loginSync(USERNAME, PASSWORD)
        assertThat(res, `is`(LoginUseCaseSync.UseCaseResult.SUCCESS))
    }
    //helperclasses
    class LoginHttpEndPintSyncTd : LoginHttpEndpointSync {
        var loginUsername: String? = ""
        var loginPassword: String? = ""
        var authError: Boolean = false
        var serverError: Boolean = false
        var generalError: Boolean = false
        var networkError: Boolean = false

        @Throws(NetworkErrorException::class)
        override fun loginSync(
            username: String?,
            password: String?
        ): LoginHttpEndpointSync.EndpointResult {
            loginUsername = username
            loginPassword = password

            if (authError) {
                return LoginHttpEndpointSync.EndpointResult(
                    LoginHttpEndpointSync.EndpointResultStatus.AUTH_ERROR,
                    ""
                )
            } else if (serverError)
                return LoginHttpEndpointSync.EndpointResult(
                    LoginHttpEndpointSync.EndpointResultStatus.SERVER_ERROR, ""
                )
            else if (generalError) {
                return LoginHttpEndpointSync.EndpointResult(
                    LoginHttpEndpointSync.EndpointResultStatus.GENERAL_ERROR,
                    ""
                )

            } else if (networkError) {
                throw NetworkErrorException()

            } else {
                return LoginHttpEndpointSync.EndpointResult(
                    LoginHttpEndpointSync.EndpointResultStatus.SUCCESS,
                    AUTH_TOKEN
                )
            }


        }
    }

    class AuthTokenCacheTd : AuthTokenCache {
        //fake td

        var authTokenTd: String? = ""

        override fun cacheAuthToken(authToken: String?) {
            authTokenTd = authToken
        }

        override fun getAuthToken(): String {
            return authTokenTd!!
        }
    }

    class EventBusPosterTd : EventBusPoster {
        var eventTd: Any? = null
        var interactionsCount: Int = 0
        override fun postEvent(event: Any?) {
            interactionsCount++
            eventTd = event
        }
    }
}