package tech.songjian.train.business.mapper.customer;

import java.util.Date;

public interface SkTokenMapperCust {

    int decrease(Date date, String trainCode, int decreaseCount);
}
