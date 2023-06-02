/**
 * @projectName train
 * @package tech.songjian.train.member.controller
 * @className tech.songjian.train.member.controller.TestController
 */
package tech.songjian.train.member.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.songjian.train.common.context.LoginMemberContext;
import tech.songjian.train.common.resp.CommonResp;
import tech.songjian.train.common.resp.PageResp;
import tech.songjian.train.member.req.PassengerQueryReq;
import tech.songjian.train.member.req.PassengerSaveReq;
import tech.songjian.train.member.resp.PassengerQueryResp;
import tech.songjian.train.member.service.PassengerService;

/**
 * PassengerController
 * @description 测试
 * @author SongJian
 * @date 2023/6/1 10:57
 * @version
 */
@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    /**
     * 新增乘客
     * @param req
     * @return
     */
    @PostMapping("/save")
    public CommonResp login(@Valid @RequestBody PassengerSaveReq req) {
        passengerService.save(req);
        return new CommonResp<>();
    }

    /**
     * 根据会员id查询乘客列表
     * @param req
     * @return
     */
    @GetMapping("/query-list")
    public CommonResp queryList(@Valid PassengerQueryReq req) {
        // 从线程本地变量中获取 会员id
        req.setMemberId(LoginMemberContext.getId());
        PageResp<PassengerQueryResp> page = passengerService.queryList(req);
        return new CommonResp<>(page);
    }
}

