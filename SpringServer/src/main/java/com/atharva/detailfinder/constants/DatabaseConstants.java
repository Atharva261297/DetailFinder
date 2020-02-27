package com.atharva.detailfinder.constants;

public final class DatabaseConstants {

    public static final String getBatchByNameAndHomeStation = "From Batch b Where b.batchName = :batchName and b.homeStation = :homeStation";
    public static final String getBatchByHomeStation = "From Batch b Where b.homeStation = :homeStation";

    public static final String getActiveStaffListByBatchId = "From ActiveStaff d Where d.batchId = :batchId";
    public static final String getActiveStaffByUserId = "From ActiveStaff d Where d.userId = :userId";
    public static final String getActiveStaffByBatchIdAndWeekNoAndPos = "From ActiveStaff d " +
            "Where d.batchId = :batchId and d.weekNo = :weekNo and pos = :pos";

    public static final String getDetailByBatchId = "From Detail d Where d.batchId = :batchId";
    public static final String getDetailByBatchIdPosWeekNo = "From Detail d Where d.batchId = :batchId and d.pos = :pos and d.weekNo = :weekNo";
}
