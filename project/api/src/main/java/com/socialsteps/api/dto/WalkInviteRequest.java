package com.socialsteps.api.dto;

public record WalkInviteRequest(
    Long userId,
    Long walkId,
    Long recipientId
) {}
