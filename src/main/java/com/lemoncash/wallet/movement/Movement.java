package com.lemoncash.wallet.movement;

import com.lemoncash.wallet.wallet.Wallet;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long amount;
    private Type movementType;

    @JoinColumn(name = "wallet_id")
    @ManyToOne
    private Wallet wallet;

}
