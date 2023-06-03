package tech.songjian.train.business.mapper;

import org.apache.ibatis.annotations.Param;
import tech.songjian.train.business.domain.Train;
import tech.songjian.train.business.domain.TrainExample;

import java.util.List;

public interface TrainMapper {
    long countByExample(TrainExample example);

    int deleteByExample(TrainExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Train record);

    int insertSelective(Train record);

    List<Train> selectByExample(TrainExample example);

    Train selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Train record, @Param("example") TrainExample example);

    int updateByExample(@Param("record") Train record, @Param("example") TrainExample example);

    int updateByPrimaryKeySelective(Train record);

    int updateByPrimaryKey(Train record);
}
