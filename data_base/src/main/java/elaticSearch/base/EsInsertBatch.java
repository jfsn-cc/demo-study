package elaticSearch.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class EsInsertBatch {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        User user = new User();
        user.setAge(44);
        user.setName("lwe");
        user.setSex("男");
        User user1 = new User();
        user1.setAge(34);
        user1.setName("lyc");
        user1.setSex("女");
        User user2 = new User();
        user2.setAge(74);
        user2.setName("lyw");
        user2.setSex("男");


        //创建请求
        IndexRequest request = new IndexRequest();
        BulkRequest bulkRequest = new BulkRequest();
        ObjectMapper objectMapper = new ObjectMapper();
        bulkRequest.add( new IndexRequest().index("class").id("1010").source(objectMapper.writeValueAsString(user), XContentType.JSON));
        bulkRequest.add(new IndexRequest().index("class").id("1020").source(objectMapper.writeValueAsString(user1), XContentType.JSON));
       // bulkRequest.add(request.index("class").id("1030").source(objectMapper.writeValueAsString(user2), XContentType.JSON));
        //接收响应
        BulkResponse bulk = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulk.hasFailures());

        client.close();
    }
}
