package com.atharva.detailfinder.dao;

import com.atharva.detailfinder.constants.DatabaseConstants;
import com.atharva.detailfinder.model.Positions;
import com.atharva.detailfinder.model.data.ActiveStaff;
import com.atharva.detailfinder.model.data.Batch;
import com.atharva.detailfinder.model.data.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataDao {

    @Autowired
    private SessionFactory sessionFactory;

    public long addBatch(final Batch batch) {
        return (long) sessionFactory.getCurrentSession().save(batch);
    }

    public Batch getBatchById(final long batchId) {
        return sessionFactory.getCurrentSession().get(Batch.class, batchId);
    }

    public Batch getBatchByNameAndHomeStation(final String batchName, final String homeStation) {
        final Query<Batch> query = sessionFactory.getCurrentSession().createQuery(DatabaseConstants.getBatchByNameAndHomeStation, Batch.class);
        query.setParameter("batchName", batchName);
        query.setParameter("homeStation", homeStation);
        return query.uniqueResult();
    }

    public List<Batch> getBatchByHomeStation(final String homeStation) {
        final Query<Batch> query = sessionFactory.getCurrentSession().createQuery(DatabaseConstants.getBatchByHomeStation, Batch.class);
        query.setParameter("homeStation", homeStation);
        return query.getResultList();
    }

    public void updateBatch(final Batch batch) {
        sessionFactory.getCurrentSession().update(batch);
    }

    public void deleteBatchByNameAndHomeStation(final String batchName, final String homeStation) {
        final Query<Batch> query = sessionFactory.getCurrentSession().createQuery(DatabaseConstants.getBatchByNameAndHomeStation, Batch.class);
        query.setParameter("batchName", batchName);
        query.setParameter("homeStation", homeStation);
        sessionFactory.getCurrentSession().delete(query.uniqueResult());
    }


    public void addDriver(final ActiveStaff activeStaff) {
        sessionFactory.getCurrentSession().save(activeStaff);
    }

    public List<ActiveStaff> getActiveStaffListByBatchId(final long batchId) {
        final Query<ActiveStaff> query = sessionFactory.getCurrentSession().createQuery(DatabaseConstants.getActiveStaffListByBatchId, ActiveStaff.class);
        query.setParameter("batchId", batchId);
        return query.getResultList();
    }

    public ActiveStaff getActiveStaffByUserId(final String userId) {
        final Query<ActiveStaff> query = sessionFactory.getCurrentSession().createQuery(DatabaseConstants.getActiveStaffByUserId, ActiveStaff.class);
        query.setParameter("userId", userId);
        return query.uniqueResult();
    }

    public void updateActiveStaffByBatchIdAndWeekNoAndPos(final long batchId, final int weekNo, final Positions pos, final String userId) {
        final Query<ActiveStaff> query = sessionFactory.getCurrentSession().createQuery(DatabaseConstants.getActiveStaffByBatchIdAndWeekNoAndPos, ActiveStaff.class);
        query.setParameter("batchId", batchId);
        query.setParameter("weekNo", weekNo);
        query.setParameter("pos", pos);
        final ActiveStaff activeStaffFromDb = query.uniqueResult();
        activeStaffFromDb.setUser(getUser(userId));
        sessionFactory.getCurrentSession().update(activeStaffFromDb);
    }


    public void addUser(final User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public User getUser(final String userId) {
        return sessionFactory.getCurrentSession().get(User.class, userId);
    }

    public void updateUser(final User user) {
        sessionFactory.getCurrentSession().update(user.getUserId(), user);
    }

    public void deleteUser(final String userId) {
        sessionFactory.getCurrentSession().delete(getUser(userId));
    }
}
