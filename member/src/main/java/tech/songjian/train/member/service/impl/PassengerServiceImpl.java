/**
 * @projectName train
 * @package tech.songjian.train.member.service
 * @className tech.songjian.train.member.service.impl.PassengerServiceImpl
 */
package tech.songjian.train.member.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tech.songjian.train.common.context.LoginMemberContext;
import tech.songjian.train.common.util.SnowUtil;
import tech.songjian.train.member.domain.Passenger;
import tech.songjian.train.member.domain.PassengerExample;
import tech.songjian.train.member.mapper.PassengerMapper;
import tech.songjian.train.member.req.PassengerQueryReq;
import tech.songjian.train.member.req.PassengerSaveReq;
import tech.songjian.train.member.resp.PassengerQueryResp;
import tech.songjian.train.member.service.PassengerService;

import java.util.List;

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


    /**
     * 根据会员id查询乘客列表
     * @param req
     */
    @Override
    public List<PassengerQueryResp> queryList(PassengerQueryReq req) {
        // 创建查询条件
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        if (ObjectUtil.isNotNull(req.getMemberId())) {
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        // 在 sql 前开启分页即可！
        // 会对这句往下遇到的第一个 sql 做拦截，添加 limit 进行分页
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Passenger> passengers = passengerMapper.selectByExample(passengerExample);
        // 封装返回结果
        List<PassengerQueryResp> passengerQueryResp = BeanUtil.copyToList(passengers, PassengerQueryResp.class);
        return passengerQueryResp;
    }
}

