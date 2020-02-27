package com.atharva.detailfinder.model.detail;

import com.atharva.detailfinder.model.Positions;
import com.atharva.detailfinder.model.data.Batch;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "detail", schema = "data")
public class Detail {

    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "batch_id")
    private Batch batch;

    @Id
    @Column(name = "pos")
    private Positions pos;

    @Id
    @Column(name = "week_no")
    private int weekNo;

    @Embedded
    private TrainLink link;
}
