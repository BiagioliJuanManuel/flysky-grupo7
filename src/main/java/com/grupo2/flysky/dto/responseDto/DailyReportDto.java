package com.grupo2.flysky.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyReportDto {

    private int dailySalesNumber;
    private double dailyIncome;
    private List<String> popularDestinations;
    private List<String> bookingTrends;

}
