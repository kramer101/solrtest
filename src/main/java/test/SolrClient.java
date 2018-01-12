package test;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;

public class SolrClient {
    private static final String ROOT_URL = "http://172.16.15.167:8983/solr/gettingstarted";
    private String name;
    private String description;
    private String manager;
    private int ranking;


    public static void main(String[] args) {
        SolrClient solrClient = new SolrClient();
        solrClient.testCreateAndIndexDocument();
    }


    public void testCreateAndIndexDocument() {
        try {

            HttpSolrClient solrHttpClient = new HttpSolrClient.Builder(ROOT_URL).build();
            solrHttpClient.setParser(new XMLResponseParser());

            ProjectBean projectBean = ProjectBean.builder().
                    description("Test Project Description " + RandomStringUtils.randomAlphanumeric(10))
                    .manager("John Smith")
                    .name("Test Project")
                    .ranking(RandomUtils.nextInt(1, 10))
                    .build();

            solrHttpClient.addBean(projectBean);
            UpdateResponse response =
                    solrHttpClient.commit();

            System.out.println(response);

            SolrQuery query = new SolrQuery();
            query.set("q", "description:*60* OR description:*4*");



            QueryResponse queryResponse =
                    solrHttpClient.query(query);

            SolrDocumentList documentList = queryResponse.getResults();

            documentList.forEach(System.out::println);

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }




}
