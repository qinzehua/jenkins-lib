package org.devops

//构建类型
def Buid(buildType, buildShell) {
    def buildTools = ['maven': 'M2', 'ant': 'ANT', 'gradle': 'GRADLE', 'npm': 'NPM']
    buildHome = tool buildTools[buildType]
    println('当前选的build类型是 ${buildType}')
    sh "${buildHome}/bin/${buildType} ${buildShell}"
}
