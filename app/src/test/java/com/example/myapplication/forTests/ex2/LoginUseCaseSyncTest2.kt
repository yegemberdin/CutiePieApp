package com.example.myapplication.forTests.ex2

import com.example.myapplication.forTests.ex2.authtoken.AuthTokenCache
import com.example.myapplication.forTests.ex2.eventbus.EventBusPoster
import com.example.myapplication.forTests.ex2.eventbus.LoggedInEvent
import com.example.myapplication.forTests.ex2.networking.LoginHttpEndpointSync
import com.example.myapplication.forTests.ex2.networking.NetworkErrorException
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Test

class LoginUseCaseSyncTest2 {

    companion object{
        const val USERNAME = "username"
        const val PASSWORD = "password"
        const val AUTH_TOKEN = "authToken"    }


    private var loginHttpEndpointSyncTd = LoginHttpEndpointSyncTd()
    private var authTokenCacheTd = AuthTokenCacheTd()
    private var eventBusPosterTd = EventBusPosterTd()

    private var SUT = LoginUseCaseSync(loginHttpEndpointSync = loginHttpEndpointSyncTd,
        authTokenCache = authTokenCacheTd, eventBusPoster = eventBusPosterTd)
    //stub mock fake

    @Test
    fun loginSync_success_usernameAndPasswordPassedToEndpoint() {
        SUT.loginSync(username = USERNAME, password = PASSWORD)
        assertThat(loginHttpEndpointSyncTd.usernameTd, `is`(USERNAME))
        assertThat(loginHttpEndpointSyncTd.passwordTd, `is`(PASSWORD))
    }

    @Test
    fun loginSync_authError_authTokenNotCached() {
        loginHttpEndpointSyncTd.authError = true
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(authTokenCacheTd.authToken, `is`(""))

    }
    @Test
    fun loginSync_success_loggedInEventPosted() {
        SUT.loginSync(USERNAME, PASSWORD)
        assertThat(eventBusPosterTd.eventTd, `is`(instanceOf(LoggedInEvent::class.java)))
    }



    class LoginHttpEndpointSyncTd: LoginHttpEndpointSync {
        var usernameTd: String? = ""
        var passwordTd: String? = ""
        var authError: Boolean = false
        var serverError: Boolean = false
        var generalError: Boolean = false
        var networkError: Boolean = false

        override fun loginSync(
            username: String?,
            password: String?
        ): LoginHttpEndpointSync.EndpointResult {
            usernameTd = username
            passwordTd = password

            if(authError) {
                return LoginHttpEndpointSync.EndpointResult(LoginHttpEndpointSync.EndpointResultStatus.AUTH_ERROR, "")
            }
            else if (serverError)
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

    class AuthTokenCacheTd: AuthTokenCache {
        var authTokenTd: String? = ""

        override fun cacheAuthToken(authToken: String?) {
            authTokenTd = authToken
        }

        override fun getAuthToken(): String {
            return authTokenTd!!
        }
    }

    class EventBusPosterTd: EventBusPoster {
        var eventTd: Any? = null
        var interactionsCount: Int = 0
        override fun postEvent(event: Any?) {
            interactionsCount++
            eventTd = event
        }
    }


}