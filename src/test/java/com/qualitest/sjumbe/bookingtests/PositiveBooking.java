package com.qualitest.sjumbe.bookingtests;

import com.qualitest.sjumbe.base.CsvDataProviders;
import com.qualitest.sjumbe.base.TestUtilities;
import com.qualitest.sjumbe.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class PositiveBooking extends TestUtilities {

    @Test(dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    public void positiveBookingTest(Map<String, String> testData) {

        //Variable to use with validation
        String expectedArrivalDate = testData.get("arrivalDate");
        String expectedStayLength = testData.get("stayLength");
        String expectedDepartureDate = testData.get("departureDate");
        String expectedNumberOfAdults = testData.get("numberOfAdults");
        String expectedRoomType = testData.get("roomType");
        String expectedRoomRate = testData.get("roomRate");
        String expectedGuaranteeOption = testData.get("guaranteeOption");
        String expectedRoomsCost = testData.get("roomsCost");
        String expectedExtraServicesCost = testData.get("extraServicesCost");
        String expectedVat = testData.get("vat");
        String expectedTotalCost = testData.get("totalCost");
        String expectedRequiredDeposit = testData.get("requiredDeposit");


        log.info("Executing booking test");

        //Opening page
        BookingHomePage bookingHomePage = new BookingHomePage(driver, log);
        bookingHomePage.openPage();


        //Entering booking details
        bookingHomePage.enterArrivalDate("01-10-2022");
        bookingHomePage.enterNumberOfNights(4);
        bookingHomePage.enterNumberOfAdults(2);
        //sleep(3000);
        bookingHomePage.bookNow();

        AvailableRooms availableRooms = new AvailableRooms(driver, log);
        availableRooms.checkAvailableRooms();
        availableRooms.selectDeluxeApartment();

        ExtraServices extraServices = new ExtraServices(driver, log);
        extraServices.addAirportTransfer(2);
        extraServices.addBusinessServices(1);
        extraServices.addSelectedServices();

        BookingReviewPage bookingReviewPage = new BookingReviewPage(driver, log);
        bookingReviewPage.addContactInfo("Stewart", "J", "stewartj@gmail.com", "07521113188", "no");
        bookingReviewPage.selectCreditCard();
        bookingReviewPage.agreeWithHotelPolicy();
        //TODO verify booking details
        //Validation of Booking
        //Checking Arrival Date
        String actualArrivalDate = bookingReviewPage.getArrivalDateTextLocator();
        Assert.assertEquals(actualArrivalDate, expectedArrivalDate,
                "actualArrivalDate does not equal csv ArrivalDate \ncsv ArrivalDate: "
                        + expectedArrivalDate + "\nactual ArrivalDate: " + actualArrivalDate);

        //Checking Stay Length
        String actualStayLength = bookingReviewPage.getStayLengthTextLocator();
        Assert.assertEquals(actualStayLength, expectedStayLength,
                "actualStayLength does not equal csv StayLength \ncsv StayLength: "
                        + expectedStayLength + "\nactual StayLength: " + actualStayLength);

        //Checking Departure Date
        String actualDepartureDate = bookingReviewPage.getDepartureTextLocator();
        Assert.assertEquals(actualDepartureDate, expectedDepartureDate,
                "actualDepartureDate does not equal csv DepartureDate \ncsv DepartureDate: "
                        + expectedDepartureDate + "\nactual DepartureDate: " + actualDepartureDate);


        //Checking Number Of Adults
        String actualNumberOfAdults = bookingReviewPage.getNumberOfAdultsTextLocator();
        Assert.assertEquals(actualNumberOfAdults, expectedNumberOfAdults,
                "actualNumberOfAdults does not equal csv NumberOfAdults \ncsv NumberOfAdults: "
                        + expectedNumberOfAdults + "\nactual NumberOfAdults: " + actualNumberOfAdults);


        //Checking Room Type
        String actualRoomType = bookingReviewPage.getRoomTypeTextLocator();
        Assert.assertEquals(actualRoomType, expectedRoomType,
                "actualRoomType does not equal csv RoomType \ncsv RoomType: "
                        + expectedRoomType + "\nactual RoomType: " + actualRoomType);

        bookingReviewPage.getRateTextLocator();
        //Checking Room Rate- Not working
        String actualRoomRate = bookingReviewPage.getRateTextLocator();
        Assert.assertTrue(actualRoomRate.contains(expectedRoomRate),
                "actualRoomRate does not contain csv RoomRate \ncsv RoomRate: "
                        + expectedRoomRate + "\nactual RoomRate: " + actualRoomRate);


        //Checking Guarantee Option
        String actualGuaranteeOption = bookingReviewPage.getGuaranteeOptionTextLocator();
        Assert.assertEquals(actualGuaranteeOption, expectedGuaranteeOption,
                "actualGuaranteeOption does not equal csv GuaranteeOption \ncsv GuaranteeOption: "
                        + expectedGuaranteeOption + "\nactual GuaranteeOption: " + actualGuaranteeOption);


        //Checking Rooms Cost
        String actualRoomsCost = bookingReviewPage.getRoomCostTextLocator();
        Assert.assertEquals(actualRoomsCost, expectedRoomsCost,
                "actualRoomsCost does not equal csv RoomsCost \ncsv RoomsCost: "
                        + expectedRoomsCost + "\nactual RoomsCost: " + actualRoomsCost);


        //Checking Extra Services Cost
        String actualExtraServicesCost = bookingReviewPage.getExtraServicesTextLocator();
        Assert.assertEquals(actualExtraServicesCost, expectedExtraServicesCost,
                "actualExtraServicesCost does not equal csv ExtraServicesCost \ncsv ExtraServicesCost: "
                        + expectedExtraServicesCost + "\nactual ExtraServicesCost: " + actualExtraServicesCost);


        //Checking VAT
        String actualVat = bookingReviewPage.getVatTextLocator();
        Assert.assertEquals(actualVat, expectedVat,
                "actualVat does not equal csv Vat \ncsv Vat: "
                        + expectedVat + "\nactual Vat: " + actualVat);


        //Checking Total Cost
        String actualTotalCost = bookingReviewPage.getTotalCostTextLocator();
        Assert.assertEquals(actualTotalCost, expectedTotalCost,
                "actualTotalCost does not equal csv TotalCost \ncsv TotalCost: "
                        + expectedTotalCost + "\nactual TotalCost: " + actualTotalCost);


        //Checking Required Deposit
        String actualRequiredDeposit = bookingReviewPage.getRequiredDepositTextLocator();
        Assert.assertEquals(actualRequiredDeposit, expectedRequiredDeposit,
                "actualRequiredDeposit does not equal csv RequiredDeposit \ncsv RequiredDeposit: "
                        + expectedRequiredDeposit + "\nactual RequiredDeposit: " + actualRequiredDeposit);

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
