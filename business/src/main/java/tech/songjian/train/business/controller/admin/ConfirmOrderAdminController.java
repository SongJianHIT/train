package tech.songjian.train.business.controller.admin;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.songjian.train.business.req.ConfirmOrderDoReq;
import tech.songjian.train.business.req.ConfirmOrderQueryReq;
import tech.songjian.train.business.resp.ConfirmOrderQueryResp;
import tech.songjian.train.business.service.ConfirmOrderService;
import tech.songjian.train.common.resp.CommonResp;
import tech.songjian.train.common.resp.PageResp;

@RestController
@RequestMapping("/admin/confirm-order")
public class ConfirmOrderAdminController {

    @Resource
    private ConfirmOrderService confirmOrderService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody ConfirmOrderDoReq req) {
        confirmOrderService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<ConfirmOrderQueryResp>> queryList(@Valid ConfirmOrderQueryReq req) {
        PageResp<ConfirmOrderQueryResp> list = confirmOrderService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        confirmOrderService.delete(id);
        return new CommonResp<>();
    }

}
