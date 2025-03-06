package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.PostInfo;
import cc.mrbird.febs.cos.dao.PostInfoMapper;
import cc.mrbird.febs.cos.service.IPostInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 岗位管理 实现层
 *
 * @author FanK
 */
@Service
public class PostInfoServiceImpl extends ServiceImpl<PostInfoMapper, PostInfo> implements IPostInfoService {

    /**
     * 分页获取岗位管理信息
     *
     * @param page     分页对象
     * @param postInfo 岗位管理信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectPostPage(Page<PostInfo> page, PostInfo postInfo) {
        return baseMapper.selectPostPage(page, postInfo);
    }

    /**
     * 根据id查询岗位信息
     *
     * @param ids id集合
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> queryPostByIds(List<Integer> ids) {
        return baseMapper.queryPostByIds(ids);
    }
}
