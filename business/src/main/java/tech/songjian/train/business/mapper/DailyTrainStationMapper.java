package tech.songjian.train.business.mapper;


import org.apache.ibatis.annotations.Param;
import tech.songjian.train.business.domain.DailyTrainStation;
import tech.songjian.train.business.domain.DailyTrainStationExample;

import java.util.List;

public interface DailyTrainStationMapper {
    long countByExample(DailyTrainStationExample example);

    int deleteByExample(DailyTrainStationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DailyTrainStation record);

    int insertSelective(DailyTrainStation record);

    List<DailyTrainStation> selectByExample(DailyTrainStationExample example);

    DailyTrainStation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DailyTrainStation record, @Param("example") DailyTrainStationExample example);

    int updateByExample(@Param("record") DailyTrainStation record, @Param("example") DailyTrainStationExample example);

    int updateByPrimaryKeySelective(DailyTrainStation record);

    int updateByPrimaryKey(DailyTrainStation record);
}
