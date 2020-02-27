package com.atharva.detailfinder.mapper;

import com.atharva.detailfinder.model.data.Batch;
import com.atharva.detailfinder.model.data.User;
import com.atharva.detailfinder.model.detail.TrainLink;

import java.util.Objects;

public class UpdateMapper {

    public static void getBatchForUpdate(final Batch source, final Batch destination) {
        if (source.getMembers() != 0) {
            destination.setBatchId(source.getMembers());
        }

        if (Objects.nonNull(source.getBatchName())) {
            destination.setBatchName(source.getBatchName());
        }

        if (Objects.nonNull(source.getStartDate())) {
            destination.setStartDate(source.getStartDate());
        }

    }

    public static void getUserForUpdate(final User source, final User destination) {
        if (Objects.nonNull(source.getName())) {
            destination.setName(source.getName());
        }

        if (Objects.nonNull(source.getEmail())) {
            destination.setEmail(source.getEmail());
        }

        if (Objects.nonNull(source.getMobile())) {
            destination.setMobile(source.getMobile());
        }

    }

    public static void getUserForPosChange(final User source, final User destination) {

        getUserForUpdate(source, destination);

        if (Objects.nonNull(source.getPos())) {
            destination.setPos(source.getPos());
        }
    }

    public static void getDetailForUpdate(final TrainLink source, final TrainLink destination) {
        if (Objects.nonNull(source.getArrivalStation())) {
            destination.setArrivalStation(source.getArrivalStation());
        }

        if (Objects.nonNull(source.getDepartureStation())) {
            destination.setDepartureStation(source.getDepartureStation());
        }

        if (Objects.nonNull(source.getLinkType())) {
            destination.setLinkType(source.getLinkType());
        }

        if (Objects.nonNull(source.getTrainNo())) {
            destination.setTrainNo(source.getTrainNo());
        }
    }
}
