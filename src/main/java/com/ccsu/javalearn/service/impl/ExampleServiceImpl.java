package com.ccsu.javalearn.service.impl;

import com.ccsu.javalearn.common.Const;
import com.ccsu.javalearn.dao.ExampleMapper;
import com.ccsu.javalearn.pojo.Example;
import com.ccsu.javalearn.service.IExampleService;
import com.ccsu.javalearn.service.IFileService;
import com.ccsu.javalearn.service.IMenuService;
import com.ccsu.javalearn.util.IdFactory;
import com.ccsu.javalearn.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-30 20:01
 */
@Service
public class ExampleServiceImpl implements IExampleService {

    @Autowired
    private ExampleMapper exampleMapper;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private IdFactory idFactory;


    @Override
    public Result saveExample(Example example, long menuIId, MultipartFile file,String path) {
        if (file == null){
            example.setAttachment(null);
            exampleMapper.update(example);
        }else {
            String url = fileService.upload(file,path);
            example.setAttachment(url);
        }

        if (example.getId() == Const.NEW_EXAMPLE) {

//            Result result = menuService.addNewMenu(menuIId, example.getTitle());

//            long menuId = (long) result.getData();
            long id = idFactory.createExample();
            example.setId(id);
            example.setMenuId(menuIId);
            exampleMapper.insert(example);
        }else {
            exampleMapper.update(example);
        }
        return Result.success(0);
    }

    @Override
    public Example getExample(long menuId) {

        Example example = exampleMapper.selectByMenuId(menuId);
        example.setScanCount(example.getScanCount()+1);
        exampleMapper.update(example);
        return example;
    }

    @Override
    public Example getExampleById(long id) {
        return exampleMapper.selectById(id);
    }
}
