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
import tech.songjian.train.common.exception.BusinessException;
import tech.songjian.train.common.exception.BusinessExceptionEnum;
import tech.songjian.train.common.resp.CommonResp;
import tech.songjian.train.common.resp.PageResp;
import tech.songjian.train.member.req.PassengerQueryReq;
import tech.songjian.train.member.req.PassengerSaveReq;
import tech.songjian.train.member.resp.PassengerQueryResp;
import tech.songjian.train.member.service.PassengerService;

import java.util.List;

import static tech.songjian.train.common.exception.BusinessExceptionEnum.MEMBER_MAX_PASSENGER;

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
    public CommonResp save(@Valid @RequestBody PassengerSaveReq req) {
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

    /**
     * 按照id删除乘客
     * @param req
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        passengerService.delete(id);
        return new CommonResp<>();
    }

    /**
     * 查 member 绑定的所有乘客
     * @return
     */
    @GetMapping("/query-mine")
    public CommonResp<List<PassengerQueryResp>> queryMine() {
        List<PassengerQueryResp> list = passengerService.queryMine();
        return new CommonResp<>(list);
    }
}

