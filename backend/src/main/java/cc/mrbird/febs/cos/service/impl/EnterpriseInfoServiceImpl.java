package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.EnterpriseInfo;
import cc.mrbird.febs.cos.dao.EnterpriseInfoMapper;
import cc.mrbird.febs.cos.service.IEnterpriseInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 企业信息管理 实现层
 *
 * @author FanK
 */
@Service
public class EnterpriseInfoServiceImpl extends ServiceImpl<EnterpriseInfoMapper, EnterpriseInfo> implements IEnterpriseInfoService {

    /**
     * 分页获取企业信息信息
     *
     * @param page           分页对象
     * @param enterpriseInfo 企业信息信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectEnterprisePage(Page<EnterpriseInfo> page, EnterpriseInfo enterpriseInfo) {
        return baseMapper.selectEnterprisePage(page, enterpriseInfo);
    }
}
