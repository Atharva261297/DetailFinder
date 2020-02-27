package com.atharva.detailfinder.service;

import com.atharva.detailfinder.dao.DataDao;
import com.atharva.detailfinder.dao.DetailDao;
import com.atharva.detailfinder.model.Response;
import com.atharva.detailfinder.model.data.ActiveStaff;
import com.atharva.detailfinder.model.detail.Detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {

    @Autowired
    private DetailDao detailDao;

    @Autowired
    private DataDao dataDao;

    public Response addDetails(final List<Detail> details) {
        final int code = verifyDetails(details);
        if (code == 200) {
            detailDao.addDetailForBatch(details);
        }
        return new Response(code, null);
    }

    private int verifyDetails(final List<Detail> details) {
        int code = 200;
        final long batchId = details.get(0).getBatch().getBatchId();
        for (final Detail detail : details) {
            if (batchId != detail.getBatch().getBatchId()) {
                code = 501;
                break;
            }

            if (detail.getLink().getTrainNo().matches("[0-9]*")) {
                code = 502;
                break;
            }

            if (detail.getLink().getDepartureStation().matches("[a-zA-Z]*[-][A-Z]*")) {
                code = 503;
                break;
            }

            if (detail.getLink().getArrivalStation().matches("[a-zA-Z]*[-][A-Z]*")) {
                code = 504;
                break;
            }

            if (detail.getWeekNo() > detail.getBatch().getMembers() / 2) {
                code = 505;
                break;
            }
        }
        return code;
    }

    public Detail getDetailForStaff(final String userId) {
        final ActiveStaff activeStaff = dataDao.getActiveStaffByUserId(userId);
        return detailDao.getDetailForSingleStaff(activeStaff.getBatch().getBatchId(), activeStaff.getPos(), activeStaff.getWeekNo());
    }

    public List<Detail> getDetailForBatch(final long batchId) {
        return detailDao.getDetailForBatch(batchId);
    }

    public void updateDetails(final List<Detail> details) {
        detailDao.updateDetail(details);
    }

    public void deleteDetail(final long batchId) {
        detailDao.deleteDetailForBatch(batchId);
    }
}
