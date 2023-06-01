/**
 * @projectName train
 * @package tech.songjian.train.member.service.impl
 * @className tech.songjian.train.member.service.impl.MemberServiceImpl
 */
package tech.songjian.train.member.service.impl;

import cn.hutool.core.collection.CollUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import tech.songjian.train.common.exception.BusinessException;
import tech.songjian.train.common.exception.BusinessExceptionEnum;
import tech.songjian.train.common.util.SnowUtil;
import tech.songjian.train.member.domain.Member;
import tech.songjian.train.member.domain.MemberExample;
import tech.songjian.train.member.mapper.MemberMapper;
import tech.songjian.train.member.req.MemberRegisterReq;
import tech.songjian.train.member.service.MemberService;

import java.util.List;

/**
 * MemberServiceImpl
 * @description
 * @author SongJian
 * @date 2023/6/1 18:56
 * @version
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Override
    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    /**
     * 会员注册
     * @return
     */
    @Override
    public long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        // 先查询该手机号是否存在
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if (CollUtil.isNotEmpty(members)) {
            throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        // 不存在则注册新用户
        Member member = new Member();
        // 雪花算法生成id
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}

