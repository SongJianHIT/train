package tech.songjian.train.business.controller.admin;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.songjian.train.business.req.DailyTrainTicketQueryReq;
import tech.songjian.train.business.req.DailyTrainTicketSaveReq;
import tech.songjian.train.business.resp.DailyTrainTicketQueryResp;
import tech.songjian.train.business.service.DailyTrainTicketService;
import tech.songjian.train.common.resp.CommonResp;
import tech.songjian.train.common.resp.PageResp;

@RestController
@RequestMapping("/admin/daily-train-ticket")
public class DailyTrainTicketAdminController {

    @Resource
    private DailyTrainTicketService dailyTrainTicketService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody DailyTrainTicketSaveReq req) {
        dailyTrainTicketService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainTicketQueryResp>> queryList(@Valid DailyTrainTicketQueryReq req) {
        PageResp<DailyTrainTicketQueryResp> list = dailyTrainTicketService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        dailyTrainTicketService.delete(id);
        return new CommonResp<>();
    }

}
