package org.devops

//构建类型
def Build(buildType, buildShell) {
    def buildTools = ['mvn': 'M2', 'ant': 'Ant', 'gradle': 'GRADLE', 'npm': 'NodeEnv']
    buildHome = tool buildTools[buildType]
    println('当前选的build类型是 ${buildType}')
    sh "${buildHome}/bin/${buildType} ${buildShell}"
}
