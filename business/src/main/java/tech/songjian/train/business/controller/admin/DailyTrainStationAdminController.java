package tech.songjian.train.business.controller.admin;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.songjian.train.business.req.DailyTrainStationQueryReq;
import tech.songjian.train.business.req.DailyTrainStationSaveReq;
import tech.songjian.train.business.resp.DailyTrainStationQueryResp;
import tech.songjian.train.business.service.DailyTrainStationService;
import tech.songjian.train.common.resp.CommonResp;
import tech.songjian.train.common.resp.PageResp;

@RestController
@RequestMapping("/admin/daily-train-station")
public class DailyTrainStationAdminController {

    @Resource
    private DailyTrainStationService dailyTrainStationService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody DailyTrainStationSaveReq req) {
        dailyTrainStationService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<DailyTrainStationQueryResp>> queryList(@Valid DailyTrainStationQueryReq req) {
        PageResp<DailyTrainStationQueryResp> list = dailyTrainStationService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        dailyTrainStationService.delete(id);
        return new CommonResp<>();
    }

}
