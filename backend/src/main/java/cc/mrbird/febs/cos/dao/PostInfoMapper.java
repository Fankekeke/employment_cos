package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.PostInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 岗位管理 mapper层
 *
 * @author FanK
 */
public interface PostInfoMapper extends BaseMapper<PostInfo> {

    /**
     * 分页获取岗位管理信息
     *
     * @param page     分页对象
     * @param postInfo 岗位管理信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectPostPage(Page<PostInfo> page, @Param("postInfo") PostInfo postInfo);

    /**
     * 根据id查询岗位信息
     *
     * @param ids id集合
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryPostByIds(@Param("ids") List<Integer> ids);
}
