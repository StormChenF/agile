{
"swagger" : "2.0",
    "info" : {
        "version" : "1.0.0",
        "title" : "Agile API平台",
        "contact" : {
            "email" : "mydeathtrial@163.com"
        }
    },
    "tags" : [
<#list list as property>
        {
            "name" : "${property.className}",
            "description" : "${property.tableComment}"
        }<#if property_has_next>,</#if></#list>
    ],
    "schemes" : [ "http", "https" ],
    "paths" : {
<#list list as property>
        "/${property.className}Service/save" :{
            "post" : {
                "tags":["${property.className}"],
                "summary" : "${property.tableComment}",
                "operationId" : "${property.className}Service_save",
                "consumes" : [ "application/json" ],
                "produces" : [ "application/json" ],
                "parameters" : [
                <#list property.columnList as params><#if params.isPrimaryKey == "false">
                    {
                        "name" : "${params.propertyName}",
                        "in" : "formData",
                        "description" : "${params.remarks}",
                        "required" : ${params.nullable},
                        "type" : "string"
                    }<#if params_has_next>,</#if></#if></#list>
                ],
                "responses" : {
                    "000001" : {
                        "description" : "成功"
                    },
                    "300000" : {
                        "description" : "系统程序异常"
                    }
                },
                "description" : "保存${property.tableComment}"
            }
        },
        "/${property.className}Service/delete" :{
            "post" : {
                "tags":["${property.className}"],
                "summary" : "${property.tableComment}",
                "operationId" : "${property.className}Service_delete",
                "consumes" : [ "application/json" ],
                "produces" : [ "application/json" ],
                "parameters" : [ {
                    "name" : "ids",
                    "in" : "formData",
                    "description" : "主键字符串",
                    "required" : true,
                    "type" : "string"
                } ],
                "responses" : {
                    "000001" : {
                        "description" : "成功"
                    },
                    "300000" : {
                        "description" : "系统程序异常"
                    }
                },
                "description" : "删除${property.tableComment}"
            }
        },
        "/${property.className}Service/update" :{
            "post" : {
                "tags":["${property.className}"],
                "summary" : "${property.tableComment}",
                "operationId" : "${property.className}Service_update",
                "consumes" : [ "application/json" ],
                "produces" : [ "application/json" ],
                "parameters" : [
                <#list property.columnList as params>
                    {
                        "name" : "${params.propertyName}",
                        "in" : "formData",
                        "description" : "${params.remarks}",
                        "required" : ${params.nullable},
                        "type" : "string"
                    }<#if params_has_next>,</#if></#list>
                ],
                "responses" : {
                    "000001" : {
                        "description" : "成功"
                    },
                    "300000" : {
                        "description" : "系统程序异常"
                    }
                },
                "description" : "更新${property.tableComment}"
            }
        },
        "/${property.className}Service/query" :{
            "get" : {
                "tags":["${property.className}"],
                "summary" : "${property.tableComment}",
                "operationId" : "${property.className}Service_query",
                "consumes" : [ "application/json" ],
                "produces" : [ "application/json" ],
                "parameters" : [
                    {
                        "name" : "page",
                        "in" : "query",
                        "description" : "第几页",
                        "required" : false,
                        "type" : "integer"
                    },{
                        "name" : "size",
                        "in" : "query",
                        "description" : "每页条数",
                        "required" : false,
                        "type" : "integer"
                    }
                ],
                "responses" : {
                    "000001" : {
                        "description" : "成功",
                        "schema" : {
                            "type" : "array",
                            "items" : {
                                "$ref" : "#/definitions/${property.className}Entity"
                            }
                        }
                    },
                    "300000" : {
                        "description" : "系统程序异常"
                    }
                },
                "description" : "查询${property.tableComment}"
            }
        }
    <#if property_has_next>,</#if>
</#list>
    },
    "securityDefinitions" : {
        "petstore_auth" : {
            "type" : "oauth2",
            "authorizationUrl" : "oauth2-redirect.html",
            "flow" : "implicit",
            "scopes" : {
                "write:pets" : "",
                "read:pets" : ""
            },
            "api_key" : {
                "type" : "apiKey",
                "name" : "api_key",
                "in" : "header"
            }
        }
    },
    "definitions" : {
<#list list as property>
        "${property.className}Entity" : {
            "type" : "object",
            "properties" : {
    <#list property.columnList as params>
                "${params.columnName}" : {
                    "type" : "${params.columnType}",
                    "description" : "${params.remarks}"
                }<#if params_has_next>,</#if>
    </#list>
            }
        }<#if property_has_next>,</#if>
</#list>
    },
    "externalDocs" : {
        "description" : "",
        "url" : ""
    }
}