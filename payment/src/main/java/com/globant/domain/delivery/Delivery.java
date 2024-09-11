package com.globant.domain.delivery;

public sealed interface Delivery permits Delivered, Pending {}
