/**
 * @projectName train
 * @package tech.songjian.train.member.service.impl
 * @className tech.songjian.train.member.service.impl.MemberServiceImpl
 */
package tech.songjian.train.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.songjian.train.member.mapper.MemberMapper;
import tech.songjian.train.member.service.MemberService;

/**
 * MemberServiceImpl
 * @description
 * @author SongJian
 * @date 2023/6/1 18:56
 * @version
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 查询表中的行数
     * @return
     */
    @Override
    public int count() {
        return memberMapper.count();
    }
}

