package org.devops

def SonarScan(projectName, des, path) {
    def sonarServer = 'http://192.168.0.102:9000'
    sonarDate = sh returnStdout: true, script: 'date +%Y%m%d%H%m%s'
    sonarDate = sonarDate - '\n'
    sh '''
    sonar-scanner  -Dsonar.host.url=${sonarServer}  \
    -Dsonar.projectKey=${projectName}  \
    -Dsonar.projectName=${projectName}  \
    -Dsonar.projectVersion=${sonarDate} \
    -Dsonar.login=admin \
    -Dsonar.password=admin \
    -Dsonar.ws.timeout=30 \
    -Dsonar.projectDescription=${des}  \
    -Dsonar.links.homepage=http://www.baidu.com \
    -Dsonar.sources=${path} \
    -Dsonar.sourceEncoding=UTF-8 \
    -Dsonar.java.binaries=target/classes \
    -Dsonar.java.test.binaries=target/test-classes \
    -Dsonar.java.surefire.report=target/surefire-reports
    '''
}
