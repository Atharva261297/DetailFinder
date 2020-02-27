package com.atharva.detailfinder.service;

import com.atharva.detailfinder.dao.DataDao;
import com.atharva.detailfinder.dao.DetailDao;
import com.atharva.detailfinder.mapper.UpdateMapper;
import com.atharva.detailfinder.model.BatchWithStaff;
import com.atharva.detailfinder.model.Positions;
import com.atharva.detailfinder.model.Response;
import com.atharva.detailfinder.model.StaffCoStaffPair;
import com.atharva.detailfinder.model.data.ActiveStaff;
import com.atharva.detailfinder.model.data.Batch;
import com.atharva.detailfinder.model.data.User;
import com.atharva.detailfinder.util.HashUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class DataService {

    @Autowired
    private DataDao dataDao;

    @Autowired
    private DetailDao detailDao;

    public Response addNewBatchForDrivers(final BatchWithStaff batchWithStaff) {
        final Batch batch = batchWithStaff.getBatch();
        final int verifyBatch = verifyBatch(batchWithStaff);
        if (verifyBatch == 200) {
            batch.setBatchId(addBatch(batchWithStaff.getBatch()));
            int i = 0;
            for (final StaffCoStaffPair driver : batchWithStaff.getStaff()) {
                final ActiveStaff lpm = new ActiveStaff(batch, i, Positions.LPM, dataDao.getUser(driver.getStaffUserId()));
                final ActiveStaff colpm = new ActiveStaff(batch, i, Positions.COLPM, dataDao.getUser(driver.getCoStaffUserId()));
                if (Objects.isNull(lpm.getUser()) || Objects.isNull(colpm.getUser())) {
                    return new Response(105, null);
                }
                dataDao.addDriver(lpm);
                dataDao.addDriver(colpm);
                i++;
            }
            return new Response(200, null);
        } else {
            return new Response(verifyBatch, null);
        }
    }

    public Response getBatchDetailsWithoutStaff(final String batchName, final String homeStation) {
        final Batch batchByNameAndHomeStation = dataDao.getBatchByNameAndHomeStation(batchName, homeStation);
        if (Objects.nonNull(batchByNameAndHomeStation)) {
            return new Response(200, new Gson().toJson(batchByNameAndHomeStation));
        } else {
            return new Response(104, null);
        }
    }

    public Response getBatchDetailsWithStaff(final String batchName, final String homeStation) {
        final Batch batchByNameAndHomeStation = dataDao.getBatchByNameAndHomeStation(batchName, homeStation);
        if (Objects.nonNull(batchByNameAndHomeStation)) {
            final List<ActiveStaff> activeStaffs = dataDao.getActiveStaffListByBatchId(batchByNameAndHomeStation.getBatchId());
            final JsonObject data = new JsonObject();
            data.add("batch", new Gson().toJsonTree(batchByNameAndHomeStation));
            data.add("staff", new Gson().toJsonTree(activeStaffs));
            return new Response(200, data.toString());
        } else {
            return new Response(104, null);
        }
    }

    public Response getAllBatchesAtStation(final String homeStation) {
        final List<Batch> batchByHomeStation = dataDao.getBatchByHomeStation(homeStation);
        return new Response(200, new Gson().toJson(batchByHomeStation));
    }

    public Response updateBatch(final Batch batch) {
        final Batch batchFromDb = dataDao.getBatchByNameAndHomeStation(batch.getBatchName(), batch.getHomeStation());
        if (batchFromDb != null) {
            UpdateMapper.getBatchForUpdate(batch, batchFromDb);
            dataDao.updateBatch(batch);
            return new Response(200, null);
        } else {
            return new Response(106, null);
        }
    }

    public Response deleteBatchByNameAndHomeStation(final String batchName, final String homeStation) {
        detailDao.deleteDetailForBatch(dataDao.getBatchByNameAndHomeStation(batchName, homeStation).getBatchId());
        dataDao.deleteBatchByNameAndHomeStation(batchName, homeStation);
        return new Response(200, null);
    }

    private int verifyBatch(final BatchWithStaff batchWithStaff) {
        final Batch batch = batchWithStaff.getBatch();
        if (batch.getHomeStation().matches("[a-zA-Z]*[-][A-Z]*")) {
            try {
                final Date ignored = new SimpleDateFormat("dd-MM-yyyy").parse(batch.getStartDate());
                if (batch.getMembers() == batchWithStaff.getStaff().size() * 2) {
                    if (Objects.nonNull(dataDao.getBatchByNameAndHomeStation(batch.getBatchName(), batch.getHomeStation()))) {
                        return 200;
                    } else {
                        return 104;
                    }
                } else {
                    return 103;
                }
            } catch (final ParseException e) {
                return 102;
            }
        } else {
            return 101;
        }
    }

    private long addBatch(final Batch batch) {
        return dataDao.addBatch(batch);
    }


    public void addUser(final User user) {
        user.setHash(new byte[]{});
        dataDao.addUser(user);
    }

    public void addUser(final User user, final String pass) {
        user.setHash(HashUtils.getHash(pass));
        dataDao.addUser(user);
    }

    public Response getUser(final String userId) {
        final User user = dataDao.getUser(userId);
        if (Objects.nonNull(user)) {
            user.setHash(null);
            return new Response(200, new Gson().toJson(user));
        }
        return new Response(201, null);
    }

    public void updateUser(final User user) {
        final User userFromDb = dataDao.getUser(user.getUserId());
        UpdateMapper.getUserForUpdate(user, userFromDb);
        dataDao.updateUser(userFromDb);
    }

    public void deleteUser(final String userId) {
        dataDao.deleteUser(userId);
    }
}
