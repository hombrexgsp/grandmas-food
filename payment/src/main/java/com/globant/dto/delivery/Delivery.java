package com.globant.dto.delivery;

import java.time.LocalDateTime;

public sealed interface Delivery permits Delivered, Pending{


}
