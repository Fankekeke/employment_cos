package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.InterviewInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.LinkedHashMap;

/**
 * 面试管理 service层
 *
 * @author FanK
 */
public interface IInterviewInfoService extends IService<InterviewInfo> {

    /**
     * 分页获取所属面试管理信息
     *
     * @param page          分页对象
     * @param interviewInfo 所属面试管理信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectInterviewPage(Page<InterviewInfo> page, InterviewInfo interviewInfo);

    /**
     * 获取主页统计数据
     *
     * @return 结果
     */
    LinkedHashMap<String, Object> homeData(Integer userId);
}
