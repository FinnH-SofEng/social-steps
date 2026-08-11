package com.socialsteps.api.dto;

import java.time.LocalDateTime;

public record CreateWalkRequest(
    Long creatorId,
    String name,
    LocalDateTime time,
    Double latitude,
    Double longitude
) {}