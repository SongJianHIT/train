/**
 * @projectName train
 * @package tech.songjian.train.member.service.impl
 * @className tech.songjian.train.member.service.impl.MemberServiceImpl
 */
package tech.songjian.train.member.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import tech.songjian.train.common.exception.BusinessException;
import tech.songjian.train.common.exception.BusinessExceptionEnum;
import tech.songjian.train.common.util.SnowUtil;
import tech.songjian.train.member.domain.Member;
import tech.songjian.train.member.domain.MemberExample;
import tech.songjian.train.member.mapper.MemberMapper;
import tech.songjian.train.member.req.MemberRegisterReq;
import tech.songjian.train.member.req.MemberSendCodeReq;
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

    /**
     * 发送短信验证码
     * @param req
     * @return
     */
    @Override
    public void sendCode(MemberSendCodeReq req) {
        // 判断手机号是否注册过
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);
        if (CollUtil.isEmpty(members)) {
            // 没有注册过，注册新用户
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        }
        // 生成验证码
        String code = RandomUtil.randomString(4);
        // TODO 保存短信记录表：手机号、短信验证码、有效期、是否已使用、业务类型（注册或忘记密码）、发送时间、使用时间
        // TODO 对接短信通道，发送短信
    }
}

