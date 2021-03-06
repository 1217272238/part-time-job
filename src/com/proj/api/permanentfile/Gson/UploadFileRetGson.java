package com.proj.api.permanentfile.Gson;

import com.proj.api.exception.error.Reason;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;

public class UploadFileRetGson {
    private int err_code=0;
    private String reson= Reason.getReason(0);
    private List<String> file_token;
    private String check_code;

    public UploadFileRetGson() {
        file_token=new ArrayList<String>();
    }

    public void addFileToken(String fileToken){
        file_token.add(fileToken);
    }

    public int getErr_code() {
        return err_code;
    }

    public String getReson() {
        return reson;
    }

    public List<String> getFile_token() {
        return file_token;
    }
    public String getCheck_code() {
        check_code = DigestUtils.md5Hex(err_code+reson+file_token);
        return check_code;
    }
}
