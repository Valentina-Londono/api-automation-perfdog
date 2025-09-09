package com.globant.model.store;

import lombok.Data;

@Data
public class CreateOrderResponse {
    private long id;
    private long petId;
    private int quantity;
    private String shipDate;
    private String status;
    private boolean complete;
}
