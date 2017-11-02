package com.xe.demo.common.hx.api.impl;


import com.xe.demo.common.hx.api.FileAPI;
import com.xe.demo.common.hx.comm.EasemobAPI;
import com.xe.demo.common.hx.comm.OrgInfo;
import com.xe.demo.common.hx.comm.ResponseHandler;
import com.xe.demo.common.hx.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.UploadAndDownloadFilesApi;

import java.io.File;


public class EasemobFile implements FileAPI {
    private ResponseHandler responseHandler = new ResponseHandler();
    private UploadAndDownloadFilesApi api = new UploadAndDownloadFilesApi();
    @Override
    public Object uploadFile(final Object file) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatfilesPost(OrgInfo.ORG_NAME, OrgInfo.APP_NAME, TokenUtil.getAccessToken(),(File)file,true);
             }
        });
    }

    @Override
    public Object downloadFile(final String fileUUID,final  String shareSecret,final Boolean isThumbnail) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
               return api.orgNameAppNameChatfilesUuidGet(OrgInfo.ORG_NAME, OrgInfo.APP_NAME, TokenUtil.getAccessToken(),fileUUID,shareSecret,isThumbnail);
            }
        });
    }
}
