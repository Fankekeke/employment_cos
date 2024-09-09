package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.EnterpriseInfo;
import cc.mrbird.febs.cos.entity.PostInfo;
import cc.mrbird.febs.cos.service.IEnterpriseInfoService;
import cc.mrbird.febs.cos.service.IPostInfoService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 岗位管理 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/post-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PostInfoController {

    private final IPostInfoService postInfoService;

    private final IEnterpriseInfoService enterpriseInfoService;

    /**
     * 分页获取岗位管理信息
     *
     * @param page     分页对象
     * @param postInfo 岗位管理信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<PostInfo> page, PostInfo postInfo) {
        return R.ok(postInfoService.selectPostPage(page, postInfo));
    }

    /**
     * 查询岗位管理信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(postInfoService.getById(id));
    }

    /**
     * 查询岗位管理信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(postInfoService.list());
    }

    /**
     * 新增岗位管理信息
     *
     * @param postInfo 岗位管理信息
     * @return 结果
     */
    @PostMapping
    public R save(PostInfo postInfo) {
        // 岗位编号
        postInfo.setCode("PT-" + System.currentTimeMillis());
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.getOne(Wrappers.<EnterpriseInfo>lambdaQuery().eq(EnterpriseInfo::getUserId, postInfo.getEnterpriseId()));
        if (enterpriseInfo != null) {
            postInfo.setEnterpriseId(enterpriseInfo.getId());
        }
        return R.ok(postInfoService.save(postInfo));
    }

    /**
     * 修改岗位管理信息
     *
     * @param postInfo 岗位管理信息
     * @return 结果
     */
    @PutMapping
    public R edit(PostInfo postInfo) {
        return R.ok(postInfoService.updateById(postInfo));
    }

    /**
     * 删除岗位管理信息
     *
     * @param ids ids
     * @return 岗位管理信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(postInfoService.removeByIds(ids));
    }
}
