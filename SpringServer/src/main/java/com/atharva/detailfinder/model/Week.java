package com.atharva.detailfinder.model;

import lombok.Data;

import java.util.List;

@Data
public class Week {
    private TrainLinkPair[] week = new TrainLinkPair[7];
}
