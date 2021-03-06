package com.louie.authcode.rest;

import com.louie.authcode.engine.core.CodeIdentify;
import com.louie.authcode.engine.model.Response;
import com.louie.authcode.engine.model.ResponseBody;
import com.louie.authcode.file.FileService;
import com.louie.authcode.file.FileServiceImpl;
import com.louie.authcode.file.model.AuthcodeFile;
import com.louie.authcode.rest.utils.RESTfulType;
import com.louie.authcode.utils.ThreadSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liuhongyu.louie on 2016/9/8.
 */
@RestController
@RequestMapping(RESTfulType.USER)
public class TrainingDataRESTful {

    @RequestMapping(method = RequestMethod.POST, path = RESTfulType.TRAINING)
    public Response trainingData(@RequestParam(value = "url", defaultValue = "") String url, @RequestParam(value = "authcode", defaultValue = "") String authcode){
        ThreadSupport.threadPool.execute(() -> {
            AuthcodeFile file = new AuthcodeFile(url, authcode);
            FileService fileService = new FileServiceImpl();
            file = fileService.downloadFile(file);
            CodeIdentify codeIdentify = new CodeIdentify();
            codeIdentify.trainingPicIdentifyForREST(file, true);
        });
        return new ResponseBody("0", "Data has submit.");
    }

}
