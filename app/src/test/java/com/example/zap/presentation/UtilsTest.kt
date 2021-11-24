package com.example.zap.presentation

import com.example.zap.presentation.enum.BusinessType
import com.example.zap.presentation.main.Utils
import junit.framework.Assert.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    fun givenImmobileToRentalInsideZapBounds_whenCheckVivaReal_shouldReturnTrue() {
        //GIVEN
        val businessType = BusinessType.RENTAL
        val condoFee = 45.0
        val price = 100.0

        val lat = -23.556686
        val lng = -46.644146

        //WHEN
        val result = Utils.checkVivaImmobile(businessType, condoFee, price, lat, lng)

        //THEN
        assertEquals(true, result)
    }

    @Test
    fun givenImmobileToRentalInsideZapBounds_whenCheckVivaReal_shouldReturnFalse() {
        //GIVEN
        val businessType = BusinessType.RENTAL
        val condoFee = 50.0
        val price = 100.0

        val lat = -23.556686
        val lng = -46.644146

        //WHEN
        val result = Utils.checkVivaImmobile(businessType, condoFee, price, lat, lng)

        //THEN
        assertEquals(false, result)
    }

    @Test
    fun givenImmobileToRentalOutsideZapBounds_whenCheckVivaReal_shouldReturnFalse() {
        //GIVEN
        val businessType = BusinessType.RENTAL
        val condoFee = 30.0
        val price = 100.0

        val lat = -23.23212
        val lng = -23.42323

        //WHEN
        val result = Utils.checkVivaImmobile(businessType, condoFee, price, lat, lng)

        //THEN
        assertEquals(false, result)
    }
    @Test
    fun givenImmobileToRentalOutsideZapBounds_whenCheckVivaReal_shouldReturnTrue() {
        //GIVEN
        val businessType = BusinessType.RENTAL
        val condoFee = 20.0
        val price = 100.0

        val lat = -23.23212
        val lng = -23.42323

        //WHEN
        val result = Utils.checkVivaImmobile(businessType, condoFee, price, lat, lng)

        //THEN
        assertEquals(true, result)
    }

    @Test
    fun givenImmobileToSaleInsideZapBounds_whenCheckVivaReal_shouldReturnFalse() {
        //GIVEN
        val businessType = BusinessType.SALE
        val condoFee = 45.0
        val price = 100.0

        val lat = -23.556686
        val lng = -46.644146

        //WHEN
        val result = Utils.checkVivaImmobile(businessType, condoFee, price, lat, lng)

        //THEN
        assertEquals(false, result)
    }

    @Test
    fun givenImmobileToSaleOutSideZapBounds_whenCheckVivaReal_shouldReturnFalse() {
        //GIVEN
        val businessType = BusinessType.SALE
        val condoFee = 45.0
        val price = 100.0

        val lat = -23.23212
        val lng = -23.42323

        //WHEN
        val result = Utils.checkVivaImmobile(businessType, condoFee, price, lat, lng)

        //THEN
        assertEquals(false, result)
    }

    @Test
    fun givenImmobileToSale_whenCheckZap_shouldReturnFalse() {
        //GIVEN
        val businessType = BusinessType.SALE
        val usableArea = 5.0
        val price = 3500.0

        //WHEN
        val result = Utils.checkZapImmobile(businessType, price, usableArea)

        //THEN
        assertEquals(false, result)
    }

    @Test
    fun givenImmobileToSale_whenCheckZap_shouldReturnTrue() {
        //GIVEN
        val businessType = BusinessType.SALE
        val usableArea = 2.0
        val price = 7001.0

        //WHEN
        val result = Utils.checkZapImmobile(businessType, price, usableArea)

        //THEN
        assertEquals(true, result)
    }

    @Test
    fun givenImmobileToRental_whenCheckZap_shouldReturnFalse() {
        //GIVEN
        val businessType = BusinessType.RENTAL
        val usableArea = 5.0
        val price = 3500.0

        //WHEN
        val result = Utils.checkZapImmobile(businessType, price, usableArea)

        //THEN
        assertEquals(false, result)
    }

    @Test
    fun givenCoordinatesOutsideZapBounds_whenCheckInBoundsZap_shouldReturnFalse() {
        //GIVEN
        val lat = -23.23212
        val lng = -23.42323

        //WHEN
        val result = Utils.insideBoundBoxZap(lat, lng)

        //THEN
        assertEquals(false, result)
    }

    @Test
    fun givenCoordinatesInsideZapBounds_whenCheckInBoundsZap_shouldReturnTrue() {
        //GIVEN
        val lat = -23.556686
        val lng = -46.644146

        //WHEN
        val result = Utils.insideBoundBoxZap(lat, lng)

        //THEN
        assertEquals(true, result)
    }
}