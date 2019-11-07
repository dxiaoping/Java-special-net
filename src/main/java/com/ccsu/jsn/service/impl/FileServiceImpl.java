package com.ccsu.jsn.service.impl;

import com.ccsu.jsn.common.Result;
import com.ccsu.jsn.service.IFileService;
import com.ccsu.jsn.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.common.collect.Lists;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Description
 * @auther DuanXiaoping
 * @create 2019-10-31 21:50
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService {

    Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String upload(MultipartFile file, String path) {
        return null;
    }

}
