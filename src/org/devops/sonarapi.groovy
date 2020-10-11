package org.devops

def HttpReq(reqType, reqUrl, reqBody) {
    def sonarServer = 'http://192.168.0.102:9000/api'
    result =    httpRequest authentication: 'sonar-admin-user',
                 httpMode: reqType,
                 contentType: 'APPLICATION_JSON',
                 consoleLogResponseBody: true,
                 ignoreSslErrors: true,
                 requestBody: reqBody,
                 url: "${sonarServer}/${reqUrl}"
    return result
}

// 获取sonar状态
def GetStatus(projectName, commitSha, status) {
    apiUrl = "project_branches/list?project=${projectName}"
    response = HttpReq('GET', apiUrl, '')
    response = readJSON text: """${response.content}"""
    println(response)
    return response
}
