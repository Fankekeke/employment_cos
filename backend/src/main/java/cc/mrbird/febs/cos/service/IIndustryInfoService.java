package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.IndustryInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 所属行业 service层
 *
 * @author FanK
 */
public interface IIndustryInfoService extends IService<IndustryInfo> {

    /**
     * 分页获取所属行业信息信息
     *
     * @param page         分页对象
     * @param industryInfo 所属行业信息信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectIndustryPage(Page<IndustryInfo> page, IndustryInfo industryInfo);
}
