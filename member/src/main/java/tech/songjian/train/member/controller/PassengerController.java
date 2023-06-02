/**
 * @projectName train
 * @package tech.songjian.train.member.controller
 * @className tech.songjian.train.member.controller.TestController
 */
package tech.songjian.train.member.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.songjian.train.common.resp.CommonResp;
import tech.songjian.train.member.req.PassengerSaveReq;
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
}

