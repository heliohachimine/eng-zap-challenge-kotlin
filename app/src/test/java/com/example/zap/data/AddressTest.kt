package com.example.zap.data

import com.example.zap.data.models.Address
import com.example.zap.presentation.model.AddressVO
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert
import org.junit.Test

class AddressTest {

    @Test
    fun givenAddress_whenToViewObject_shouldReturnAddressViewObject() {
        //GIVEN
        val data = mockk<Address>()
        val vo = mockk<AddressVO>()

        //WHEN
        every { data.toViewObject() } returns vo
        val result = data.toViewObject()

        //THEN
        Assert.assertTrue(result is AddressVO)
    }
}