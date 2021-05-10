package com.js.service.system;

import com.js.mapper.system.SysDictMapper;
import com.js.pojo.system.SysDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;

    public SysDict getById(Long id) {
        return sysDictMapper.selectById(id);
    }

    public List<SysDict> listByEntity(SysDict sysDict) {
        return sysDictMapper.selectList(sysDict);
    }

    /**
     * @return
     * @Description: 添加字典
     * @Param [sysDict]
     * @Author: 渡劫 dujie
     * @Date: 2021/5/10 4:18 PM
     */
    public int insert(SysDict sysDict) {
        SysDict tempSysDict = sysDictMapper.selectByCodeAndKey(sysDict.getCode(), sysDict.getParamKey());
        if (Objects.isNull(tempSysDict)) {
            sysDictMapper.insert(sysDict);
            return 1;
        }
        return 0;
    }

    /**
     * @return
     * @Description: 修改逻辑
     * @Param [sysDict]
     * @Author: 渡劫 dujie
     * @Date: 2021/5/10 4:21 PM
     */
    public int update(SysDict sysDict) {
        return sysDictMapper.update(sysDict);
    }

    /**
     * @return
     * @Description: 删除逻辑
     * @Param [id]
     * @Author: 渡劫 dujie
     * @Date: 2021/5/10 4:21 PM
     */
    public int deleteById(Long id) {
        return sysDictMapper.deleteById(id);
    }

    /**
     * @return
     * @Description: 根据条件删除
     * @Param [sysDict]
     * @Author: 渡劫 dujie
     * @Date: 2021/5/10 4:21 PM
     */
    public int deleteByEntity(SysDict sysDict) {
        return sysDictMapper.deleteByEntity(sysDict);
    }

    /**
     * @return
     * @Description: 根据id批量删除
     * @Param [list]
     * @Author: 渡劫 dujie
     * @Date: 2021/5/10 4:21 PM
     */
    public int deleteByIds(List<Long> list) {
        return sysDictMapper.deleteByIds(list);
    }

    /**
     * @return
     * @Description: 条件查询总的条数
     * @Param [sysDict]
     * @Author: 渡劫 dujie
     * @Date: 2021/5/10 4:22 PM
     */
    public int countByEntity(SysDict sysDict) {
        return sysDictMapper.countByEntity(sysDict);
    }

}