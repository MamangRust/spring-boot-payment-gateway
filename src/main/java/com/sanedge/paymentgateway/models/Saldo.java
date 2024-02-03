package com.sanedge.paymentgateway.models;

import java.security.Timestamp;
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
@Table(name = "saldo")
@EqualsAndHashCode(callSuper = true)
public class Saldo extends BaseModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "saldo_id")
  private Long saldoId;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "total_balance", nullable = false)
  private Integer totalBalance;

  @Column(name = "withdraw_amount", columnDefinition = "INTEGER DEFAULT 0", nullable = true)
  private Integer withdrawAmount;

  @Column(name = "withdraw_time", nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date withdrawTime;

}
