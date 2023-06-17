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
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tech.songjian.train.common.context.LoginMemberContext;
import tech.songjian.train.common.exception.BusinessException;
import tech.songjian.train.common.exception.BusinessExceptionEnum;
import tech.songjian.train.common.resp.PageResp;
import tech.songjian.train.common.util.SnowUtil;
import tech.songjian.train.member.domain.Passenger;
import tech.songjian.train.member.domain.PassengerExample;
import tech.songjian.train.member.mapper.PassengerMapper;
import tech.songjian.train.member.req.PassengerQueryReq;
import tech.songjian.train.member.req.PassengerSaveReq;
import tech.songjian.train.member.resp.PassengerQueryResp;
import tech.songjian.train.member.service.PassengerService;

import java.util.List;

import static tech.songjian.train.common.consts.PassenagerConst.MAX_PASSENGERNUMB;

/**
 * PassengerServiceImpl
 * @description
 * @author SongJian
 * @date 2023/6/2 20:09
 * @version
 */
@Service
public class PassengerServiceImpl implements PassengerService {

    private static final Logger LOG = LoggerFactory.getLogger(PassengerService.class);

    @Resource
    private PassengerMapper passengerMapper;

    /**
     * 新增乘客，编辑乘客
     * @param req
     */
    @Override
    public void save(PassengerSaveReq req) {

        PassengerExample passengerExample = new PassengerExample();
        passengerExample.createCriteria().andMemberIdEqualTo(LoginMemberContext.getId());
        long l = passengerMapper.countByExample(passengerExample);
        if (l >= MAX_PASSENGERNUMB) {
            // 如果超过设置最大的可添加乘客数，则不允许添加
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MAX_PASSENGER);
        }
        DateTime now = DateTime.now();
        Passenger passenger = new Passenger();
        // 如果没有id，则是新增保存
        BeanUtils.copyProperties(req, passenger);
        if (ObjectUtil.isNull(passenger.getId())) {
            // 重点：从 ThreadLocal 中获取当前登入的会员
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setCreateTime(now);
            passenger.setUpdateTime(now);
            passengerMapper.insert(passenger);
        } else {
            // 否则，是修改保存
            passenger.setUpdateTime(now);
            passengerMapper.updateByPrimaryKey(passenger);
        }
    }


    /**
     * 根据会员id查询乘客列表
     * @param req
     */
    @Override
    public PageResp<PassengerQueryResp> queryList(PassengerQueryReq req) {
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

        // 获取总条数
        PageInfo<Passenger> pageInfo = new PageInfo<>(passengers);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());
        List<PassengerQueryResp> list = BeanUtil.copyToList(passengers, PassengerQueryResp.class);

        // 封装返回结果
        PageResp<PassengerQueryResp> page = new PageResp<>();
        page.setTotal(pageInfo.getTotal());
        page.setList(list);
        return page;
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void delete(Long id) {
        passengerMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询我的所有乘客
     */
    public List<PassengerQueryResp> queryMine() {
        PassengerExample passengerExample = new PassengerExample();
        passengerExample.setOrderByClause("name asc");
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        criteria.andMemberIdEqualTo(LoginMemberContext.getId());
        List<Passenger> list = passengerMapper.selectByExample(passengerExample);
        return BeanUtil.copyToList(list, PassengerQueryResp.class);
    }
}

