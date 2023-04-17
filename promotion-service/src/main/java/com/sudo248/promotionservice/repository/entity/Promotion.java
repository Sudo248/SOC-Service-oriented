package com.sudo248.promotionservice.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Getter
@Setter
@ToString
@Entity
@Table(name = "promotion")
@NoArgsConstructor
@AllArgsConstructor

public class Promotion {
    @Id
    @Column(name = "promotion_id")
    private String promotionId;
    @Column(name = "value")
    private Double value;
    @Column(name = "name")
    private String name;
}
