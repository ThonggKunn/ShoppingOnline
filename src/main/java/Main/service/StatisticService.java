package Main.service;

import Main.dto.request.statistic.DateRangeRequestDto;

public interface StatisticService {

    Object getRevenueStatistics(DateRangeRequestDto request);

}
