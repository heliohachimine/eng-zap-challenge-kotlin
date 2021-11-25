package com.example.zap.presentation

import com.example.zap.presentation.utils.extensions.formatArea
import com.example.zap.presentation.utils.extensions.toCurrencyBr
import junit.framework.Assert
import org.junit.Test

class DoubleExtensionsTest {

    @Test
    fun givenDoubleValue_whenToCurrencyBr_shouldReturnMaskedValue() {
        // GIVEN
        val value = 1000.99

        // WHEN
        val result = value.toCurrencyBr()

        // THEN
        Assert.assertEquals("R$ 1.000,99", result)
    }

    @Test
    fun givenDoubleValue_whenToMaskArea_shouldReturnMaskedValue() {
        // GIVEN
        val value = 1000.99

        // WHEN
        val result = value.formatArea()

        // THEN
        Assert.assertEquals("1000.99 m2", result)
    }
}
