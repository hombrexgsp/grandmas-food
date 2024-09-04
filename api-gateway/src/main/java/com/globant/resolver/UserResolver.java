package com.globant.resolver;

import com.globant.infrastructure.user.UserClient;
import graphql.kickstart.tools.GraphQLResolver;

import java.util.List;

public class UserResolver implements GraphQLResolver {

    private UserClient userClient;

    public UserResolver(UserClient userClient) {
        this.userClient = userClient;
    }

    public List<Object> allUsers(){
        return userClient.allUsers();
    }

    public Object userByDocumentNumber(String documentNumber){
        return userClient.userByDocumentNumber(documentNumber);
    }

}
