package tech.songjian.train.business.controller;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.songjian.train.business.req.DailyTrainTicketQueryReq;
import tech.songjian.train.business.resp.DailyTrainTicketQueryResp;
import tech.songjian.train.business.service.DailyTrainTicketService;
import tech.songjian.train.common.resp.CommonResp;
import tech.songjian.train.common.resp.PageResp;

@RestController
@RequestMapping("/daily-train-ticket")
public class DailyTrainTicketController {

    @Resource
    private DailyTrainTicketService dailyTrainTicketService;

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainTicketQueryResp>> queryList(@Valid DailyTrainTicketQueryReq req) {
        PageResp<DailyTrainTicketQueryResp> list = dailyTrainTicketService.queryList(req);
        return new CommonResp<>(list);
    }

}
