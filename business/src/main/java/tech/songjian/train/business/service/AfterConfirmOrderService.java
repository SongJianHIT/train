package tech.songjian.train.business.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.EnumUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.songjian.train.business.domain.*;
import tech.songjian.train.business.enums.ConfirmOrderStatusEnum;
import tech.songjian.train.business.enums.SeatColEnum;
import tech.songjian.train.business.enums.SeatTypeEnum;
import tech.songjian.train.business.mapper.ConfirmOrderMapper;
import tech.songjian.train.business.mapper.DailyTrainSeatMapper;
import tech.songjian.train.business.mapper.customer.DailyTrainTicketMapperCust;
import tech.songjian.train.business.req.ConfirmOrderDoReq;
import tech.songjian.train.business.req.ConfirmOrderQueryReq;
import tech.songjian.train.business.req.ConfirmOrderTicketReq;
import tech.songjian.train.business.resp.ConfirmOrderQueryResp;
import tech.songjian.train.common.context.LoginMemberContext;
import tech.songjian.train.common.exception.BusinessException;
import tech.songjian.train.common.exception.BusinessExceptionEnum;
import tech.songjian.train.common.resp.PageResp;
import tech.songjian.train.common.util.SnowUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 本类方法调用，事务不生效，所以需要把事务卸载这个类 AfterConfirmOrderService
 */
@Service
public class AfterConfirmOrderService {

    private static final Logger LOG = LoggerFactory.getLogger(AfterConfirmOrderService.class);

    @Resource
    private DailyTrainSeatMapper dailyTrainSeatMapper;

    @Resource
    private DailyTrainTicketMapperCust dailyTrainTicketMapperCust;
    /**
     * 选中座位后事务处理：
     *  1、座位表修改售卖情况sell；
     *  2、余票详情表修改余票；
     *  3、为会员增加购票记录
     *  4、更新确认订单为成功
     */
    @Transactional
    // @GlobalTransactional
    public void afterDoConfirm(DailyTrainTicket dailyTrainTicket, List<DailyTrainSeat> finalSeatList) {

        for (DailyTrainSeat seatForUpdate : finalSeatList) {
            // 1、座位表修改售卖情况sell；
            // 只更新座位售卖情况，所以创建一个新对象，只设置主键和销量
            DailyTrainSeat dailyTrainSeatDate = new DailyTrainSeat();
            dailyTrainSeatDate.setId(seatForUpdate.getId());
            dailyTrainSeatDate.setSell(dailyTrainSeatDate.getSell());
            dailyTrainSeatDate.setUpdateTime(new Date());
            dailyTrainSeatMapper.updateByPrimaryKeySelective(dailyTrainSeatDate);

            // 2、余票详情表修改余票
            // 计算这个站卖出去后，影响了哪些站的余票库存
            // 影响库存：没卖过票的， 和本次购买的区间有交集的区间
            // 假设10个站，本次买4~7站
            // 原售：001000001
            // 购买：000011100
            // 新售：001011101
            // 影响：XXX11111X
            // Integer startIndex = 4;
            // Integer endIndex = 7;
            // Integer minStartIndex = startIndex - 往前碰到的最后一个0;
            // Integer maxStartIndex = endIndex - 1;
            // Integer minEndIndex = startIndex + 1;
            // Integer maxEndIndex = endIndex + 往后碰到的最后一个0;
            Integer startIndex = dailyTrainTicket.getStartIndex();
            Integer endIndex = dailyTrainTicket.getEndIndex();
            char[] chars = seatForUpdate.getSell().toCharArray();
            Integer maxStartIndex = endIndex - 1;
            Integer minEndIndex = startIndex + 1;
            Integer minStartIndex = 0;
            for (int i = startIndex - 1; i >= 0; i--) {
                char aChar = chars[i];
                if (aChar == '1') {
                    minStartIndex = i + 1;
                    break;
                }
            }
            LOG.info("影响出发站区间：" + minStartIndex + "-" + maxStartIndex);
            Integer maxEndIndex = seatForUpdate.getSell().length();
            for (int i = endIndex; i < seatForUpdate.getSell().length(); i++) {
                char aChar = chars[i];
                if (aChar == '1') {
                    maxEndIndex = i;
                    break;
                }
            }
            LOG.info("影响到达站区间：" + minEndIndex + "-" + maxEndIndex);

            // 更新
            dailyTrainTicketMapperCust.updateCountBySell(
                    seatForUpdate.getDate(),
                    seatForUpdate.getTrainCode(),
                    seatForUpdate.getSeatType(),
                    minStartIndex,
                    maxStartIndex,
                    minEndIndex,
                    maxEndIndex);
        }
    }
}
