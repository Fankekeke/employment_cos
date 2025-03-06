package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.InterviewInfo;
import cc.mrbird.febs.cos.entity.ReserveInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 面试管理 mapper层
 *
 * @author FanK
 */
public interface InterviewInfoMapper extends BaseMapper<InterviewInfo> {

    /**
     * 分页获取所属面试管理信息
     *
     * @param page          分页对象
     * @param interviewInfo 所属面试管理信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectInterviewPage(Page<InterviewInfo> page, @Param("interviewInfo") InterviewInfo interviewInfo);

    /**
     * 根据面试ID获取岗位面试信息
     *
     * @param ids 面试IDS
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectInterViewPostByIds(@Param("ids") List<Integer> ids);

    /**
     * 本月预约信息
     *
     * @param staffId 员工ID
     * @return 结果
     */
    List<ReserveInfo> selectOrderByMonth(@Param("staffId") Integer staffId);

    /**
     * 本年预约信息
     *
     * @param staffId 员工ID
     * @return 结果
     */
    List<ReserveInfo> selectOrderByYear(@Param("staffId") Integer staffId);

    /**
     * 本月面试投递信息
     *
     * @param staffId 员工ID
     * @return 结果
     */
    List<InterviewInfo> selectInterviewByMonth(@Param("staffId") Integer staffId);

    /**
     * 本年面试投递信息
     *
     * @param staffId 员工ID
     * @return 结果
     */
    List<InterviewInfo> selectInterviewByYear(@Param("staffId") Integer staffId);

    /**
     * 十天内订单数量统计
     *
     * @param staffId 员工ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectOrderNumWithinDays(@Param("staffId") Integer staffId);

    /**
     * 十天内订单收益统计
     *
     * @param staffId 员工ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectOrderPriceWithinDays(@Param("staffId") Integer staffId);
}
