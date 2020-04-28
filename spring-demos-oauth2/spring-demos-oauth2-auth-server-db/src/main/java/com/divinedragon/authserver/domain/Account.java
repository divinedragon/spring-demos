package com.divinedragon.authserver.domain;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account", schema = "sd_oauth")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "a_id", columnDefinition = "uuid")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "a_hash")
    private String hash;

    @Column(name = "a_email")
    private String email;

    @JsonIgnore
    @Column(name = "a_password")
    private String password;

    @Column(name = "a_verification_key", columnDefinition = "uuid")
    private UUID verificationKey;

    @Column(name = "a_disabled")
    private boolean disabled;

    @Column(name = "a_verified")
    private boolean verified;

    @Column(name = "a_role")
    private String role;

    @Column(name = "a_created_at")
    private OffsetDateTime createdAt;
}
