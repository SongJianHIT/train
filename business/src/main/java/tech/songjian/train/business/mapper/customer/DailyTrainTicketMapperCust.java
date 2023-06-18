package tech.songjian.train.business.mapper.customer;


import org.apache.ibatis.annotations.Param;
import tech.songjian.train.business.domain.DailyTrainTicket;
import tech.songjian.train.business.domain.DailyTrainTicketExample;

import java.util.Date;
import java.util.List;

public interface DailyTrainTicketMapperCust {

    /**
     * 扣减库存
     * @param date
     * @param trainCode
     * @param seatTypeCode
     * @param minStartIndex
     * @param maxStartIndex
     * @param minEndIndex
     * @param maxEndIndex
     */
    void updateCountBySell(Date date
            , String trainCode
            , String seatTypeCode
            , Integer minStartIndex
            , Integer maxStartIndex
            , Integer minEndIndex
            , Integer maxEndIndex);
}
