/**
 * @projectName train
 * @package tech.songjian.train.member.controller
 * @className tech.songjian.train.member.controller.TestController
 */
package tech.songjian.train.member.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.songjian.train.common.resp.CommonResp;
import tech.songjian.train.member.req.MemberLoginReq;
import tech.songjian.train.member.req.MemberRegisterReq;
import tech.songjian.train.member.req.MemberSendCodeReq;
import tech.songjian.train.member.resp.MemberLoginResp;
import tech.songjian.train.member.service.MemberService;

/**
 * MemberController
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
    public CommonResp register(@Valid MemberRegisterReq req) {
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }

    /**
     * 发送短信验证码
     * @param req
     * @return
     */
    @PostMapping("/send-code")
    public CommonResp sendCode(@Valid @RequestBody MemberSendCodeReq req) {
        memberService.sendCode(req);
        return new CommonResp<>();
    }

    /**
     * 会员登入
     * @param req
     * @return
     */
    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody MemberLoginReq req) {
        MemberLoginResp login = memberService.login(req);
        return new CommonResp<>(login);
    }
}

