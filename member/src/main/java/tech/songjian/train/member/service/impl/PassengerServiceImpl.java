/**
 * @projectName train
 * @package tech.songjian.train.member.service
 * @className tech.songjian.train.member.service.impl.PassengerServiceImpl
 */
package tech.songjian.train.member.service.impl;

import cn.hutool.core.date.DateTime;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tech.songjian.train.common.context.LoginMemberContext;
import tech.songjian.train.common.util.SnowUtil;
import tech.songjian.train.member.domain.Passenger;
import tech.songjian.train.member.mapper.PassengerMapper;
import tech.songjian.train.member.req.PassengerSaveReq;
import tech.songjian.train.member.service.PassengerService;

/**
 * PassengerServiceImpl
 * @description
 * @author SongJian
 * @date 2023/6/2 20:09
 * @version
 */
@Service
public class PassengerServiceImpl implements PassengerService {

    @Resource
    private PassengerMapper passengerMapper;

    /**
     * 新增乘客
     * @param req
     */
    @Override
    public void save(PassengerSaveReq req) {
        DateTime now = DateTime.now();
        Passenger passenger = new Passenger();
        BeanUtils.copyProperties(req, passenger);
        // 重点：从 ThreadLocal 中获取当前登入的会员
        passenger.setMemberId(LoginMemberContext.getId());
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);
    }
}

