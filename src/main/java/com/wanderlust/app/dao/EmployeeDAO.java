package com.wanderlust.app.dao;


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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Repository
public class EmployeeDAO {

    @Autowired
    RestHighLevelClient client;

//    public List<Employee> getEmployees() {
//
//        return datastore
//                .createQuery(Employee.class)
//                .find()
//                .toList();
//    }

    public IndexResponse create(String employeeId, String employee) {

        try {

            IndexRequest request = new IndexRequest("employee")
                    .id(employeeId)
                    .source(employee, XContentType.JSON);
            return client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    public GetResponse get(String employeeId) {

        try {

            GetRequest getRequest = new GetRequest("employee", employeeId);
            return client.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    public UpdateResponse update(String employeeId, String employee) {

        try {

            UpdateRequest request = new UpdateRequest("employee", employeeId)
                    .doc(employee, XContentType.JSON);
            return client.update(request, RequestOptions.DEFAULT);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }

    }

    public DeleteResponse delete(String employeeId) {

        try {

            DeleteRequest request = new DeleteRequest("employee", employeeId);
            return client.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}
