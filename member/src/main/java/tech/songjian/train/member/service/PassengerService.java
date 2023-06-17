/**
 * @projectName train
 * @package tech.songjian.train.member.service
 * @className tech.songjian.train.member.service.PassengerService
 */
package tech.songjian.train.member.service;

import tech.songjian.train.common.resp.PageResp;
import tech.songjian.train.member.req.PassengerQueryReq;
import tech.songjian.train.member.req.PassengerSaveReq;
import tech.songjian.train.member.resp.PassengerQueryResp;

import java.util.List;

/**
 * PassengerService
 * @description 乘客service接口
 * @author SongJian
 * @date 2023/6/2 20:08
 * @version
 */
public interface PassengerService {

    /**
     * 新增乘客
     * @param req
     */
    public void save(PassengerSaveReq req);

    /**
     * 根据会员id查询其乘客列表
     * @param req
     */
    public PageResp<PassengerQueryResp> queryList(PassengerQueryReq req);

    /**
     * 根据id删除
     * @param id
     */
    public void delete(Long id);

    List<PassengerQueryResp> queryMine();

}
