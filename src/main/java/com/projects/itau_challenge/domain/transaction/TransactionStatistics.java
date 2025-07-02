package com.projects.itau_challenge.domain.transaction;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TransactionStatistics {

    private Long count;
    private Double sum;
    private Double average;
    private Double min;
    private Double max;

}