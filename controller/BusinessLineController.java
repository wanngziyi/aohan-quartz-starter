package ..controller;

import ..entity.BusinessLine;
import ..service.BusinessLineService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 业务线(BusinessLine)表控制层
 *
 * @author makejava
 * @since 2020-12-23 17:40:28
 */
@RestController
@RequestMapping("businessLine")
public class BusinessLineController {
    /**
     * 服务对象
     */
    @Resource
    private BusinessLineService businessLineService;

    /**
     * 通过主键查询单条数据
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    @ApiOperation("获取业务线")
    public Result<BusinessLine> getOne(@PathVariable Object id) {
        return Result.success(businessLineService.selectById(id));
    }

}
