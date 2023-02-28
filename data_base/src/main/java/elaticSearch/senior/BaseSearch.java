package elaticSearch.senior;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.RandomScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

public class BaseSearch {
    public static void main(String[] args) throws IOException {
        //构建连接
        RestHighLevelClient client = new RestHighLevelClient(RestClient.
                        builder(new HttpHost("localhost",9200, "http")));
        //
        SearchRequest request = geoSearch();
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        search.getHits().forEach(hit ->{
            System.out.println(hit);
        });
    }


    //基础term搜索
    public static SearchRequest search() {
        //
        SearchRequest request = new SearchRequest("hotel");
        //
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("city","北京")).
                query(QueryBuilders.rangeQuery("price").gt(300).lt(500));
        sourceBuilder.from(0);
        sourceBuilder.size(2);
        request.source(sourceBuilder);
        return request;
    }


    //基础match搜索
    public static SearchRequest matchSearch() {
        //
        SearchRequest request = new SearchRequest("hotel");
        //
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //MatchAllQueryBuilder allQueryBuilder = new MatchAllQueryBuilder();
        //可以选中包含的俄关键词数量
        //MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("title", "金都").operator(Operator.AND).minimumShouldMatch("80%");
       // MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("假日", "title", "amenities");
        MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("title", "文雅酒店").slop(2);
        sourceBuilder.query(queryBuilder);
        request.source(sourceBuilder);
        return request;
    }

    //bool查询
    public static SearchRequest boolSearch() {
        SearchRequest request = new SearchRequest("hotel");
        //
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.termQuery("city","北京"));
                //.filter(QueryBuilders.rangeQuery("price").gt(0).lt(500));
                //.must(QueryBuilders.termQuery("city","天津"));
        sourceBuilder.query(boolQuery);
        request.source(sourceBuilder);
        return request;
    }
    //不干涉评分查询
    public static SearchRequest constantSearch() {
        SearchRequest request = new SearchRequest("hotel");
        //
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        TermQueryBuilder termQuery = QueryBuilders.termQuery("city", "北京");
        ConstantScoreQueryBuilder scoreQueryBuilder = QueryBuilders.constantScoreQuery(termQuery);
        boolQuery.filter().add(scoreQueryBuilder);

        //评分函数
        RandomScoreFunctionBuilder randomFunction = ScoreFunctionBuilders.randomFunction();
        FunctionScoreQueryBuilder queryBuilder = QueryBuilders.functionScoreQuery(termQuery, randomFunction).boostMode(CombineFunction.SUM);
        sourceBuilder.query(queryBuilder);
        request.source(sourceBuilder);
        return request;
    }
    //随机评分查询
    public static SearchRequest dde() {
        SearchRequest request = new SearchRequest("hotel");
        //
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        ConstantScoreQueryBuilder scoreQueryBuilder = QueryBuilders.constantScoreQuery(QueryBuilders.termQuery("city","北京"));
        boolQuery.filter().add(scoreQueryBuilder);
        sourceBuilder.query(boolQuery);
        request.source(sourceBuilder);
        return request;
    }

    //地理位置查找
    public static SearchRequest geoSearch() {
        SearchRequest request = new SearchRequest("hotel");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        GeoDistanceQueryBuilder queryBuilder = QueryBuilders.geoDistanceQuery("location")
                                                .distance(10, DistanceUnit.KILOMETERS).point(40.0, 116.4);
        GeoDistanceSortBuilder sortBuilder = SortBuilders.geoDistanceSort("location", 40.0, 116.4)
                                            .unit(DistanceUnit.KILOMETERS)
                                            .order(SortOrder.DESC);

        sourceBuilder.query(queryBuilder).sort(sortBuilder);
        request.source(sourceBuilder);
        return request;
    }

}
