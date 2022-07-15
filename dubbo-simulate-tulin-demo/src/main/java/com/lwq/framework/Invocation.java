package com.lwq.framework;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class Invocation implements Serializable {
    private String interfaceName;
    private String methodName;
    private Class[] paramType;
    private Object[] params;
}
