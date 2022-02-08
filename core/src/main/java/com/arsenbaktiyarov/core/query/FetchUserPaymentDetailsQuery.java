package com.arsenbaktiyarov.core.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
public class FetchUserPaymentDetailsQuery {
    private String userId;
}
