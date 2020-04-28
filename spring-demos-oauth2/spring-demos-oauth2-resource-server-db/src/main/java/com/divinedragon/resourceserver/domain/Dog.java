package com.divinedragon.resourceserver.domain;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dogs", schema = "sd_oauth")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dog {

    @Id
    @Column(name = "d_id", columnDefinition = "uuid")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "d_name")
    private String name;

    @Column(name = "d_breed")
    private String breed;

    @Column(name = "d_created_at")
    private OffsetDateTime createdAt;
}
