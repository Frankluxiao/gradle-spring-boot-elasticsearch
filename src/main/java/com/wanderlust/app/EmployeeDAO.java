package com.wanderlust.app;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Repository
public class EmployeeDAO {

//    public List<Employee> getEmployees() {
//
//        return datastore
//                .createQuery(Employee.class)
//                .find()
//                .toList();
//    }

    public IndexResponse create(Employee employee) {

        try {
            RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            IndexRequest request = new IndexRequest("employee")
                    .id(employee.getEmployeeId().toString())
                    .source(mapper.writeValueAsString(employee), XContentType.JSON);
            return client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    public GetResponse get(UUID employeeId) {

        try {
            RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
            GetRequest getRequest = new GetRequest(
                    "employee",
                    employeeId.toString());
            return client.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    public UpdateResponse update(UUID employeeId, Employee employee) {

        try {
            RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
            UpdateRequest request = new UpdateRequest("employee", employeeId.toString());
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        request.doc(mapper.writeValueAsString(employee), XContentType.JSON);
            return client.update(
                    request, RequestOptions.DEFAULT);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

    public DeleteResponse delete(UUID employeeId) {

        try {
            RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
            DeleteRequest request = new DeleteRequest(
                "employee",
                employeeId.toString());
            return client.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}
