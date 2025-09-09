package com.globant.model.store;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderRequest {
    private long id;
    private long petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;
}
