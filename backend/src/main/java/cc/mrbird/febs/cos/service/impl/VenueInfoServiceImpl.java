package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.VenueInfo;
import cc.mrbird.febs.cos.dao.VenueInfoMapper;
import cc.mrbird.febs.cos.service.IVenueInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 会场信息 实现层
 *
 * @author FanK
 */
@Service
public class VenueInfoServiceImpl extends ServiceImpl<VenueInfoMapper, VenueInfo> implements IVenueInfoService {

}
