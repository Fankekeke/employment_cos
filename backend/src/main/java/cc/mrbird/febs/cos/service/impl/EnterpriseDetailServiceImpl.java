package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.EnterpriseDetail;
import cc.mrbird.febs.cos.dao.EnterpriseDetailMapper;
import cc.mrbird.febs.cos.service.IEnterpriseDetailService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 企业详情 实现层
 *
 * @author FanK
 */
@Service
public class EnterpriseDetailServiceImpl extends ServiceImpl<EnterpriseDetailMapper, EnterpriseDetail> implements IEnterpriseDetailService {

    /**
     * 分页获取企业详情信息
     *
     * @param page             分页对象
     * @param enterpriseDetail 企业详情信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectEnterprisePage(Page<EnterpriseDetail> page, EnterpriseDetail enterpriseDetail) {
        return baseMapper.selectEnterprisePage(page, enterpriseDetail);
    }
}
