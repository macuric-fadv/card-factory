package com.marsus.demo.cardfactory.model.dto;

import com.marsus.demo.cardfactory.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class RequestDto {

    private Long id;

    private Status status;
}
