package com.globant.typeclass;


public interface Mappable<In, Out> {
    Out to (In in);
    In from (Out out);
}
