package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ExpertInfo;
import cc.mrbird.febs.cos.entity.InterviewInfo;
import cc.mrbird.febs.cos.service.IExpertInfoService;
import cc.mrbird.febs.cos.service.IInterviewInfoService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 面试管理 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/interview-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterviewInfoController {

    private final IInterviewInfoService interviewInfoService;

    private final IExpertInfoService expertInfoService;

    /**
     * 分页获取所属面试管理信息
     *
     * @param page          分页对象
     * @param interviewInfo 所属面试管理信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<InterviewInfo> page, InterviewInfo interviewInfo) {
        return R.ok(interviewInfoService.selectInterviewPage(page, interviewInfo));
    }

    /**
     * 查询所属面试管理信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(interviewInfoService.getById(id));
    }

    /**
     * 查询所属面试管理信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(interviewInfoService.list());
    }

    /**
     * 新增所属面试管理信息
     *
     * @param interviewInfo 所属面试管理信息
     * @return 结果
     */
    @PostMapping
    public R save(InterviewInfo interviewInfo) {
        ExpertInfo expertInfo = expertInfoService.getOne(Wrappers.<ExpertInfo>lambdaQuery().eq(ExpertInfo::getUserId, interviewInfo.getExpertId()));
        if (expertInfo != null) {
            interviewInfo.setExpertId(expertInfo.getId());
        }
        return R.ok(interviewInfoService.save(interviewInfo));
    }

    /**
     * 修改所属面试管理信息
     *
     * @param interviewInfo 所属面试管理信息
     * @return 结果
     */
    @PutMapping
    public R edit(InterviewInfo interviewInfo) {
        return R.ok(interviewInfoService.updateById(interviewInfo));
    }

    /**
     * 删除所属面试管理信息
     *
     * @param ids ids
     * @return 所属面试管理信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(interviewInfoService.removeByIds(ids));
    }
}
