package elaticSearch.base;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

public class EsDcQuery {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        //创建请求
        SearchRequest request = new SearchRequest("shopping");
        //先构建Builder
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //获取全部数据
     //   sourceBuilder.query(QueryBuilders.matchAllQuery());


        sourceBuilder.query(QueryBuilders.matchQuery("name", "小米公司"));
        sourceBuilder.from(0).size(3);//分页参数， from指的是偏移值，size 每页大小
        sourceBuilder.sort("price", SortOrder.DESC);
        //接收响应
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //System.out.println(response.);
        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
        client.close();
    }
}
