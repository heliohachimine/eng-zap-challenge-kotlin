package com.example.zap.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.zap.core.Either
import com.example.zap.domain.Repository
import com.example.zap.presentation.details.DetailsViewModel
import com.example.zap.presentation.model.ImmobileVO
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.mockkClass
import junit.framework.Assert.assertEquals
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

class DetailViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = TestCoroutineDispatcher()
    private lateinit var repository: Repository
    private lateinit var viewModel: DetailsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        repository = mockkClass(Repository::class)
        viewModel = DetailsViewModel(repository)
    }
    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun shouldGetImmobileById_successfulCase() {
        val immobileVO = mockk<ImmobileVO>()
        val successfulCase = Either.Right(immobileVO)
        dispatcher.runBlockingTest {
            coEvery { repository.getImmobileById("")} returns successfulCase
            viewModel.getImmobile("")

            val result = viewModel.immobileLiveData.value
            assertEquals(successfulCase, result)
        }
    }

    @Test
    fun shouldGetImmobileById_failedCase() {
        val exception = mockk<HttpException>()
        val failedCase = Either.Left(exception)
        dispatcher.runBlockingTest {
            coEvery { repository.getImmobileById("")} returns failedCase
            viewModel.getImmobile("")

            val result = viewModel.immobileLiveData.value
            assertEquals(failedCase, result)
        }
    }
}