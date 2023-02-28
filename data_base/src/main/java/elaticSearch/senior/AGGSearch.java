package elaticSearch.senior;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.filter.Filter;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Avg;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.StatsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ValueCountAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * 聚合搜索
 */
public class AGGSearch {
    public static void main(String[] args) throws IOException {
        //构建连接
        RestHighLevelClient client = new RestHighLevelClient(RestClient.
                builder(new HttpHost("localhost",9200, "http")));
        String aggName = "my_agg";
        SearchRequest request = queryFilterAggSearch(aggName);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Aggregations aggregations = response.getAggregations();
        //Stats stats = aggregations.get(aggName);
        // System.out.println("avg = " + stats.getAvg() + ";max = " + stats.getMax() + ";min = " + stats.getMin() +";sum = "+ stats.getSum() + ";"+stats.getCount());

        //还需要根据返回数据定义字符串或者数值类型
       // ParsedTerms terms = aggregations.get(aggName);
/*        terms.getBuckets().forEach(item->{
            System.out.println(item.getKey()+":"+item.getDocCount());
        });*/

        //组合桶聚合
/*        Terms terms = aggregations.get(aggName);
        terms.getBuckets().stream().forEach(bucket -> {
            System.out.println(bucket.getKey()+":"+bucket.getDocCount());
        });*/

        //查询聚合，获取查询结果和结果的聚合情况 再输出聚合情况
/*        response.getHits().forEach(System.out::println);
        Avg avg = aggregations.get(aggName);
        System.out.println(avg.getName() + ": " + avg.getValue());*/

        //前后过滤器输出
        response.getHits().forEach(System.out::println);
        Filter filter = aggregations.get("filter");
        Aggregations filterAggregations = filter.getAggregations();
        Avg avg = filterAggregations.get(aggName);
        System.out.println(avg.getName() + ":" + avg.getValue());
        client.close();
    }


    //简单获取平均数
    private static SearchRequest baseAgg(String aggName) {
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        AvgAggregationBuilder aggregationBuilder = AggregationBuilders.avg(aggName).field("price");
        aggregationBuilder.missing(100);//
        ValueCountAggregationBuilder price = AggregationBuilders.count(aggName).field("price");
        sourceBuilder.aggregation(aggregationBuilder);
        return request.source(sourceBuilder);
    }

    //获取各类计算数值
    private static  SearchRequest stateAgg(String aggName) {
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        StatsAggregationBuilder statsAggregationBuilder = AggregationBuilders.stats(aggName).field("price");
        sourceBuilder.aggregation(statsAggregationBuilder);
        return request.source(sourceBuilder);
    }

    //桶聚合 单维度桶聚合 term桶聚合
    private static SearchRequest bucketAgg(String aggName) {
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        TermsAggregationBuilder city = AggregationBuilders.terms(aggName).field("full_room");
        sourceBuilder.aggregation(city);
        request.source(sourceBuilder);
        return request;
    }

    //范围查询，组合子桶查询
    private static SearchRequest rangeAgg(String aggName) {
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        TermsAggregationBuilder city = AggregationBuilders.terms(aggName).field("city");

        RangeAggregationBuilder aggregationBuilder = AggregationBuilders.range("my_range").field("price");
        aggregationBuilder.addRange(100d, 200d);
        aggregationBuilder.addRange(200d, 300d);
        aggregationBuilder.addRange(300d, 500d);
        city.subAggregation(aggregationBuilder);
        sourceBuilder.aggregation(city);
        request.source(sourceBuilder);
        return request;
    }

    //先查询在聚合
    public static SearchRequest queryAggSearch(String aggName) {
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        AvgAggregationBuilder aggregationBuilder = AggregationBuilders.avg(aggName).field("price");

        sourceBuilder.aggregation(aggregationBuilder);
        sourceBuilder.query(QueryBuilders.termQuery("city", "北京"));
        request.source(sourceBuilder);
        return request;
    }

    //前后过滤器 在聚合中进一步过滤数据
    public static SearchRequest queryFilterAggSearch(String aggName) {
        SearchRequest request = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        AvgAggregationBuilder aggregationBuilder = AggregationBuilders.avg(aggName).field("price");
        FilterAggregationBuilder filter = AggregationBuilders.filter("filter", QueryBuilders.termQuery("full_room", false));
        filter.subAggregation(aggregationBuilder);
        sourceBuilder.aggregation(filter);
        sourceBuilder.query(QueryBuilders.termQuery("city", "天津"));
        request.source(sourceBuilder);
        return request;
    }
}
