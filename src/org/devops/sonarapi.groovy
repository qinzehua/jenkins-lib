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
def GetStatus(projectName) {
    apiUrl = "project_branches/list?project=${projectName}"
    response = HttpReq('GET', apiUrl, '')
    response = readJSON text: """${response.content}"""
    result = response['branches'][0]['status']['qualityGateStatus']
    return result
}

def SearchProject(projectName) {
    apiUrl = "projects/search?projects=${projectName}"
    response = HttpReq('GET', apiUrl, '')
    response = readJSON text: """${response.content}"""
    result = response['paging']['total']
    if (result.toString() == '0') {
        return false
    }
    return true
}

def CreateProject(projectName) {
    apiUrl = "projects/create?name=${projectName}&project=${projectName}"
    response = HttpReq('POST', apiUrl, '')
    response = readJSON text: """${response.content}"""
    println(response)
    return true
}

//配置规则
def ConfigQualityProfiles( projectName, language, qpame) {
    apiUrl = "qualityprofiles/add_project?project=${projectName}&language=${language}&qualityProfile=${qpame}"
    response = HttpReq('POST', apiUrl, '')
    response = readJSON text: """${response.content}"""
    println(response)
    return true
}

//获取质量阈的id
def ConfigQualityGateId(gateName) {
    apiUrl = "qualitygates/show?name=${gateName}"
    response = HttpReq('GET', apiUrl, '')
    response = readJSON text: """${response.content}"""
    gateId = response['id']
    println(response)
    return gateId
}

//配置项目质量阈
def ConfigQualityGate(projectName, gateName) {
    gateId = ConfigQualityGateId(gateName)
    apiUrl = "qualitygates/select?gateId=${gateId}&projectKey=${projectName}"
    response = HttpReq('POST', apiUrl, '')
    response = readJSON text: """${response.content}"""
    println(response)
    return true
}
