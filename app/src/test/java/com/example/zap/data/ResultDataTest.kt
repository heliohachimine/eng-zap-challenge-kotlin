package com.example.zap.data

import com.example.zap.presentation.model.ImmobileVO
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert
import org.junit.Test

class ResultDataTest {
    @Test
    fun givenResultData_whenToViewObject_shouldReturnImmobileViewObject() {
        //GIVEN
        val data = mockk<ResultData>()
        val vo = mockk<ImmobileVO>()

        //WHEN
        every { data.toViewObject() } returns vo
        val result = data.toViewObject()

        //THEN
        Assert.assertTrue(result is ImmobileVO)
    }
}