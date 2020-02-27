package com.atharva.detailfinder.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "batches", schema = "data")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long batchId;

    @Column(name = "batch_name")
    private String batchName;
    @Column(name = "home_station")
    private String homeStation;
    @Column(name = "start_date")
    private String startDate;
    @Column(name = "members")
    private int members;
}
