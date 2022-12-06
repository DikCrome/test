package com.isaackubo.guava.demo1;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class EventMessage2 {
    private int type;
    private String msg;
}
