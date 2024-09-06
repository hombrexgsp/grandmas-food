package com.globant.dto.delivery;

import java.time.LocalDateTime;

public record Delivered(LocalDateTime deliveredDate) implements  Delivery{}

