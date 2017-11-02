package com.xe.demo.common.hx.api.impl;


import com.xe.demo.common.hx.api.SendMessageAPI;
import com.xe.demo.common.hx.comm.EasemobAPI;
import com.xe.demo.common.hx.comm.OrgInfo;
import com.xe.demo.common.hx.comm.ResponseHandler;
import com.xe.demo.common.hx.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.MessagesApi;
import io.swagger.client.model.Msg;

public class EasemobSendMessage implements SendMessageAPI {
    private ResponseHandler responseHandler = new ResponseHandler();
    private MessagesApi api = new MessagesApi();
    @Override
    public Object sendMessage(final Object payload) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameMessagesPost(OrgInfo.ORG_NAME, OrgInfo.APP_NAME, TokenUtil.getAccessToken(), (Msg) payload);
            }
        });
    }
}
