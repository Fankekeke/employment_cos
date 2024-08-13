package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.VenueInfo;
import cc.mrbird.febs.cos.dao.VenueInfoMapper;
import cc.mrbird.febs.cos.service.IVenueInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 会场信息 实现层
 *
 * @author FanK
 */
@Service
public class VenueInfoServiceImpl extends ServiceImpl<VenueInfoMapper, VenueInfo> implements IVenueInfoService {

    /**
     * 分页获取会场信息信息
     *
     * @param page      分页对象
     * @param venueInfo 会场信息信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectVenuePage(Page<VenueInfo> page, VenueInfo venueInfo) {
        return baseMapper.selectVenuePage(page, venueInfo);
    }
}
