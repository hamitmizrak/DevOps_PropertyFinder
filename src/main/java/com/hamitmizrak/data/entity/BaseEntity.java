package com.hamitmizrak.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

//lombok
@Data

//audit
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value={"created_date,update_date"})

@MappedSuperclass
 abstract   public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",updatable = false)
    private Long id;

    //kim ekledi
    @CreatedBy
    @Column(name="created_by")
    private String createdBy;

    //kim ne zaman ekledi
    @CreatedDate
    @Column(name="created_date")
    private java.util.Date createdDate;

    //kim güncelledi
    @LastModifiedBy
    @Column(name="update_by")
    private String updateBy;

    //kim ne zaman güncelledi
    @LastModifiedDate
    @Column(name="update_date")
    private Date updateDate;
}
