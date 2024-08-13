package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ResumeInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 简历管理 service层
 *
 * @author FanK
 */
public interface IResumeInfoService extends IService<ResumeInfo> {

    /**
     * 分页获取简历管理信息
     *
     * @param page       分页对象
     * @param resumeInfo 简历管理信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectResumePage(Page<ResumeInfo> page, ResumeInfo resumeInfo);
}
