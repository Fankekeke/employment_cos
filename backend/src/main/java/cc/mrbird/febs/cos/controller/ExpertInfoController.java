package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.FileDownloadUtils;
import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ExpertInfo;
import cc.mrbird.febs.cos.service.IExpertInfoService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
        return R.ok(expertInfoService.selectExpertPage(page, expertInfo));
    }

    @GetMapping("/key/list/{key}")
    public R listByKey(@PathVariable(value = "key", required = false) String key) {
        return R.ok(expertInfoService.list(Wrappers.<ExpertInfo>lambdaQuery()
                .like(StrUtil.isNotEmpty(key), ExpertInfo::getName, key).or()
                .like(StrUtil.isNotEmpty(key), ExpertInfo::getPoliticalStatus, key).or()
                .like(StrUtil.isNotEmpty(key), ExpertInfo::getEmployer, key).or()
                .like(StrUtil.isNotEmpty(key), ExpertInfo::getPosition, key).or()
                .like(StrUtil.isNotEmpty(key), ExpertInfo::getLevelOne, key).or()
                .like(StrUtil.isNotEmpty(key), ExpertInfo::getLevelTwo, key).eq(ExpertInfo::getOpenFlag, 1)));
    }

    /**
     * 根据专家编号查询详情
     *
     * @param expertCode 专家编号
     * @return 结果
     */
    @GetMapping("/detail/code/{expertCode}")
    public R detailByCode(@PathVariable("expertCode") String expertCode) {
        return R.ok(expertInfoService.getOne(Wrappers.<ExpertInfo>lambdaQuery().eq(ExpertInfo::getCode, expertCode)));
    }

    /**
     * 下载模板
     */
    @GetMapping("/template")
    public void downloadTemplate(HttpServletResponse response) {
        try {
            FileDownloadUtils.downloadTemplate(response, "专家基础数据.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入专家信息列表
     */
    @PostMapping("/import")
    public R importExcel(@RequestParam("file") MultipartFile file) {
        try {
            String errorMsg = expertInfoService.importExcel(file);
            if (StrUtil.isNotEmpty(errorMsg)) {
                return R.error(errorMsg);
            }
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error("导入异常");
    }

    /**
     * 根据企业编号获取推荐专家
     *
     * @param enterpriseCode 企业编号
     * @return 结果
     */
    @GetMapping("/recommend/{enterpriseCode}")
    public R selectExpertRecommend(@PathVariable("enterpriseCode") String enterpriseCode) {
        return R.ok(expertInfoService.selectExpertRecommend(enterpriseCode));
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
