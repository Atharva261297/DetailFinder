package com.atharva.detailfinder.model.detail;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class TrainLink {

    @Column(name = "train_no")
    private String trainNo;

    @Column(name = "departure_station")
    private String departureStation;

    @Column(name = "arrival_station")
    private String arrivalStation;

    @Column(name = "link_type")
    private LinkType linkType;
}
