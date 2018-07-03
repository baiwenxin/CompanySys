package com.bdqn.demo;

import com.bdqn.demo.pojo.Tests;
import com.mongodb.BasicDBObject;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    MongoTemplate template;
    private GridFSBucket gridFSBucket;

    /**
     * 查询所有
     */
    @Test
    public void contextLoads() {

        List<Tests> list=template.findAll(Tests.class,"demo");

        for (Tests tests : list) {
            System.out.println(tests.getName());
        }
    }

    /**
     * 添加
     */
    @Test
    public void addMongoInfo(){
//        Map<String,String> map=new HashMap<String, String>();
//        map.put("name","小红");
//        map.put("name","小黑");
        BasicDBObject doc=new BasicDBObject();
        doc.put("name","大白");
        doc.put("id","6");
        template.save(doc,"demo");
    }

    /**
     * 修改数据
     */
    @Test
    public  void updateMongodbInfo(){
        Query query=new Query(Criteria.where("name").is("三哥"));
        Update update=new Update().set("name","二哥");
        template.updateFirst(query,update,Tests.class,"demo");
    }
    /**
     * 删除数据
     */
        @Test
    public  void  delMongodbInfo(){
            Query query=new Query(Criteria.where("name").is("大白"));
            template.remove(query,Tests.class,"demo");
        }
    /**
     * 向mongodb添加数据
     */
    @Test
    public  void addInfo(){
        gridFSBucket= GridFSBuckets.create(template.getDb(),"wenjian");
        String url="E:/录像文件/mongodb.txt";
        InputStream inputStream=null;
        ObjectId fileid=null;
        // 配置一些参数
        GridFSUploadOptions options = null;
        //截取文件名
        String fileName=url.substring((url.lastIndexOf("/")+1),url.length());
        try {
            inputStream=new FileInputStream(new File(url));
            options=new GridFSUploadOptions().chunkSizeBytes(358400).metadata(new Document("type","presentation"));
            //保存文件
            fileid=gridFSBucket.uploadFromStream(fileName,inputStream,options);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(fileid);
    }
    /**
     * 向mongodb中读取数据
     */
    @Test
    public void downFile(){
        gridFSBucket=GridFSBuckets.create(template.getDb(),"wenjian");
        String url="E:/录像文件/downfile.txt";
        ObjectId id=new ObjectId("5b344b896d66ac23ac290bb4");
        FileOutputStream fileOutputStream=null;
        String result=null;
        try {
            fileOutputStream=new FileOutputStream(new File(url));
            gridFSBucket.downloadToStream(id,fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
                result=fileOutputStream.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.print(result);
    }
    /**
     * 根据objectId删除mongodb中的指定文件
     */
        @Test
    public void delfile(){
            gridFSBucket = GridFSBuckets.create(template.getDb(),"wenjian");
            ObjectId id=new ObjectId("5b34a4606d66ac1d10c7d398");
            gridFSBucket.delete(id);
        }
}
