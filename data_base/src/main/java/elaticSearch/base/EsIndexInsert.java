package elaticSearch.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class EsIndexInsert {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        User user = new User();
        user.setAge(24);
        user.setName("ly");
        user.setSex("男");
        //创建请求
        IndexRequest request = new IndexRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        String josn = objectMapper.writeValueAsString(user);
        request.index("class").id("1003").source(josn, XContentType.JSON);
        //接收响应
        IndexResponse index = client.index(request, RequestOptions.DEFAULT);//.create(request, RequestOptions.DEFAULT);
        System.out.println(index.getIndex());

        client.close();
    }
}
