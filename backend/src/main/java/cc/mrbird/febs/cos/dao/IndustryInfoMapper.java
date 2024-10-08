package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.IndustryInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 所属行业 mapper层
 *
 * @author FanK
 */
public interface IndustryInfoMapper extends BaseMapper<IndustryInfo> {

    /**
     * 分页获取所属行业信息信息
     *
     * @param page         分页对象
     * @param industryInfo 所属行业信息信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectIndustryPage(Page<IndustryInfo> page, @Param("industryInfo") IndustryInfo industryInfo);
}
