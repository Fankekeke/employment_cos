package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.EnterpriseDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 企业详情 mapper层
 *
 * @author FanK
 */
public interface EnterpriseDetailMapper extends BaseMapper<EnterpriseDetail> {

    /**
     * 分页获取企业详情信息
     *
     * @param page             分页对象
     * @param enterpriseDetail 企业详情信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectEnterprisePage(Page<EnterpriseDetail> page, @Param("enterpriseDetail") EnterpriseDetail enterpriseDetail);
}
