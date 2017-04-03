package com.lc.springBoot.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.lc.springBoot.util.Base64;
import com.lc.springBoot.util.HttpBody;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * 实现参数加密解密
 * 我们参照FastJsonHttpMessageConverter4的实现,进行改造
 *
 * @author lsj <lishuijun1992@gmail.com>
 * @date 17-3-29
 */
public class EncrypConverter extends FastJsonHttpMessageConverter4 {

    /**
     * 重写read()方法
     *
     * @param type
     * @param contextClass
     * @param inputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream in = inputMessage.getBody();
        byte[] data = Base64.decode(IOUtils.toString(in, "UTF-8"));
        return JSON.parseObject(data, 0, data.length, getFastJsonConfig().getCharset(), type, getFastJsonConfig().getFeatures());
    }

    /**
     * 重写readInternal
     *
     * @param clazz
     * @param inputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

        InputStream in = inputMessage.getBody();
        byte[] data = Base64.decode(IOUtils.toString(in, "UTF-8"));
        return JSON.parseObject(data, 0, data.length, getFastJsonConfig().getCharset(), clazz, getFastJsonConfig().getFeatures());
    }


    /**
     * 重写writeInternal()方法
     *
     * @param obj
     * @param type
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(Object obj, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String s = Base64.encode(JSON.toJSONBytes(obj));
        HttpBody httpBody = new HttpBody();
        httpBody.setStatus(1);
        httpBody.setBody(s);
        super.writeInternal(httpBody, type, outputMessage);
    }
}
