package com.atharva.detailfinder.dao;

import com.atharva.detailfinder.constants.DatabaseConstants;
import com.atharva.detailfinder.mapper.UpdateMapper;
import com.atharva.detailfinder.model.Positions;
import com.atharva.detailfinder.model.detail.Detail;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetailDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void addDetailForBatch(final List<Detail> details) {
        for (final Detail detail : details) {
            sessionFactory.getCurrentSession().save(detail);
        }
    }

    public List<Detail> getDetailForBatch(final long batchId) {
        final Query<Detail> query = sessionFactory.getCurrentSession().createQuery(DatabaseConstants.getDetailByBatchId, Detail.class);
        query.setParameter("batchId", batchId);
        return query.getResultList();
    }

    public Detail getDetailForSingleStaff(final long batchId, final Positions pos, final int weekNo) {
        final Query<Detail> query = sessionFactory.getCurrentSession().createQuery(DatabaseConstants.getDetailByBatchIdPosWeekNo, Detail.class);
        query.setParameter("batchId", batchId);
        query.setParameter("pos", pos);
        query.setParameter("weekNo", weekNo);
        return query.uniqueResult();
    }

    public void updateDetail(final List<Detail> details) {
        for (final Detail detail : details) {
            final Detail detailFromDb = getDetailForSingleStaff(detail.getBatch().getBatchId(), detail.getPos(), detail.getWeekNo());
            UpdateMapper.getDetailForUpdate(detail.getLink(), detailFromDb.getLink());
            sessionFactory.getCurrentSession().update(detailFromDb);
        }
    }

    public void deleteDetailForBatch(final long batchId) {
        final List<Detail> details = getDetailForBatch(batchId);
        for (final Detail detail : details) {
            sessionFactory.getCurrentSession().delete(detail);
        }
    }
}
