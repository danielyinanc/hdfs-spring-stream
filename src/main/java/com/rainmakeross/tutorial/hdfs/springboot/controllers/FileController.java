package com.rainmakeross.tutorial.hdfs.springboot.controllers;

import com.rainmakeross.tutorial.hdfs.springboot.services.HdfsFileAccess;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.fs.Hdfs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class FileController {
    @Autowired
    HdfsFileAccess hdfsFileAccess;

    @RequestMapping("/file")
    public void getFileContents(HttpServletResponse response) throws IOException {
        response.addHeader("Content-disposition", "attachment;filename=myfilename.txt");
        response.setContentType("txt/plain");
        IOUtils.copy(hdfsFileAccess.readFile("/user/dyinanc/test.txt"), response.getOutputStream());
        response.flushBuffer();
    }
}
