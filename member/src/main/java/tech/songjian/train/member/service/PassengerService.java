/**
 * @projectName train
 * @package tech.songjian.train.member.service
 * @className tech.songjian.train.member.service.PassengerService
 */
package tech.songjian.train.member.service;

import tech.songjian.train.member.req.PassengerSaveReq;

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
}
