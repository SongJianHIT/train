package tech.songjian.train.business.controller.admin;

import tech.songjian.train.common.context.LoginMemberContext;
import tech.songjian.train.common.resp.CommonResp;
import tech.songjian.train.common.resp.PageResp;
import tech.songjian.train.business.req.SkTokenQueryReq;
import tech.songjian.train.business.req.SkTokenSaveReq;
import tech.songjian.train.business.resp.SkTokenQueryResp;
import tech.songjian.train.business.service.SkTokenService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/sk-token")
public class SkTokenAdminController {

    @Resource
    private SkTokenService skTokenService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody SkTokenSaveReq req) {
        skTokenService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<SkTokenQueryResp>> queryList(@Valid SkTokenQueryReq req) {
        PageResp<SkTokenQueryResp> list = skTokenService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        skTokenService.delete(id);
        return new CommonResp<>();
    }

}
