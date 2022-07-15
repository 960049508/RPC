package com.lwq.common.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TransformObject implements Serializable {
    private Integer id;
    private String message;
}
