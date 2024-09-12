package com.globant.domain.user;

import domain.user.DocumentIdentity;

public record User(DocumentIdentity documentIdentity, String firstName, String lastName, String email, Long phoneNumber, String address) {
}
