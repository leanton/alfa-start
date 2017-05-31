package me.antonle.alfastart.transfers.entity;

import me.antonle.alfastart.domain.Ccy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "ACCOUNTS")
public class Account implements Serializable {

    @Id
    @Column(name = "ACCOUNT_ID")
    private long accountID;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "CCY")
    private Ccy ccy;



}
