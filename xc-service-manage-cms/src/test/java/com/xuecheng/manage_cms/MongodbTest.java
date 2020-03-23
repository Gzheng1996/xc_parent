package com.xuecheng.manage_cms;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.GridFSUploadStream;
import net.minidev.json.JSONUtil;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MongodbTest {


    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    @Test
    public void testConnection() {
        //创建mongodb 客户端
       // MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        //或者采用连接字符串 //
        MongoClientURI connectionString = new MongoClientURI("mongodb://root:123@localhost:27017"); //
         MongoClient mongoClient = new MongoClient(connectionString); //连接数据库

        MongoDatabase database = mongoClient.getDatabase("xc_cms"); // 连接collection
         MongoCollection<Document> collection = database.getCollection("student"); //查询第一个文档
         Document myDoc = collection.find().first(); //得到文件内容 json串
         String json = myDoc.toJson(); System.out.println(json); }


         @Test
    public void testRestTemplet(){
             RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());

             ResponseEntity<Map> forEntity = restTemplate.getForEntity("", Map.class);

             Map body = forEntity.getBody();
             body.toString();
         }

    @Test
    public void testRestTemple2t() throws FileNotFoundException {

        File file = new File("C:\\Users\\Lenovo\\Desktop\\管正学历证明.PNG");

        FileInputStream fileInputStream = new FileInputStream(file);

        ObjectId sss = gridFsTemplate.store(fileInputStream, "sss");

           System.out.println(sss.toString());
    }

    @Test
    public void testRestTemple3t() throws FileNotFoundException {
        GridFSFindIterable id = gridFsTemplate.find(Query.query(Criteria.where("_id").is("5e6c74b1bbd2dd67e8624af2")));

        System.out.println(id);
        GridFSUploadStream gridFSUploadStream = gridFSBucket.openUploadStream(id.toString());



    }

}
