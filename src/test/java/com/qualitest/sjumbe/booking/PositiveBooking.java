package com.qualitest.sjumbe.booking;

import com.qualitest.sjumbe.base.TestUtilities;
import com.qualitest.sjumbe.pages.*;
import org.testng.annotations.Test;

public class PositiveBooking extends TestUtilities {

    @Test
    public void positiveBookingTest() throws InterruptedException {
        log.info("Executing booking test");

        //Opening page
        BookingHomePage bookingHomePage = new BookingHomePage(driver, log);
        bookingHomePage.openPage();


        //Entering booking details
        bookingHomePage.enterArrivalDate("12-09-2022");
        bookingHomePage.enterNumberOfNights(4);
        bookingHomePage.enterNumberOfAdults(2);
        sleep(3000);

        bookingHomePage.bookNow();

        AvailableRooms availableRooms = new AvailableRooms(driver, log);
        availableRooms.checkAvailableRooms();
        availableRooms.selectDeluxeAppartment();

        ExtraServices extraServices = new ExtraServices(driver, log);
        extraServices.addAirportTransfer(2);
        extraServices.addBusinessServices(1);
        extraServices.addSelectedServices();

        BookingReviewPage bookingReviewPage = new BookingReviewPage(driver, log);
        bookingReviewPage.addContactInfo("Stewart", "J", "stewartj@gmail.com", "07521113188", "no");
        bookingReviewPage.selectCreditCard();
        bookingReviewPage.agreeWithHotelPolicy();
        bookingReviewPage.createBooking();

        PaymentPage paymentPage = new PaymentPage(driver, log);
        paymentPage.enterCardNumber("5425233430109903");
        paymentPage.selectCreditCardBrand("master");
        paymentPage.selectCardExpiryMonthAndYear(04, 2023);
        paymentPage.enterBillingAddress("70 Eden Steet", "75214", "San Diago", "California", "us");
        //paymentPage.payDeposit();
        sleep(5000);


    }


}
