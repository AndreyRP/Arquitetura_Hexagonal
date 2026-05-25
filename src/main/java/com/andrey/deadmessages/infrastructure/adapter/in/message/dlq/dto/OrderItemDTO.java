package com.andrey.deadmessages.infrastructure.adapter.in.message.dlq.dto;

import lombok.Data;

@Data
public class OrderItemDTO {

    private Long sku;

    private Integer amount;
}