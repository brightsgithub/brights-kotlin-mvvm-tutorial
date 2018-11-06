package cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver

import android.net.Uri
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.util.Log


import java.net.URLEncoder
import java.util.*
import java.util.concurrent.TimeUnit
import java.io.*
import android.util.TypedValue
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest


/**
 * Run all tests in a package
 *
 *
 */
open class FakeServer {
    private var mockWebServer: MockWebServer? = null
    private var delay: Long = 0

    fun useFakeServer() {

        // Create a MockWebServer. These are lean enough that you can create a new
        // instance for every unit test.
        mockWebServer = MockWebServer()

        val myDispatcher = MyDispatcher()
        mockWebServer!!.setDispatcher(myDispatcher)
        try {
            // Start the server.
            mockWebServer!!.start(PORT)
        } catch (e: Throwable) {
            throw RuntimeException(e)
        }

    }

    fun setBodyDelayInSeconds(delay: Long) {
        this.delay = delay
    }

    /**
     * By default MockWebServer uses a queue to specify a series of responses. Use a Dispatcher
     * to handle requests using another policy. One natural policy is to dispatch on the
     * request path. You can, for example, filter the request instead of using server.enqueue().
     */
    private inner class MyDispatcher : Dispatcher() {

        @Throws(InterruptedException::class)
        override fun dispatch(recordedRequest: RecordedRequest): MockResponse {

            // Only the path is needed, not the query string
            var requestPath = recordedRequest.path
            if (requestPath.contains("?")) {
                requestPath = requestPath.substring(0, requestPath.indexOf('?')).trim { it <= ' ' }
            }

            Log.v("FakeServer", "THE requestPath is: $requestPath")

            var statusCode: Int
            var jsonResponse: String
            val fullPath = recordedRequest.path
            Log.v("***FakeServer***", "PATH IS=" + recordedRequest.path)

            // Handle checkEmailRequest
            if (requestPath.equals("/random-data/master/fake_data/users/1/user_details.json")) {
                statusCode = 200
                jsonResponse = getMockedResponseByFile(cleanarcpro.brightowusu.com.cleanarcproj.data.test.R.raw.user_details)
            } else {
                statusCode = 500
                jsonResponse = "OUCH! need to declare our fake json response!!!"
            }

            return getMockResponseForJson(statusCode, jsonResponse)
        }

        /**
         * Mock responses default to an empty response body and a 200 status code.
         * You can set a custom body with a string, input stream or byte array.
         * Also add headers with a fluent builder API.
         * @param statusCode
         * @param json
         * @return
         */
        private fun getMockResponseForJson(statusCode: Int, json: String?): MockResponse {
            val response = MockResponse()
            if (json != null) {
                response.setBody(json)
            }
            response.setResponseCode(statusCode)
            response.setBodyDelay(delay, TimeUnit.SECONDS)
            return response
        }
    }

    fun encode(str: String): String {
        return URLEncoder.encode(str, "UTF-8")
    }

    /**
     * Cleanup per test.
     */
    fun performCleanUp() {
        try {
            if (mockWebServer != null) {
                mockWebServer!!.shutdown()
                mockWebServer = null
            }
        } catch (e: Throwable) {
            e.printStackTrace()
            throw RuntimeException(e)
        }

    }

    // https://stackoverflow.com/questions/41000584/best-way-to-use-bufferedreader-in-kotlin
    private fun getMockedResponseByFile(res: Int): String {
        val inputStream = getInstrumentation().getContext().getResources().openRawResource(res)
        val allText = inputStream.bufferedReader().use(BufferedReader::readText)
        return allText
    }

    companion object {

        private val PORT = 8080
    }
}