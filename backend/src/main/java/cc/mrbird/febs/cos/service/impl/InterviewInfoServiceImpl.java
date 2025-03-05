package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.InterviewInfo;
import cc.mrbird.febs.cos.dao.InterviewInfoMapper;
import cc.mrbird.febs.cos.service.IInterviewInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 面试管理 实现层
 *
 * @author FanK
 */
@Service
public class InterviewInfoServiceImpl extends ServiceImpl<InterviewInfoMapper, InterviewInfo> implements IInterviewInfoService {

    /**
     * 分页获取所属面试管理信息
     *
     * @param page          分页对象
     * @param interviewInfo 所属面试管理信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectInterviewPage(Page<InterviewInfo> page, InterviewInfo interviewInfo) {
        return baseMapper.selectInterviewPage(page, interviewInfo);
    }

    /**
     * 获取主页统计数据
     *
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> homeData(Integer userId) {
        return null;
    }
}
