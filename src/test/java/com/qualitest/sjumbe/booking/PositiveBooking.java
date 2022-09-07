package com.qualitest.sjumbe.booking;

import com.qualitest.sjumbe.base.TestUtilities;
import com.qualitest.sjumbe.pages.BookingHomePage;
import org.testng.annotations.Test;

public class PositiveBooking extends TestUtilities {

    @Test
    public void positiveBookingTest(){
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

        sleep(5000);


    }


}
