package tech.songjian.train.member.controller.admin;

import tech.songjian.train.common.resp.CommonResp;
import tech.songjian.train.common.resp.PageResp;
import tech.songjian.train.member.req.TicketQueryReq;
import tech.songjian.train.member.resp.TicketQueryResp;
import tech.songjian.train.member.service.TicketService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/ticket")
public class TicketAdminController {

    @Resource
    private TicketService ticketService;

    @GetMapping("/query-list")
    public CommonResp<PageResp<TicketQueryResp>> queryList(@Valid TicketQueryReq req) {
        PageResp<TicketQueryResp> list = ticketService.queryList(req);
        return new CommonResp<>(list);
    }

}
