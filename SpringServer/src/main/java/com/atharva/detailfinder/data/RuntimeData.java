package com.atharva.detailfinder.data;

import com.atharva.detailfinder.model.DetailOld;
import com.atharva.detailfinder.model.Time;
import com.atharva.detailfinder.model.data.ActiveStaff;
import com.atharva.detailfinder.model.data.Batch;
import com.atharva.detailfinder.model.data.User;

import java.util.List;
import java.util.Map;

public class RuntimeData {
    public static List<Batch> batches;
    public static List<User> users;
    public static Map<String, DetailOld> details;
    public static List<ActiveStaff> activeStaffs;
    public static Map<String, Time> trainTime;
}
