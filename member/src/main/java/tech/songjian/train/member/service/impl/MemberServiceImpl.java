/**
 * @projectName train
 * @package tech.songjian.train.member.service.impl
 * @className tech.songjian.train.member.service.impl.MemberServiceImpl
 */
package tech.songjian.train.member.service.impl;

import jakarta.annotation.Resource;
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

    @Resource
    private MemberMapper memberMapper;

    @Override
    public int count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }
}

