package elaticSearch.base;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

public class EsCombineQuery {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        //创建请求
        SearchRequest request = new SearchRequest("class");
        //先构建Builder
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder("age");
        //获取全部数据
     //   sourceBuilder.query(QueryBuilders.matchAllQuery());

        rangeQueryBuilder.lt("100");
        queryBuilder.must(QueryBuilders.matchQuery("name", "ly"));
        //queryBuilder.mustNot(QueryBuilders.termQuery("age", 11));
        queryBuilder.must().add(rangeQueryBuilder);
        sourceBuilder.query(queryBuilder);
        sourceBuilder.from(0).size(3);//分页参数， from指的是偏移值，size 每页大小
        sourceBuilder.sort("age", SortOrder.DESC);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("加前缀");
        highlightBuilder.postTags("后缀");
        highlightBuilder.field("name");
        sourceBuilder.highlighter(highlightBuilder);//前后缀必须都要有
        //接收响应
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //System.out.println(response.);
        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getHighlightFields());
            System.out.println(hit.getSourceAsString());
        }
        client.close();
    }
}
