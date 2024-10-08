package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ReserveInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 学生会场预约 service层
 *
 * @author FanK
 */
public interface IReserveInfoService extends IService<ReserveInfo> {

    /**
     * 分页获取学生会场预约信息
     *
     * @param page        分页对象
     * @param reserveInfo 学生会场预约信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectReservePage(Page<ReserveInfo> page, ReserveInfo reserveInfo);

    /**
     * 查询学生会场预约信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    LinkedHashMap<String, Object> queryReserveDetail(Integer id);
}
