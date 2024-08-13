package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.EnterpriseDetail;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 企业详情 service层
 *
 * @author FanK
 */
public interface IEnterpriseDetailService extends IService<EnterpriseDetail> {

    /**
     * 分页获取企业详情信息
     *
     * @param page             分页对象
     * @param enterpriseDetail 企业详情信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectEnterprisePage(Page<EnterpriseDetail> page, EnterpriseDetail enterpriseDetail);
}
