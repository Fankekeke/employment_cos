package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ResumeInfo;
import cc.mrbird.febs.cos.dao.ResumeInfoMapper;
import cc.mrbird.febs.cos.service.IResumeInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 简历管理 实现层
 *
 * @author FanK
 */
@Service
public class ResumeInfoServiceImpl extends ServiceImpl<ResumeInfoMapper, ResumeInfo> implements IResumeInfoService {

    /**
     * 分页获取简历管理信息
     *
     * @param page       分页对象
     * @param resumeInfo 简历管理信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectResumePage(Page<ResumeInfo> page, ResumeInfo resumeInfo) {
        return baseMapper.selectResumePage(page, resumeInfo);
    }
}
