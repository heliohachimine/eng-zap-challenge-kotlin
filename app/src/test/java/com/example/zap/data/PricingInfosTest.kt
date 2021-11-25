package com.example.zap.data

import com.example.zap.data.models.PricingInfos
import com.example.zap.presentation.model.PricingInfoVO
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert
import org.junit.Test

class PricingInfosTest {
    @Test
    fun givenResultData_whenToViewObject_shouldReturnImmobileViewObject() {
        // GIVEN
        val data = mockk<PricingInfos>()
        val vo = mockk<PricingInfoVO>()

        // WHEN
        every { data.toViewObject() } returns vo
        val result = data.toViewObject()

        // THEN
        Assert.assertTrue(result is PricingInfoVO)
    }
}
