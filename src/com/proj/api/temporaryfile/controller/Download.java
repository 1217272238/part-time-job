package com.proj.api.temporaryfile.controller;

import com.proj.api.exception.file.MongoDbException;
import com.proj.api.temporaryfile.model.DeleteExpiredFile;
import com.proj.api.utils.MongoDbPool;
import com.mongodb.BasicDBObject;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.Binary;

public class Download{
    private byte[] fileByte=null;
    private String fileName="";
    public Download(String fileToken) throws MongoDbException {
        try{
            MongoDatabase mongoDatabase = MongoDbPool.getConn("wjxt");
            MongoCollection<Document> collection = mongoDatabase.getCollection("temporaryfile");

            BasicDBObject basicDBObject=new BasicDBObject("file_token",fileToken);
            FindIterable<Document> result=collection.find(basicDBObject);
            Document document=result.first();
            fileByte= ((Binary) document.get("file_byte")).getData();
            fileName=(String)document.get("orgin_file_name");
            DeleteExpiredFile.doCheck();
        }catch(Exception e){
            throw new MongoDbException();
        }

    }

    public byte[] getFileByte() {
        return fileByte;
    }

    public String getFileName() {
        return fileName;
    }
}