package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ExpertInfo;
import cc.mrbird.febs.cos.service.IExpertInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生信息管理 控制层
 *
 * @author FanK
 */
@RestController
@RequestMapping("/cos/expert-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExpertInfoController {

    private final IExpertInfoService expertInfoService;

    /**
     * 分页获取学生信息信息
     *
     * @param page       分页对象
     * @param expertInfo 学生信息信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ExpertInfo> page, ExpertInfo expertInfo) {
        return R.ok();
    }

    /**
     * 查询学生信息信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(expertInfoService.getById(id));
    }

    /**
     * 查询学生信息信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(expertInfoService.list());
    }

    /**
     * 新增学生信息信息
     *
     * @param expertInfo 学生信息信息
     * @return 结果
     */
    @PostMapping
    public R save(ExpertInfo expertInfo) {
        return R.ok(expertInfoService.save(expertInfo));
    }

    /**
     * 修改学生信息信息
     *
     * @param expertInfo 学生信息信息
     * @return 结果
     */
    @PutMapping
    public R edit(ExpertInfo expertInfo) {
        return R.ok(expertInfoService.updateById(expertInfo));
    }

    /**
     * 删除学生信息信息
     *
     * @param ids ids
     * @return 学生信息信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(expertInfoService.removeByIds(ids));
    }
}
