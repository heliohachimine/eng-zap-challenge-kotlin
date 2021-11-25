package com.example.zap.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.zap.core.Either
import com.example.zap.domain.Repository
import com.example.zap.presentation.main.MainViewModel
import com.example.zap.presentation.model.ImmobileVO
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkClass
import junit.framework.Assert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException

class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var repository: Repository
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repository = mockkClass(Repository::class)
        viewModel = MainViewModel(repository)
    }
    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun shouldGetVivaRealList_successfulCase() {
        val list = mockk<ArrayList<ImmobileVO>>()
        val successfulCase = Either.Right(list)
        dispatcher.runBlockingTest {
            coEvery { repository.getVivaRealData()} returns successfulCase
            viewModel.getVivaRealData()

            val result = viewModel.dataLiveData.value
            Assert.assertEquals(successfulCase, result)
        }
    }

    @Test
    fun shouldGetVivaRealList_failureCase() {
        val exception = mockk<HttpException>()
        val failureCase = Either.Left(exception)
        dispatcher.runBlockingTest {
            coEvery { repository.getVivaRealData()} returns failureCase
            viewModel.getVivaRealData()

            val result = viewModel.dataLiveData.value
            Assert.assertEquals(failureCase, result)
        }
    }

    @Test
    fun shouldGetZapList_successfulCase() {
        val list = mockk<ArrayList<ImmobileVO>>()
        val successfulCase = Either.Right(list)
        dispatcher.runBlockingTest {
            coEvery { repository.getZapData()} returns successfulCase
            viewModel.getZapData()

            val result = viewModel.dataLiveData.value
            Assert.assertEquals(successfulCase, result)
        }
    }

    @Test
    fun shouldGetZapList_failureCase() {
        val exception = mockk<HttpException>()
        val failureCase = Either.Left(exception)
        dispatcher.runBlockingTest {
            coEvery { repository.getZapData()} returns failureCase
            viewModel.getZapData()

            val result = viewModel.dataLiveData.value
            Assert.assertEquals(failureCase, result)
        }
    }

}