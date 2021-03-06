package com.louie.authcode.rest;

import com.louie.authcode.engine.core.CodeIdentify;
import com.louie.authcode.engine.model.Response;
import com.louie.authcode.engine.model.ResponseBody;
import com.louie.authcode.file.FileService;
import com.louie.authcode.file.FileServiceImpl;
import com.louie.authcode.file.model.AuthcodeFile;
import com.louie.authcode.rest.utils.RESTfulType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuhongyu.louie on 2016/9/9.
 */
@RestController
@RequestMapping(RESTfulType.USER)
public class IdentifyCodeRESTful {

    @RequestMapping(method = RequestMethod.POST, path = RESTfulType.GET_AUTHCODE)
    public Response getAuthcode(@RequestParam(value = "url", defaultValue = "") String url){
        AuthcodeFile file = new AuthcodeFile(url);
        FileService fileService = new FileServiceImpl();
        file = fileService.downloadFile(file);
        CodeIdentify codeIdentify = new CodeIdentify();
        return new ResponseBody("1", "success", codeIdentify.getCode(file, true)[0]);
    }

}
