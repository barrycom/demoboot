package com.xe.demo.common.utils;

/**
 * Created by Administrator on 2017-11-14.
 */
public class NewOrdersTemplate {
    private String touser; //用户OpenID
           private String template_id; //模板消息ID

         private String form_id; //URL置空，则在发送后，点击模板消息会进入一个空白页面（ios），或无法点击（android）。

           private ModelMessage data; //详细内容

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public ModelMessage getData() {
        return data;
    }

    public void setData(ModelMessage data) {
        this.data = data;
    }
}
