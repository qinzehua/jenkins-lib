package org.devops

def HttpReq(reqType, reqUrl, reqBody) {
    def gitServer = 'http://192.168.0.102:13800/api/v4'
    withCredentials([string(credentialsId: 'global-gitlab-token', variable: 'gitlabToken')]) {
        result = httpRequest customHeaders: [[maskValue: true, name: 'PRIVATE-TOKEN', value: "${gitlabToken}"]],
                 httpMode: reqType,
                 contentType: 'APPLICATION_JSON',
                 consoleLogResponseBody: true,
                 ignoreSslErrors: true,
                 requestBody: reqBody,
                 url: "${gitServer}/${reqUrl}"
    }
    return result
}

// 更改commit状态
def ChangeCommitStatus(projectId, commitSha, status) {
    commitApi = "projects/${projectId}/statuses/${commitSha}?state=${status}"
    response = HttpReq('POST', commitApi, '')
    println(response)
    return response
}
