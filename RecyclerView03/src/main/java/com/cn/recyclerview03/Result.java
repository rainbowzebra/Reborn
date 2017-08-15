package com.cn.recyclerview03;
import java.util.List;

/**
 * 原创作者：谷哥的小弟
 * 博客地址：http://blog.csdn.net/lfdfhl
 */

public class Result {

    private boolean error;
    private List<Girl> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Girl> getResults() {
        return results;
    }

    public void setResults(List<Girl> results) {
        this.results = results;
    }


}
