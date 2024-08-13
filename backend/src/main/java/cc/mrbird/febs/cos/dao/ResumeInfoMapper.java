package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ResumeInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 简历管理 mapper层
 *
 * @author FanK
 */
public interface ResumeInfoMapper extends BaseMapper<ResumeInfo> {

    /**
     * 分页获取简历管理信息
     *
     * @param page       分页对象
     * @param resumeInfo 简历管理信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectResumePage(Page<ResumeInfo> page, @Param("resumeInfo") ResumeInfo resumeInfo);
}
