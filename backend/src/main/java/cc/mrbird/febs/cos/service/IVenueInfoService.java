package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.VenueInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 会场信息 service层
 *
 * @author FanK
 */
public interface IVenueInfoService extends IService<VenueInfo> {

    /**
     * 分页获取会场信息信息
     *
     * @param page      分页对象
     * @param venueInfo 会场信息信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectVenuePage(Page<VenueInfo> page, VenueInfo venueInfo);
}
