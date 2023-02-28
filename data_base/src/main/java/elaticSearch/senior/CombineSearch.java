package elaticSearch.senior;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoostingQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScriptScoreQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.rescore.QueryRescorerBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CombineSearch {
    public static void main(String[] args) throws IOException {
        //构建连接
        RestHighLevelClient client = new RestHighLevelClient(RestClient.
                builder(new HttpHost("localhost",9200, "http")));
        //
        SearchRequest request = reScorerBuilder();
        SearchResponse search = client.search(request, RequestOptions.DEFAULT);
        search.getHits().forEach(System.out::println);
        client.close();
    }

    public static SearchRequest negativeSearch() {
        SearchRequest request = new SearchRequest("hotel");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
      //  BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("title", "金都");
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price").lt(200);
        BoostingQueryBuilder boostingQuery = QueryBuilders.boostingQuery(matchQuery, rangeQueryBuilder);
        boostingQuery.negativeBoost(0.2F);
        sourceBuilder.query(boostingQuery);
        return request.source(sourceBuilder);
    }

    //使用scriptScore自定义函数评分
    public static SearchRequest scriptSearch() {
        SearchRequest request = new SearchRequest("hotel");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        String scriptScore = "int weight = -100;\n" +
                                "def random = randomScore(params.uuidHash);\n" +
                                "return weight*random;";
        Map paramMap = new HashMap();
        paramMap.put("uuidHash", -1);
        Script script = new Script(Script.DEFAULT_SCRIPT_TYPE, "painless", scriptScore, paramMap);
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("title", "金都");
        ScriptScoreQueryBuilder scriptScoreQuery = QueryBuilders.scriptScoreQuery(matchQuery, script);
        sourceBuilder.query(scriptScoreQuery);
        request.source(sourceBuilder);
        return request;
    }

    //二次打分
    public static SearchRequest reScorerBuilder() {
        SearchRequest request = new SearchRequest("hotel");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //原始查询
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("price").gte("300");
        sourceBuilder.query(rangeQueryBuilder);
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "金都");
        //二次打分需要新建二次打分builder
        QueryRescorerBuilder rescorerBuilder = new QueryRescorerBuilder(matchQueryBuilder);
        //二次打分设置权重
        rescorerBuilder.setQueryWeight(0.7f);
        rescorerBuilder.setRescoreQueryWeight(1.7f);
        //添加二次打分请求
        sourceBuilder.addRescorer(rescorerBuilder);
        request.source(sourceBuilder);
        return request;
    }

    //聚合AVG搜索
}
