package com.example.huhaichang.learn3.eleven;


import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by huhaichang on 2019/8/4.
 */

public class ContentHandler extends DefaultHandler {
    private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;
    //开始解析
    @Override
    public void startDocument() throws SAXException {
        //super.startDocument();
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }
    //开始节点解析
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //super.startElement(uri, localName, qName, attributes);
        //当前节点赋值
        nodeName = localName;
    }
    //节点元素获取
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       // super.characters(ch, start, length);
        //当前节点元素赋值
        if("id".equals(nodeName)){
            id.append(ch,start,length);
        }else if("name".equals(nodeName)){
            name.append(ch,start,length);

        }else if("version".equals(nodeName)){
            version.append(ch,start,length);
        }
    }
    //单个节点解析结束
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
       // super.endElement(uri, localName, qName);
        //打印日志
        if("app".equals(localName)){
            Log.d("id:"+id.toString().trim(),"name:"+name.toString().trim()+" version:"+version.toString().trim());

            //清空StringBuilder为下次赋值做准备
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }
    //结束解析
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
