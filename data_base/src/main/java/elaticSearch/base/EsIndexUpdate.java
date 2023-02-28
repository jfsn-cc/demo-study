package elaticSearch.base;

import org.apache.http.HttpHost;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class EsIndexUpdate {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        //创建请求
        UpdateRequest request = new UpdateRequest();
        request.index("class").id("1003").doc(XContentType.JSON, "age", 25);
        UpdateResponse update = client.update(request, RequestOptions.DEFAULT);
        System.out.println(update.getIndex());
        //接收响应
        //System.out.println(delete.isAcknowledged());
        client.close();
    }
}
