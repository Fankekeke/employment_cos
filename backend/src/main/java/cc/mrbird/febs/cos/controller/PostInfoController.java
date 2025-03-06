package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.EnterpriseInfo;
import cc.mrbird.febs.cos.entity.ExpertInfo;
import cc.mrbird.febs.cos.entity.PostInfo;
import cc.mrbird.febs.cos.service.IEnterpriseInfoService;
import cc.mrbird.febs.cos.service.IExpertInfoService;
import cc.mrbird.febs.cos.service.IPostInfoService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    private final IExpertInfoService expertInfoService;

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

    @GetMapping("/queryPostRecommend")
    public R queryPostRecommend(Integer userId) {
        // 获取学生信息
        ExpertInfo expertInfo = expertInfoService.getOne(Wrappers.<ExpertInfo>lambdaQuery().eq(ExpertInfo::getUserId, userId));
        if (expertInfo == null) {
            List<PostInfo> postInfoList = postInfoService.list(Wrappers.<PostInfo>lambdaQuery().last("limit 10"));
            if (CollectionUtil.isEmpty(postInfoList)) {
                return R.ok(Collections.emptyList());
            }
            List<Integer> ids = postInfoList.stream().map(PostInfo::getId).collect(Collectors.toList());
            return R.ok(postInfoService.queryPostByIds(ids));
        }
        List<PostInfo> postInfoList = postInfoService.list(Wrappers.<PostInfo>lambdaQuery().like(StrUtil.isNotEmpty(expertInfo.getPosition()), PostInfo::getPostName, expertInfo.getPosition())
                .or()
                .like(StrUtil.isNotEmpty(expertInfo.getLevelOne()), PostInfo::getPostName, expertInfo.getLevelOne())
                .or()
                .like(StrUtil.isNotEmpty(expertInfo.getLevelTwo()), PostInfo::getPostName, expertInfo.getLevelTwo()).last("limit 10"));
        if (CollectionUtil.isEmpty(postInfoList)) {
            return R.ok(Collections.emptyList());
        }
        List<Integer> ids = postInfoList.stream().map(PostInfo::getId).collect(Collectors.toList());
        return R.ok(postInfoService.queryPostByIds(ids));
    }

    /**
     * 岗位信息上下架
     *
     * @param postId 岗位ID
     * @param status 状态
     * @return 结果
     */
    @GetMapping("/audit")
    public R audit(@RequestParam("postId") Integer postId, @RequestParam("status") Integer status) {
        return R.ok(postInfoService.update(Wrappers.<PostInfo>lambdaUpdate().set(PostInfo::getDelFlag, status).eq(PostInfo::getId, postId)));
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
        postInfo.setDelFlag(1);
        postInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
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
