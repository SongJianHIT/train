package tech.songjian.train.business.controller.admin;


import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import tech.songjian.train.business.req.TrainStationQueryReq;
import tech.songjian.train.business.req.TrainStationSaveReq;
import tech.songjian.train.business.resp.TrainStationQueryResp;
import tech.songjian.train.business.service.TrainStationService;
import tech.songjian.train.common.resp.CommonResp;
import tech.songjian.train.common.resp.PageResp;

@RestController
@RequestMapping("/admin/train-station")
public class TrainStationAdminController {

    @Resource
    private TrainStationService trainStationService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody TrainStationSaveReq req) {
        trainStationService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<TrainStationQueryResp>> queryList(@Valid TrainStationQueryReq req) {
        PageResp<TrainStationQueryResp> list = trainStationService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        trainStationService.delete(id);
        return new CommonResp<>();
    }

}
