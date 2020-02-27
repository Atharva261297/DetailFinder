package com.atharva.detailfinder.model.data;


import com.atharva.detailfinder.model.Positions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "users", schema = "data")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;
    @Column(name = "pos")
    private Positions pos;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "mobile")
    private String mobile;
    @JsonIgnore
    @Column(name = "hash")
    private byte[] hash;
}
