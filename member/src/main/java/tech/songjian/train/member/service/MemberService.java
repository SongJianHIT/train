/**
 * @projectName train
 * @package tech.songjian.train.member.service
 * @className tech.songjian.train.member.service.MemberService
 */
package tech.songjian.train.member.service;

import tech.songjian.train.member.req.MemberRegisterReq;

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
}

