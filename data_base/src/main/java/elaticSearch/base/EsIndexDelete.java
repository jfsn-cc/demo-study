package elaticSearch.base;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class EsIndexDelete {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        //创建请求
        DeleteIndexRequest request = new DeleteIndexRequest("class");
        //接收响应
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);//.create(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
        client.close();
    }
}
