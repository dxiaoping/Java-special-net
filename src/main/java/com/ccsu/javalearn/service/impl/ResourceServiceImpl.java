package com.ccsu.javalearn.service.impl;

import com.ccsu.javalearn.common.Const;
import com.ccsu.javalearn.common.Result;
import com.ccsu.javalearn.dao.ResourceMapper;
import com.ccsu.javalearn.pojo.Resource;
import com.ccsu.javalearn.service.IFileService;
import com.ccsu.javalearn.service.IResourceService;
import com.ccsu.javalearn.util.IdFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-11-30 23:23
 */
@Service
public class ResourceServiceImpl implements IResourceService {


    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private IdFactory idFactory;

    @Autowired
    private IFileService fileService;

    @Override
    public Result getResourceList(long menuId) {
        List<Resource> resourceList = resourceMapper.selectResourceByMenuId(menuId);
        return Result.success(resourceList);
    }

    @Override
    public int addResource(MultipartFile file, Resource resource) {

        long id = idFactory.createResource();

        String url = fileService.upload(file, resource.getUrl());
        resource.setId(id);
        resource.setName(file.getOriginalFilename());
        resource.setUrl(url);
        int res = resourceMapper.insert(resource);

        return res;
    }
}
