/**
 * @projectName train
 * @package tech.songjian.train.member.service
 * @className tech.songjian.train.member.service.MemberService
 */
package tech.songjian.train.member.service;

import tech.songjian.train.member.req.MemberLoginReq;
import tech.songjian.train.member.req.MemberRegisterReq;
import tech.songjian.train.member.req.MemberSendCodeReq;
import tech.songjian.train.member.resp.MemberLoginResp;

/**
 * MemberService
 * @description
 * @author SongJian
 * @date 2023/6/1 18:54
 * @version
 */
public interface MemberService {

    /**
     * 查询表中的行数
     * @return
     */
    public int count();

    /**
     * 会员注册
     * @return
     */
    public long register(MemberRegisterReq req);

    /**
     * 发送短信验证码
     * @param req
     * @return
     */
    public void sendCode(MemberSendCodeReq req);

    /**
     * 会员登入
     * @param req
     * @return
     */
    public MemberLoginResp login(MemberLoginReq req);
}

