package com.example.danielhorowitz.bitcoin.data.repository

import com.example.danielhorowitz.bitcoin.blockchainChartDTO
import com.example.danielhorowitz.bitcoin.data.network.BlockchainAPI
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

@ExperimentalCoroutinesApi
internal class BlockchainChartNetworkDataSourceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var dataSource: BlockchainChartNetworkDataSource
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        val api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BlockchainAPI::class.java)

        dataSource = BlockchainChartNetworkDataSource(api)
    }

    @Test
    fun `should fetch charts correctly given ok response`() {
        givenOkResponse()

        runBlocking {
            val actual = dataSource.fetchChart("foobar")

            val expected = blockchainChartDTO()

            assertEquals(expected, actual)
        }
    }

    private fun givenOkResponse() = enqueueResponse("chart.json", 200)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun enqueueResponse(fileName: String, code: Int) {
        val inputStream =
            javaClass.classLoader?.getResourceAsStream("fixtures/$fileName")

        val source = inputStream?.let { inputStream.source().buffer() }
        source?.let {
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(code)
                    .setBody(source.readString(StandardCharsets.UTF_8))
            )
        }
    }
}
