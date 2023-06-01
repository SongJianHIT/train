/**
 * @projectName train
 * @package tech.songjian.train.member.controller
 * @className tech.songjian.train.member.controller.TestController
 */
package tech.songjian.train.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.songjian.train.common.resp.CommonResp;
import tech.songjian.train.member.req.MemberRegisterReq;
import tech.songjian.train.member.service.MemberService;

/**
 * TestController
 * @description 测试
 * @author SongJian
 * @date 2023/6/1 10:57
 * @version
 */
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp count() {
        CommonResp<Integer> integerCommonResp = new CommonResp<>();
        integerCommonResp.setContent(memberService.count());
        return integerCommonResp;
    }

    /**
     * 会员注册
     * @param req
     * @return
     */
    @PostMapping("/register")
    public CommonResp register(MemberRegisterReq req) {
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }
}

