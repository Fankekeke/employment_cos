package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.PostInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 岗位管理 service层
 *
 * @author FanK
 */
public interface IPostInfoService extends IService<PostInfo> {

    /**
     * 分页获取岗位管理信息
     *
     * @param page     分页对象
     * @param postInfo 岗位管理信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectPostPage(Page<PostInfo> page, PostInfo postInfo);

    /**
     * 根据id查询岗位信息
     *
     * @param ids id集合
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryPostByIds(List<Integer> ids);
}
