package com.sanedge.paymentgateway.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transfer")
@EqualsAndHashCode(callSuper = true)
public class Transfer extends BaseModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transfer_id")
  private Long transferId;

  @ManyToOne
  @JoinColumn(name = "transfer_from", nullable = false)
  private User transferFrom;

  @ManyToOne
  @JoinColumn(name = "transfer_to", nullable = false)
  private User transferTo;

  @Column(name = "transfer_amount", nullable = false)
  private Integer transferAmount;

  @Column(name = "transfer_time", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date transferTime;

}
