package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.CollectInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 企业收藏 mapper层
 *
 * @author FanK
 */
public interface CollectInfoMapper extends BaseMapper<CollectInfo> {

    /**
     * 分页获取企业收藏信息
     *
     * @param page        分页对象
     * @param collectInfo 企业收藏信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectCollectPage(Page<CollectInfo> page, @Param("collectInfo") CollectInfo collectInfo);
}
