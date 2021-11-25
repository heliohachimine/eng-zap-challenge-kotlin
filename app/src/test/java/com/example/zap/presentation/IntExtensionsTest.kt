package com.example.zap.presentation

import com.example.zap.presentation.utils.extensions.formatBathrooms
import com.example.zap.presentation.utils.extensions.formatBedrooms
import com.example.zap.presentation.utils.extensions.formatParking
import junit.framework.Assert
import org.junit.Test

class IntExtensionsTest {
    @Test
    fun givenIntValue_whenToMaskBedroom_shouldReturnMaskedValue() {
        //GIVEN
        val value = 10

        //WHEN
        val result = value.formatBedrooms()

        //THEN
        Assert.assertEquals("10 quartos", result)
    }

    @Test
    fun givenIntValue_whenToMaskBathroom_shouldReturnMaskedValue() {
        //GIVEN
        val value = 10

        //WHEN
        val result = value.formatBathrooms()

        //THEN
        Assert.assertEquals("10 banheiros", result)
    }

    @Test
    fun givenIntValue_whenToMaskParking_shouldReturnMaskedValue() {
        //GIVEN
        val value = 10

        //WHEN
        val result = value.formatParking()

        //THEN
        Assert.assertEquals("10 vagas", result)
    }
}