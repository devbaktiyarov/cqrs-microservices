package com.arsenbaktiyarov.userservice.event;

import com.arsenbaktiyarov.core.model.PaymentDetails;
import com.arsenbaktiyarov.core.model.User;
import com.arsenbaktiyarov.core.query.FetchUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserEventsHandler {

    @QueryHandler
    public User getUser(FetchUserPaymentDetailsQuery detailsQuery) {
        PaymentDetails paymentDetails = PaymentDetails.builder()
                .cardNumber("bankid")
                .cvv("001")
                .name("John Doe")
                .validUntilMonth(12)
                .validUntilYear(2023)
                .build();

        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .userId(detailsQuery.getUserId())
                .paymentDetails(paymentDetails)
                .build();

        return user;
    }

}
