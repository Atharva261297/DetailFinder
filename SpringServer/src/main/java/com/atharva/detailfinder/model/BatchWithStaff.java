package com.atharva.detailfinder.model;

import com.atharva.detailfinder.model.data.Batch;
import lombok.Data;

import java.util.List;

@Data
public class BatchWithStaff {
    private Batch batch;
    private List<StaffCoStaffPair> staff;
}
