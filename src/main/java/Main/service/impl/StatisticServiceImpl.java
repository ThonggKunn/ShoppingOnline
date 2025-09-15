package Main.service.impl;

import Main.dto.request.statistic.DateRangeRequestDto;
import Main.service.StatisticService;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Override
    public Object getRevenueStatistics(DateRangeRequestDto request) {

        // Logic here

        return new DateRangeRequestDto();
    }

}
